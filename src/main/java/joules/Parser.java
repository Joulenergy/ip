package joules;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import joules.task.TaskList;

/**
 * Parses user input commands in the Joules chatbot.
 * <p>
 * Provides static methods to extract task numbers, descriptions, deadlines,
 * and event dates from user input strings.
 * </p>
 */
public class Parser {
    /**
     * Parses a "find" command to extract the keyword to search for.
     *
     * @param input The full user input starting with "find".
     * @return The keyword to search for, trimmed of leading/trailing spaces.
     * @throws JoulesException If no keyword is provided after "find".
     */
    public static String parseFind(String input) throws JoulesException {
        String[] split = input.split("find ");
        if (split.length == 1 || split[1].trim().isEmpty()) {
            throw new JoulesException(" Please add a keyword about what task you want to find!");
        }
        String keyword = split[1].trim();
        assert !keyword.isEmpty() : "Keyword should not be empty after validation";
        return keyword;
    }

    /**
     * Parses the task number from a user input command.
     *
     * @param input The full user input string (e.g., "mark 2").
     * @param tasks The current list of tasks.
     * @return The parsed task number.
     * @throws JoulesException If the input is not a valid integer or
     *                         if the task number is out of range.
     */
    public static int parseTaskNum(String input, TaskList tasks) throws JoulesException {
        String[] commands = input.split(" ");
        if (commands.length != 2) {
            throw new JoulesException("Mark, unmark and delete is followed by one task number only");
        }
        try {
            assert commands.length >= 2 : "Expected task number after command";
            int taskNum = Integer.parseInt(commands[1]);
            if (taskNum > tasks.taskCount()) {
                throw new JoulesException(" There are only " + tasks.taskCount() + " tasks!");
            } else if (taskNum <= 0) {
                throw new JoulesException(" There is no task with a negative number!");
            }
            assert taskNum > 0 && taskNum < tasks.taskCount() : "Task number should be valid";
            return taskNum;
        } catch (NumberFormatException e) {
            throw new JoulesException(" Please enter a valid task number!");
        }
    }

    /**
     * Parses the description of a todo task from a user input command.
     *
     * @param input The full user input string (e.g., "todo read book").
     * @return The todo description.
     * @throws JoulesException If the description is missing or empty.
     */
    public static String parseTodo(String input) throws JoulesException {
        String[] split = input.split("todo ");
        if (split.length == 1 || split[1].trim().isEmpty()) {
            throw new JoulesException(" Please add a description about what the todo is!");
        }
        assert !split[1].isEmpty() : "Todo description should not be empty after validation";
        return split[1];
    }

    /**
     * Parses the description and deadline date of a deadline task from a user input command.
     *
     * @param input The full user input string (e.g., "deadline submit report /by 2025-09-30").
     * @return An array where the first element is the description and the second is the deadline date.
     * @throws JoulesException If the description or date is missing or invalid.
     */
    public static String[] parseDeadline(String input) throws JoulesException {
        String[] split = input.split("deadline |/by");
        if (split.length == 1 || split[1].trim().isEmpty()) {
            throw new JoulesException(" Please add a description about what the deadline is!");
        }
        if (split.length == 2 || split[2].trim().isEmpty()) {
            throw new JoulesException(" Please add a deadline with /by!");
        }
        try {
            LocalDate date = LocalDate.parse(split[2].trim());
            assert date != null : "Parsed deadline date should not be null";
            return new String[] { split[1].trim(), split[2].trim() };
        } catch (DateTimeParseException e) {
            throw new JoulesException(" Invalid date format or value, use yyyy-MM-dd ");
        }
    }

    /**
     * Parses the description and start/end dates of an event task from a user input command.
     *
     * @param input The full user input string (e.g., "event meeting /from 2025-09-30 /to 2025-10-01").
     * @return An array where the first element is the description,
     *         the second is the start date, and the third is the end date.
     * @throws JoulesException If the description or dates are missing or invalid.
     */
    public static String[] parseEvent(String input) throws JoulesException {
        String[] split = input.split("event |/from |/to ");
        if (split.length == 1 || split[1].trim().isEmpty()) {
            throw new JoulesException(" Please add a description about what the event is!");
        }
        if (split.length <= 3 || split[2].trim().isEmpty() || split[3].trim().isEmpty()) {
            throw new JoulesException(" Please ensure you have a /from and /to date!");
        }
        try {
            LocalDate fromDate = LocalDate.parse(split[2].trim());
            LocalDate toDate = LocalDate.parse(split[3]);
            assert fromDate != null && toDate != null : "Parsed event dates should not be null";
            return new String[] { split[1].trim(), split[2].trim(), split[3] };
        } catch (DateTimeParseException e) {
            throw new JoulesException(" Invalid date format or value, use yyyy-MM-dd ");
        }
    }
}
