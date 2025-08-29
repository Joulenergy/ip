package joules;

import joules.task.Task;
import joules.task.TaskList;
import joules.task.Deadline;
import joules.task.Todo;
import joules.task.Event;

import java.util.Set;
import java.time.LocalDate;

/**
 * Entry point for the Joules chatbot application.
 * <p>
 * This class contains the {@code main} method that starts the chatbot,
 * initializes stored tasks, and continuously processes user commands
 * until the user exits with the {@code bye} command.
 * </p>
 */
public class Joules {
    /** User interface for interacting with the chatbot */
    private static final Ui UI = new Ui();

    /** List of tasks representing the user's task history */
    private static final TaskList history = new TaskList(100);

    /**
     * Starts the Joules chatbot
     * This method processes commands such as {@code todo},
     * {@code deadline}, {@code event}, {@code mark}, {@code unmark},
     * {@code delete}, and {@code list}.
     * The loop continues until the {@code bye} command is received.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        UI.showWelcome();

        // Load input
        Store.loadTasks(history);

        // Accept user commands
        boolean bye = false;
        while (!bye) {
            String input = UI.readInput();
            String[] commands = input.split(" ");
            if (input.equals("bye")) {
                bye = true;
                UI.showGoodbye();
            } else if (input.equals("list")) {
                UI.listTasks(history);
            } else if (commands.length == 2 && Set.of("mark", "unmark", "delete").contains(commands[0])) {
                try {
                    int taskNum = Parser.parseTaskNum(input, history);
                    Task task = history.getTask(taskNum);
                    if (commands[0].equals("mark")) {
                        task.mark();
                        Store.saveAll(history);
                        UI.markTask(task);
                    } else if (commands[0].equals("unmark")) {
                        task.unmark();
                        Store.saveAll(history);
                        UI.unMarkTask(task);
                    } else {
                        // delete
                        history.removeTask(taskNum);
                        UI.deleteTask(task);
                    }
                } catch (JoulesException e) {
                    UI.showError(e.getMessage());
                }
            } else {
                Task t;
                try {
                    if (commands[0].equals("todo")) {
                        String todo = Parser.parseTodo(input);
                        t = new Todo(todo);
                    } else if (commands[0].equals("deadline")) {
                        String[] deadline = Parser.parseDeadline(input);
                        t = new Deadline(deadline[0], LocalDate.parse(deadline[1]));
                    } else if (commands[0].equals("event")) {
                        String[] event = Parser.parseEvent(input);
                        t = new Event(event[0], LocalDate.parse(event[1]), LocalDate.parse(event[2]));
                    } else {
                        throw new JoulesException(" Sorry, I do not understand ;<");
                    }
                    history.addTask(t);
                    t.store();
                    UI.addTask(t, history.taskCount());
                } catch (JoulesException e) {
                    UI.showError(e.getMessage());
                }
            }
        }
    }
}