package joules;

import joules.task.Task;
import joules.task.TaskList;

import java.util.Scanner;

/**
 * Handles user interactions for the Joules chatbot.
 * <p>
 * This class provides methods to display welcome and goodbye messages,
 * show task updates, read user input, and display errors.
 * </p>
 */
public class Ui {
    /** Scanner to read input from the user */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Displays the welcome message and ASCII art when the chatbot starts.
     */
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

    /**
     * Displays the goodbye message and ASCII art when the chatbot exits.
     */
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

    /**
     * Reads a line of input from the user.
     *
     * @return The user's input as a {@link String}.
     */
    public String readInput() {
        return Ui.SCANNER.nextLine();
    }

    /**
     * Lists all tasks in the provided {@link TaskList}.
     *
     * @param tasks The {@link TaskList} containing tasks to display.
     */
    public void listTasks(TaskList tasks) {
        System.out.println(" You got this! These are your tasks:");
        tasks.printTaskList();
    }

    /**
     * Displays a message that the specified task has been marked as done.
     *
     * @param t The task that was marked.
     */
    public void markTask(Task t) {
        System.out.println(" Keep up the good work! I've marked this task as done:");
        System.out.println("   " + t);
    }

    /**
     * Displays a message that the specified task has been unmarked (not done).
     *
     * @param t The task that was unmarked.
     */
    public void unMarkTask(Task t) {
        System.out.println(" Okay, I've marked this task as not done yet:");
        System.out.println("   " + t);
    }

    /**
     * Displays a message that the specified task has been deleted.
     *
     * @param t The task that was deleted.
     */
    public void deleteTask(Task t) {
        System.out.println(" Okay, I've removed this task:");
        System.out.println("   " + t);
    }

    /**
     * Displays a message that a task has been added and shows the
     * current number of tasks.
     *
     * @param t The task that was added.
     * @param tasks The total number of tasks after adding this task.
     */
    public void addTask(Task t, int tasks) {
        System.out.println(" Added this task:");
        System.out.println("   " + t);
        System.out.println(" You now have " + tasks + " task(s)");
    }

    /**
     * Displays an error message to the user.
     *
     * @param msg The error message to display.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }
}
