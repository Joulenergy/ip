import java.util.Scanner;

public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(" Hello! I'm Joules!");
        System.out.println("""
                     .-./`)     ,-----.      ___    _   .---.       .-''-.     .-'''-. \s
                     \\ '_ .') .'  .-,  '.  .'   |  | |  | ,_|     .'_ _   \\   / _     \\\s
                    (_ (_) _)/ ,-.|  \\ _ \\ |   .'  | |,-./  )    / ( ` )   ' (`' )/`--'\s
                      / .  \\;  \\  '_ /  | :.'  '_  | |\\  '_ '`) . (_ o _)  |(_ o _).   \s
                 ___  |-'`| |  _`,/ \\ _/  |'   ( \\.-.| > (_)  ) |  (_,_)___| (_,_). '. \s
                |   | |   ' : (  '\\_/ \\   ;' (`. _` /|(  .  .-' '  \\   .---..---.  \\  :\s
                |   `-'  /   \\ `"/  \\  ) / | (_ (_) _) `-'`-'|___\\  `-'    /\\    `-'  |\s
                 \\      /     '. \\_/``".'   \\ /  . \\ /  |        \\\\       /  \\       / \s
                  `-..-'        '-----'      ``-'`-''   `--------` `'-..-'    `-...-'  \s
                                                                                       \s""");
        System.out.println(" What can I do for you?");
    }

    public void showGoodbye() {
        System.out.println("""
                 _______      ____     __   .-''-.  .---. \s
                \\  ____  \\    \\   \\   /  /.'_ _   \\ \\   / \s
                | |    \\ |     \\  _. /  '/ ( ` )   '|   | \s
                | |____/ /      _( )_ .'. (_ o _)  | \\ /  \s
                |   _ _ '.  ___(_ o _)' |  (_,_)___|  v   \s
                |  ( ' )  \\|   |(_,_)'  '  \\   .---. _ _  \s
                | (_{;}_) ||   `-'  /    \\  `-'    /(_I_) \s
                |  (_,_)  / \\      /      \\       /(_(=)_)\s
                /_______.'   `-..-'        `'-..-'  (_I_) \s
                                                          \s""");
        System.out.println(" Hope to see you again soon!");
    }

    public String readInput() {
        return Ui.SCANNER.nextLine();
    }

    public void listTasks(TaskList tasks) {
        System.out.println(" You got this! These are your tasks:");
        for (int i = 1; i <= tasks.taskCount(); i++) {
            System.out.printf(" %d.%s%n", i + 1, tasks.getTask(i));
        }
    }

    public void markTask(Task t) {
        System.out.println(" Keep up the good work! I've marked this task as done:");
        System.out.println("   " + t);
    }

    public void unMarkTask(Task t) {
        System.out.println(" Okay, I've marked this task as not done yet:");
        System.out.println("   " + t);
    }

    public void deleteTask(Task t) {
        System.out.println(" Okay, I've removed this task:");
        System.out.println("   " + t);
    }

    public void addTask(Task t, int tasks) {
        System.out.println(" Added this task:");
        System.out.println("   " + t);
        System.out.println(" You now have " + tasks + " task(s)");
    }

    public void showError(String msg) {
        System.out.println(msg);
    }
}
