package joules;

import java.time.LocalDate;
import java.util.Set;

import joules.task.Deadline;
import joules.task.Event;
import joules.task.Task;
import joules.task.TaskList;
import joules.task.Todo;

/**
 * Entry point for the Joules chatbot application.
 * <p>
 * This class contains the {@code main} method that starts the chatbot,
 * initializes stored tasks, and continuously processes user commands
 * until the user exits with the {@code bye} command.
 * </p>
 */
public class Joules {
    /** Initial size of task list */
    private static final int DEFAULT_CAPACITY = 100;

    /** User interface for interacting with the chatbot */
    private static final Ui UI = new Ui();

    /** List of tasks representing the user's task history */
    private static final TaskList tasks = new TaskList(DEFAULT_CAPACITY);

    /**
     * Constructs the Joules chatbot
     */
    public Joules() {
        // Load input
        Store.loadTasks(tasks);
    }

    /**
     * Generates a response for the user's chat message.
     * This method processes commands such as {@code todo},
     * {@code deadline}, {@code event}, {@code mark}, {@code unmark},
     * {@code delete}, and {@code list} until the {@code bye} command
     * is received.
     *
     * @param input User's chat message.
     * @return Joules response string.
     */
    public String getResponse(String input) {
        try {
            String[] commands = input.split(" ");
            if (input.equals("bye")) {
                return UI.getGoodbyeMessage();
            } else if (input.equals("list")) {
                return UI.getAllTasksMessage(tasks);
            } else if (commands[0].equals("find")) {
                String keyword = Parser.parseFind(input);
                assert keyword != "" : "keyword should not be empty";
                return UI.getMatchingTasksMessage(keyword, tasks);
            } else if (commands.length == 2 && Set.of("mark", "unmark", "delete").contains(commands[0])) {
                int taskNum = Parser.parseTaskNum(input, tasks);
                Task task = tasks.getTask(taskNum);
                assert task != null : "task should not be null";
                if (commands[0].equals("mark")) {
                    task.mark();
                    Store.saveAll(tasks);
                    return UI.getMarkedTaskMessage(task);
                } else if (commands[0].equals("unmark")) {
                    task.unmark();
                    Store.saveAll(tasks);
                    return UI.getUnmarkedTaskMessage(task);
                } else {
                    // delete
                    tasks.removeTask(taskNum);
                    return UI.getDeletedTaskMessage(task);
                }
            } else {
                Task t;
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
                    throw new JoulesException(" I do not understand your command ;<");
                }
                assert t != null : "Task t should not be null";
                tasks.addTask(t);
                t.store();
                return UI.getAddedTaskMessage(t, tasks.taskCount());
            }
        } catch (JoulesException e) {
            return UI.getErrorMessage(e.getMessage());
        }
    }

    public String welcome() {
        return UI.getWelcomeMessage();
    }
}
