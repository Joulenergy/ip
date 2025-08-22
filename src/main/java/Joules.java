import java.util.Scanner;

public class Joules {
    public static void main(String[] args) {
        System.out.println(" Hello! I'm Joules!\n" +
                " What can I do for you?");

        // Store input
        String[] history = new String[100];
        int index = 0;

        // Accept user input
        Scanner scanner = new Scanner(System.in);
        boolean bye = false;
        while (!bye) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                bye = true;
                System.out.println(" Bye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.printf(" %d. %s%n", i + 1, history[i]);
                }
            } else {
                history[index] = input;
                index += 1;
                System.out.println(" added: " + input);
            }
        }
    }
}
