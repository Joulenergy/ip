package joules;

import joules.contact.Contact;
import joules.contact.ContactList;
import joules.task.Task;
import joules.task.TaskList;

/**
 * Handles user interactions for the Joules chatbot.
 * <p>
 * This class provides methods to create welcome, goodbye task update,
 * and error messages.
 * </p>
 */
public class Ui {
    /**
     * Returns the welcome message with ASCII art when the chatbot starts.
     *
     * @return A formatted welcome message string.
     */
    public String getWelcomeMessage() {
        return " Hello! I'm Joules!\n"
                + "     .-./`)     ,-----.      ___    _   .---.       .-''-.     .-'''-.  \n"
                + "     \\ '_ .') .'  .-,  '.  .'   |  | |  | ,_|     .'_ _   \\   / _     \\ \n"
                + "    (_ (_) _)/ ,-.|  \\ _ \\ |   .'  | |,-./  )    / ( ` )   ' (`' )/`--' \n"
                + "      / .  \\;  \\  '_ /  | :.'  '_  | |\\  '_ '`) . (_ o _)  |(_ o _).    \n"
                + " ___  |-'`| |  _`,/ \\ _/  |'   ( \\.-.| > (_)  ) |  (_,_)___| (_,_). '.  \n"
                + "|   | |   ' : (  '\\_/ \\   ;' (`. _` /|(  .  .-' '  \\   .---..---.  \\  : \n"
                + "|   `-'  /   \\ `\"/  \\  ) / | (_ (_) _) `-'`-'|___\\  `-'    /\\    `-'  | \n"
                + " \\      /     '. \\_/``\".'   \\ /  . \\ /  |        \\\\       /  \\       /  \n"
                + "  `-..-'        '-----'      ``-'`-''   `--------` `'-..-'    `-...-'   \n"
                + " What can I do for you?";
    }

    /**
     * Returns the goodbye message with ASCII art when the chatbot exits.
     *
     * @return A formatted goodbye message string.
     */
    public String getGoodbyeMessage() {
        return " _______      ____     __   .-''-.  .---.  \n"
                + "\\  ____  \\    \\   \\   /  /.'_ _   \\ \\   /  \n"
                + "| |    \\ |     \\  _. /  '/ ( ` )   '|   |  \n"
                + "| |____/ /      _( )_ .'. (_ o _)  | \\ /   \n"
                + "|   _ _ '.  ___(_ o _)' |  (_,_)___|  v    \n"
                + "|  ( ' )  \\|   |(_,_)'  '  \\   .---. _ _   \n"
                + "| (_{;}_) ||   `-'  /    \\  `-'    /(_I_)  \n"
                + "|  (_,_)  / \\      /      \\       /(_(=)_) \n"
                + "/_______.'   `-..-'        `'-..-'  (_I_)\n"
                + " Hope to see you again soon!";
    }

    /**
     * Returns a formatted string that lists all tasks in the provided {@link TaskList}.
     *
     * @param tasks The {@link TaskList} containing tasks to include.
     * @return A formatted string of all tasks.
     */
    public String getAllTasksMessage(TaskList tasks) {
        return " You got this! These are your tasks:\n"
                + tasks.getListString();
    }

    /**
     * Returns a message indicating that the specified task
     * has been marked as done.
     *
     * @param t The task that was marked.
     * @return The formatted message to display.
     */
    public String getMarkedTaskMessage(Task t) {
        return " Keep up the good work! I've marked this task as done:\n   " + t;
    }

    /**
     * Returns a message listing all tasks from the given {@link TaskList}
     * that contain the specified keyword in their description.
     *
     * @param keyword The string to search for in each task's description.
     * @param tasks The {@link TaskList} containing the tasks to search through.
     * @return A formatted string of matching tasks, or "None found" if no matches.
     */
    public String getMatchingTasksMessage(String keyword, TaskList tasks) {
        return " I have found these matching tasks:\n" + tasks.getMatchingTaskListString(keyword);
    }

    /**
     * Returns a message indicating that the specified task has been unmarked (not done).
     *
     * @param t The task that was unmarked.
     * @return The formatted message to display.
     */
    public String getUnmarkedTaskMessage(Task t) {
        return " Okay, I've marked this task as not done yet:\n   " + t;
    }

    /**
     * Returns a message indicating that the specified task has been deleted.
     *
     * @param t The task that was deleted.
     * @return The formatted message to display.
     */
    public String getDeletedTaskMessage(Task t) {
        return " Okay, I've removed this task:\n   " + t;
    }

    /**
     * Returns a message indicating that a task has been added and shows the
     * current number of tasks.
     *
     * @param t The task that was added.
     * @param totalTasks The total number of tasks after adding this task.
     * @return The formatted message to display.
     */
    public String getAddedTaskMessage(Task t, int totalTasks) {
        return " Added this task:\n   " + t
                + "\n You now have " + totalTasks + " task(s)";
    }

    /**
     * Returns an error message for display to the user.
     *
     * @param msg The error message to display.
     * @return The formatted error message.
     */
    public String getErrorMessage(String msg) {
        return " I'm sorry, something went wrong X.X\n" + msg;
    }

    public String unknownCommandMessage() {
        return " I do not understand your command ;<";
    }

    public String getAddedContactMessage(Contact c) {
        return " Added this contact:\n    " + c;
    }

    public String getAllContactsMessage(ContactList contacts) {
        return " Here are your contacts:\n"
                + contacts.getListString();
    }

    public String getMatchingContactsMessage(String keyword, ContactList contacts) {
        return " I have found these matching contacts:\n" + contacts.getMatchingContactListString(keyword);
    }

    public String getDeletedContactMessage(Contact c) {
        return " Okay, I've deleted this contact:\n   " + c;
    }
}
