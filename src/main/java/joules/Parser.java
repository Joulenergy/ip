package joules;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String keyword = "";
        if (input.startsWith("findc")) {
            keyword = input.substring("findc".length()).trim();
        } else if (input.startsWith("find")) {
            keyword = input.substring("find".length()).trim();
        } else {
            throw new JoulesException(" Unknown command! Should be find or findc");
        }
        if (keyword.isEmpty()) {
            throw new JoulesException(" Please add a keyword about what you want to find!");
        }
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
        // Normalize whitespace to prevent too many splits
        String[] commands = input.trim().replaceAll("\\s+", " ").split(" ");
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
        final String prefix = "todo";
        if (!input.startsWith(prefix)) {
            throw new JoulesException(" Input must start with 'todo'.");
        }

        String description = input.substring(prefix.length()).trim();
        if (description.isEmpty()) {
            throw new JoulesException(" Please add a description about what the todo is!");
        }

        assert !description.isEmpty() : "Todo description should not be empty after validation";
        return description;
    }

    /**
     * Parses the description and deadline date of a deadline task from a user input command.
     *
     * @param input The full user input string (e.g., "deadline submit report /by 2025-09-30").
     * @return An array where the first element is the description and the second is the deadline date.
     * @throws JoulesException If the description or date is missing or invalid.
     */
    public static String[] parseDeadline(String input) throws JoulesException {
        // Copilot AI was used to generate recommendations to improve string parsing.
        Pattern pattern = Pattern.compile("deadline\\s+(.*?)\\s+/by\\s+(\\d{4}-\\d{2}-\\d{2})");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            throw new JoulesException(" Please ensure your input follows the format: "
                    + "deadline <description> /by <yyyy-MM-dd>");
        }

        String description = matcher.group(1).trim();
        String dateStr = matcher.group(2).trim();

        if (description.isEmpty()) {
            throw new JoulesException(" Please add a description about what the deadline is!");
        }

        try {
            LocalDate date = LocalDate.parse(dateStr);
            assert date != null : "Parsed deadline date should not be null";
            return new String[] { description, dateStr };
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
        Pattern pattern = Pattern.compile("event\\s+(.*?)\\s+/from\\s+(\\d{4}-\\d{2}-\\d{2})\\s+"
                + "/to\\s+(\\d{4}-\\d{2}-\\d{2})");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            throw new JoulesException(" Please ensure your input follows the format: "
                    + "event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>");
        }

        String description = matcher.group(1).trim();
        String fromDateStr = matcher.group(2).trim();
        String toDateStr = matcher.group(3).trim();

        if (description.isEmpty()) {
            throw new JoulesException(" Please add an event description!");
        }

        try {
            LocalDate fromDate = LocalDate.parse(fromDateStr);
            LocalDate toDate = LocalDate.parse(toDateStr);
            assert fromDate != null && toDate != null : "Parsed event dates should not be null";
            return new String[] { description, fromDateStr, toDateStr };
        } catch (DateTimeParseException e) {
            throw new JoulesException(" Invalid date format or value, use yyyy-MM-dd ");
        }
    }

    public static String[] parseContact(String input) throws JoulesException {
        Pattern pattern = Pattern.compile("addc\\s+(.*?)\\s+(\\+\\d{8,15})");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.find()) {
            throw new JoulesException(" Please ensure your input follows the format: "
                    + "addc <name> +<country code><number>");
        }

        String name = matcher.group(1);
        if (name.isEmpty()) {
            throw new JoulesException(" Please add a contact name!");
        }

        return new String[] { name, matcher.group(2).trim() };
    }
}
