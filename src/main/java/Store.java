import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Store {
    private static final String PATH = "/store/tasks.txt";

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

    public static void storeTask(String textToAppend) {
        try {
            FileWriter fw = new FileWriter(Store.PATH, true);
            fw.write(textToAppend + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

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
