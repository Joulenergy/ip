package joules;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
        String[] split = input.split("find |findc ");
        if (split.length == 1 || split[1].trim().isEmpty()) {
            throw new JoulesException(" Please add a keyword about what you want to find!");
        }
        String keyword = split[1].trim();
        assert !keyword.isEmpty() : "Keyword should not be empty after validation";
        return keyword;
    }

    /**
     * Parses a number that follows a user input command for example {@code mark}.
     *
     * @param input The full user input string (e.g., "mark 2").
     * @param items The list of items.
     * @return The parsed item number.
     * @throws JoulesException If the input is not a valid integer or
     *                         if the item number is out of range.
     */
    public static <T> int parseNumAfterCommand(String input, ItemList<T> items) throws JoulesException {
        String[] commands = input.split(" ");
        if (commands.length != 2) {
            throw new JoulesException(commands[0] + " is followed by one number only");
        }
        try {
            assert commands.length >= 2 : "Expected number after command";
            int num = Integer.parseInt(commands[1]);
            if (num > items.itemCount()) {
                throw new JoulesException(" There are only " + items.itemCount() + " items in the list!");
            } else if (num <= 0) {
                throw new JoulesException(" There is no list item with a negative number!");
            }
            assert num > 0 && num <= items.itemCount() : "Item number should be valid";
            return num;
        } catch (NumberFormatException e) {
            throw new JoulesException(" Please enter a valid item number!");
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

    public static String[] parseContact(String input) throws JoulesException {
        String[] split = input.split(" ");
        if (split.length == 1 || split[1].trim().isEmpty()) {
            throw new JoulesException(" Please add a contact name!");
        }
        if (split.length == 2 || split[2].trim().isEmpty()) {
            throw new JoulesException(" Please add a contact number!");
        }
        String contact = split[2].trim();
        if (!contact.matches("^\\+[0-9]{8,15}$")) {
            throw new JoulesException( "Please ensure your number is in the format +<country code><number>");
        }
        return new String[] { split[1].trim(), contact };
    }
}
