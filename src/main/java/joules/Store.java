package joules;

import joules.task.Task;
import joules.task.TaskList;
import joules.task.Deadline;
import joules.task.Todo;
import joules.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * Handles persistent storage of tasks for the Joules chatbot.
 * <p>
 * This class provides static methods to load tasks from a file,
 * store individual tasks, and save all tasks to a file.
 * </p>
 */
public class Store {
    /** Path to read stored tasks */
    private static final String PATH = "/store/tasks.txt";

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
        File f = new File(Store.PATH);
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
                    tasks.addTask(t);
                }
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
        try {
            FileWriter fw = new FileWriter(Store.PATH, true);
            fw.write(textToAppend + "\n");
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
    public static void saveAll(TaskList tasks) {
        try {
            // clear the file
            FileWriter fw = new FileWriter(Store.PATH);
            fw.close();
            for (int i = 1; i <= tasks.taskCount(); i++) {
                tasks.getTask(i).store();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
