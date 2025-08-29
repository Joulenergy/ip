package joules;

import joules.task.Task;
import joules.task.TaskList;

import java.util.Scanner;

public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(" Hello! I'm Joules!");
        System.out.println("     .-./`)     ,-----.      ___    _   .---.       .-''-.     .-'''-.  \n" +
               "     \\ '_ .') .'  .-,  '.  .'   |  | |  | ,_|     .'_ _   \\   / _     \\ \n" +
               "    (_ (_) _)/ ,-.|  \\ _ \\ |   .'  | |,-./  )    / ( ` )   ' (`' )/`--' \n" +
               "      / .  \\;  \\  '_ /  | :.'  '_  | |\\  '_ '`) . (_ o _)  |(_ o _).    \n" +
               " ___  |-'`| |  _`,/ \\ _/  |'   ( \\.-.| > (_)  ) |  (_,_)___| (_,_). '.  \n" +
               "|   | |   ' : (  '\\_/ \\   ;' (`. _` /|(  .  .-' '  \\   .---..---.  \\  : \n" +
               "|   `-'  /   \\ `\"/  \\  ) / | (_ (_) _) `-'`-'|___\\  `-'    /\\    `-'  | \n" +
               " \\      /     '. \\_/``\".'   \\ /  . \\ /  |        \\\\       /  \\       /  \n" +
               "  `-..-'        '-----'      ``-'`-''   `--------` `'-..-'    `-...-'   \n" +
               "                                                                        ");
        System.out.println(" What can I do for you?");
    }

    public void showGoodbye() {
        System.out.println(" _______      ____     __   .-''-.  .---.  \n" +
               "\\  ____  \\    \\   \\   /  /.'_ _   \\ \\   /  \n" +
               "| |    \\ |     \\  _. /  '/ ( ` )   '|   |  \n" +
               "| |____/ /      _( )_ .'. (_ o _)  | \\ /   \n" +
               "|   _ _ '.  ___(_ o _)' |  (_,_)___|  v    \n" +
               "|  ( ' )  \\|   |(_,_)'  '  \\   .---. _ _   \n" +
               "| (_{;}_) ||   `-'  /    \\  `-'    /(_I_)  \n" +
               "|  (_,_)  / \\      /      \\       /(_(=)_) \n" +
               "/_______.'   `-..-'        `'-..-'  (_I_)  \n" +
               "                                           ");
        System.out.println(" Hope to see you again soon!");
    }

    public String readInput() {
        return Ui.SCANNER.nextLine();
    }

    public void listTasks(TaskList tasks) {
        System.out.println(" You got this! These are your tasks:");
        tasks.printTaskList();
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
