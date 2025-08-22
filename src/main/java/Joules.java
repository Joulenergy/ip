import java.util.Scanner;

public class Joules {
    public static void main(String[] args) {
        System.out.println(" Hello! I'm Joules!\n" +
                " What can I do for you?");

        // Accept user input
        Scanner scanner = new Scanner(System.in);
        boolean bye = false;
        while (!bye) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                bye = true;
                System.out.println(" Bye. Hope to see you again soon!");
            } else {
                System.out.println(" " + input);
            }
        }
    }
}
