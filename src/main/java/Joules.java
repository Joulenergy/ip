import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class Joules {
    public static void main(String[] args) {
        System.out.println(" Hello! I'm Joules!\n" +
                " What can I do for you?");

        // Store input
        Task[] history = new Task[100];
        int index = 0;

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
                for (int i = 0; i < index; i++) {
                    System.out.printf(" %d.%s%n", i + 1, history[i]);
                }
            } else if (commands.length == 2 && Set.of("mark", "unmark").contains(commands[0])){
                Task task = history[Integer.parseInt(commands[1]) - 1];
                if (commands[0].equals("mark")) {
                    task.mark();
                    System.out.println(" Keep up the good work! I've marked this task as done:");
                    System.out.println("   " + task);
                } else {
                    task.unmark();
                    System.out.println(" Okay, I've marked this task as not done yet::");
                    System.out.println("   " + task);
                }
            } else {
                history[index] = new Task(input);
                index += 1;
                System.out.println(" added: " + input);
            }
        }
    }
}
