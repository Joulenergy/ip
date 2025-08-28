import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import java.time.LocalDate;

public class Joules {
    public static void main(String[] args) {
        System.out.println(" Hello! I'm Joules!\n" +
                " What can I do for you?");

        // Load input
        ArrayList<Task> history = new ArrayList<>(100);
        Store.loadTasks(history);

        // Accept user input
        Scanner scanner = new Scanner(System.in);
        boolean bye = false;
        while (!bye) {
            String input = scanner.nextLine();
            String[] commands = input.split(" ");
            if (input.equals("bye")) {
                bye = true;
                System.out.println(" Bye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                System.out.println(" You got this! These are your tasks:");
                for (int i = 0; i < history.size(); i++) {
                    System.out.printf(" %d.%s%n", i + 1, history.get(i));
                }
            } else if (commands.length == 2 && Set.of("mark", "unmark", "delete").contains(commands[0])){
                try {
                    int taskNum = Integer.parseInt(commands[1]);
                    if (taskNum > history.size()) {
                        throw new JoulesException(" There are only " + history.size() + " tasks!");
                    }
                    Task task = history.get(taskNum - 1);
                    if (commands[0].equals("mark")) {
                        task.mark();
                        Store.saveAll(history);
                        System.out.println(" Keep up the good work! I've marked this task as done:");
                        System.out.println("   " + task);
                    } else if (commands[0].equals("unmark")) {
                        task.unmark();
                        Store.saveAll(history);
                        System.out.println(" Okay, I've marked this task as not done yet::");
                        System.out.println("   " + task);
                    } else {
                        // delete
                        history.remove(taskNum - 1);
                        System.out.println("Okay, I've removed this task:");
                        System.out.println("   " + task);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Please enter a valid task number!");
                } catch (JoulesException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                Task t;
                String[] split;
                try {
                    if (commands[0].equals("todo")) {
                        split = input.split("todo ");
                        if (split.length == 1 || split[1].trim().isEmpty()) {
                            throw new JoulesException(" Please add a description about what the todo is!");
                        }
                        t = new Todo(split[1]);
                    } else if (commands[0].equals("deadline")) {
                        split = input.split("deadline |/by ");
                        if (split.length == 1 || split[1].trim().isEmpty()) {
                            throw new JoulesException(" Please add a description about what the deadline is!");
                        }
                        if (split.length == 2 || split[2].trim().isEmpty()) {
                            throw new JoulesException(" Please add a deadline with /by!");
                        }
                        try {
                            LocalDate date = LocalDate.parse(split[2]);
                            t = new Deadline(split[1].trim(), date);
                        } catch (DateTimeParseException e) {
                            throw new JoulesException(" Invalid date format or value: " + e.getMessage());
                        }
                    } else if (commands[0].equals("event")) {
                        split = input.split("event |/from |/to ");
                        if (split.length == 1 || split[1].trim().isEmpty()) {
                            throw new JoulesException(" Please add a description about what the event is!");
                        }
                        if (split.length <= 3 || split[2].trim().isEmpty() || split[3].trim().isEmpty()) {
                            throw new JoulesException(" Please ensure you have a /from and /to date or time!");
                        }
                        t = new Event(split[1].trim(), split[2].trim(), split[3]);
                    } else {
                        throw new JoulesException(" Sorry, I do not understand :<");
                    }
                    history.add(t);
                    t.store();
                    System.out.println(" Added this task:");
                    System.out.println("   " + t);
                    System.out.println(" You now have " + history.size() + " task(s)");
                } catch (JoulesException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}