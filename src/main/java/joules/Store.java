package joules;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

import joules.contact.Contact;
import joules.contact.ContactList;
import joules.task.Deadline;
import joules.task.Event;
import joules.task.Task;
import joules.task.TaskList;
import joules.task.Todo;

/**
 * Handles persistent storage of tasks for the Joules chatbot.
 * <p>
 * This class provides static methods to load tasks from a file,
 * store individual tasks, and save all tasks to a file.
 * </p>
 */
public class Store {
    /** Path to read stored tasks */
    private static final String TASKS_PATH = "/store/tasks.txt";
    private static final String CONTACTS_PATH = "/store/contacts.txt";

    /**
     * Loads tasks from the storage file into the given {@link TaskList}.
     * <p>
     * If the file does not exist, it will be created automatically.
     * Each line in the file represents a task with the format:
     * <pre>
     * type | isDone | description | [date] | [endDate]
     * </pre>
     * </p>
     *
     * @param tasks The {@link TaskList} to populate with loaded tasks.
     */
    public static void loadTasks(TaskList tasks) {
        assert TASKS_PATH != null && !TASKS_PATH.trim().isEmpty() : "Storage path must be defined";
        File f = new File(Store.TASKS_PATH);
        try {
            if (!f.exists()) {
                f.getParentFile().mkdir();
                f.createNewFile();
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] taskDetails = line.split(" \\| ");
                Task t = null;
                assert !(taskDetails[0].isEmpty()) : "Task type should not be empty";
                switch (taskDetails[0]) {
                case "T":
                    t = new Todo(taskDetails[2]);
                    break;
                case "D":
                    t = new Deadline(taskDetails[2], LocalDate.parse(taskDetails[3]));
                    break;
                case "E":
                    t = new Event(taskDetails[2], LocalDate.parse(taskDetails[3]), LocalDate.parse(taskDetails[4]));
                    break;
                default:
                    System.out.println("Unable to load unknown task type: " + line);
                }
                if (t != null) {
                    if (taskDetails[1].equals("1")) {
                        t.mark();
                    }
                    tasks.add(t);
                }
            }
        } catch (IOException | SecurityException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadContacts(ContactList contacts) {
        assert CONTACTS_PATH != null && !CONTACTS_PATH.trim().isEmpty() : "Storage path must be defined";
        File f = new File(Store.CONTACTS_PATH);
        try {
            if (!f.exists()) {
                f.getParentFile().mkdir();
                f.createNewFile();
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] contactDetails = line.split(" \\| ");
                if (contactDetails.length != 2) {
                    System.out.println("Unable to load unknown contact type: " + line);
                }
                contacts.add(new Contact(contactDetails[0], contactDetails[1]));
            }
        } catch (IOException | SecurityException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Appends a single task string to the storage file.
     *
     * @param textToAppend The string representation of the task to append.
     */
    public static void storeTask(String textToAppend) {
        assert TASKS_PATH != null && !TASKS_PATH.trim().isEmpty() : "Storage path must be defined";
        try {
            FileWriter fw = new FileWriter(Store.TASKS_PATH, true);
            fw.write(textToAppend + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void storeContact(String contactToAppend) {
        try {
            FileWriter fw = new FileWriter(Store.CONTACTS_PATH, true);
            fw.write(contactToAppend + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves all tasks in the given {@link TaskList} to the storage file.
     * <p>
     * This method overwrites the file contents and writes all tasks
     * in their current state.
     * </p>
     *
     * @param tasks The {@link TaskList} containing tasks to save.
     */
    public static void saveAllTasks(TaskList tasks) {
        try {
            // clear the file
            FileWriter fw = new FileWriter(Store.TASKS_PATH);
            fw.close();
            assert tasks.itemCount() >= 0 : "Task count should not be negative";
            for (int i = 1; i <= tasks.itemCount(); i++) {
                tasks.get(i).store();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveAllContacts(ContactList contacts) {
        try {
            // clear the file
            FileWriter fw = new FileWriter(Store.CONTACTS_PATH);
            fw.close();
            assert contacts.itemCount() >= 0 : "Contact count should not be negative";
            for (int i = 1; i <= contacts.itemCount(); i++) {
                contacts.get(i).store();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
