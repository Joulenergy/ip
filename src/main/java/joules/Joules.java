package joules;

import java.time.LocalDate;
import java.util.Set;

import joules.contact.Contact;
import joules.contact.ContactList;
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
    private static final TaskList TASKS = new TaskList(DEFAULT_CAPACITY);

    /** List of contacts the user has saved */
    private static final ContactList CONTACTS = new ContactList(DEFAULT_CAPACITY);

    /**
     * Constructs the Joules chatbot
     */
    public Joules() {
        // Load input
        Store.loadTasks(TASKS);
        Store.loadContacts(CONTACTS);
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
                return UI.getAllTasksMessage(TASKS);
            } else if (input.equals("contacts")) {
                return UI.getAllContactsMessage(CONTACTS);
            } else if (Set.of("find", "findc").contains(commands[0])) {
                String keyword = Parser.parseFind(input);
                return getFindResults(keyword, commands);
            } else if (Set.of("mark", "unmark", "delete").contains(commands[0])) {
                int taskNum = Parser.parseNumAfterCommand(input, TASKS);
                return executeChangeAndReturnMessage(taskNum, commands);
            } else if (commands[0].equals("deletec")) {
                int contactNum = Parser.parseNumAfterCommand(input, CONTACTS);
                Contact c = CONTACTS.get(contactNum);
                CONTACTS.remove(contactNum);
                Store.saveAllContacts(CONTACTS);
                return UI.getDeletedContactMessage(c);
            } else if (Set.of("todo", "deadline", "event").contains(commands[0])) {
                Task t = createTask(input, commands);
                return UI.getAddedTaskMessage(t, TASKS.itemCount());
            } else if (commands[0].equals("addc")) {
                Contact c = createContact(input);
                return UI.getAddedContactMessage(c);
            } else {
                throw new JoulesException(UI.unknownCommandMessage());
            }
        } catch (JoulesException e) {
            return UI.getErrorMessage(e.getMessage());
        }
    }

    private static String getFindResults(String keyword, String[] commands) throws JoulesException {
        assert keyword != "" : "keyword should not be empty";
        switch (commands[0]) {
        case "find":
            return UI.getMatchingTasksMessage(keyword, TASKS);
        case "findc":
            return UI.getMatchingContactsMessage(keyword, CONTACTS);
        default:
            throw new JoulesException(UI.unknownCommandMessage());
        }
    }
    
    /**
     * Executes modification command {@code mark} {@code unmark} or {@code delete}
     * on a task and returns the corresponding feedback message
     * <p>
     * After applying the change, this method persists the updated
     * task list to storage.
     * </p>
     * @param taskNum 1-based index of the task to be modified.
     * @param commands User input in array.
     * @return Feedback message describing the result of the modification.
     * @throws JoulesException If the command is invalid or the task number is out of range.
     */
    private static String executeChangeAndReturnMessage(int taskNum, String[] commands) throws JoulesException {
        Task task = TASKS.get(taskNum);
        String message;
        switch (commands[0]) {
        case "mark" -> {
            task.mark();
            message = UI.getMarkedTaskMessage(task);
        }
        case "unmark" -> {
            task.unmark();
            message = UI.getUnmarkedTaskMessage(task);
        }
        case "delete" -> {
            TASKS.remove(taskNum);
            message = UI.getDeletedTaskMessage(task);
        }
        default -> throw new JoulesException(UI.unknownCommandMessage());
        }
        Store.saveAllTasks(TASKS);
        return message;
    }

    /**
     * Creates a new task based on the user input.
     * <p>
     * Supported commands are:
     * <ul>
     *   <li>{@code todo} – creates a {@link Todo}</li>
     *   <li>{@code deadline} – creates a {@link Deadline} with a description and due date</li>
     *   <li>{@code event} – creates an {@link Event} with a description, start date, and end date</li>
     * </ul>
     * The newly created task is added to the task list and stored persistently.
     * </p>
     *
     * @param input Full user input string.
     * @param commands Tokenized version of the input, where the first element
     *                 specifies the task type.
     * @return The task that was created and stored.
     * @throws JoulesException If the input format is invalid or the command is unsupported.
     */
    private static Task createTask(String input, String[] commands) throws JoulesException {
        Task t;
        switch (commands[0]) {
        case "todo" -> {
            String todo = Parser.parseTodo(input);
            t = new Todo(todo);
        }
        case "deadline" -> {
            String[] deadline = Parser.parseDeadline(input);
            t = new Deadline(deadline[0], LocalDate.parse(deadline[1]));
        }
        case "event" -> {
            String[] event = Parser.parseEvent(input);
            t = new Event(event[0], LocalDate.parse(event[1]), LocalDate.parse(event[2]));
        }
        default -> throw new JoulesException(UI.unknownCommandMessage());
        }
        TASKS.add(t);
        t.store();
        return t;
    }

    public static Contact createContact(String input) throws JoulesException {
        String[] contactInfo = Parser.parseContact(input);
        Contact c = new Contact(contactInfo[0], contactInfo[1]);
        CONTACTS.add(c);
        c.store();
        return c;
    }

    public String welcome() {
        return UI.getWelcomeMessage();
    }
}
