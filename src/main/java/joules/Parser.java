package joules;

import joules.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static int parseTaskNum(String input, TaskList tasks) throws JoulesException {
        String[] commands = input.split(" ");
        try {
            int taskNum = Integer.parseInt(commands[1]);
            if (taskNum > tasks.taskCount()) {
                throw new JoulesException(" There are only " + tasks.taskCount() + " tasks!");
            }
            return taskNum;
        } catch (NumberFormatException e) {
            throw new JoulesException(" Please enter a valid task number!");
        }
    }

    public static String parseTodo(String input) throws JoulesException {
        String[] split = input.split("todo ");
        if (split.length == 1 || split[1].trim().isEmpty()) {
            throw new JoulesException(" Please add a description about what the todo is!");
        }
        return split[1];
    }

    public static String[] parseDeadline(String input) throws JoulesException {
        String[] split = input.split("deadline |/by ");
        if (split.length == 1 || split[1].trim().isEmpty()) {
            throw new JoulesException(" Please add a description about what the deadline is!");
        }
        if (split.length == 2 || split[2].trim().isEmpty()) {
            throw new JoulesException(" Please add a deadline with /by!");
        }
        try {
            LocalDate date = LocalDate.parse(split[2]);
            return new String[] { split[1].trim(), split[2] };
        } catch (DateTimeParseException e) {
            throw new JoulesException(" Invalid date format or value, use yyyy-MM-dd ");
        }
    }

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
            return new String[] { split[1].trim(), split[2].trim(), split[3] };
        } catch (DateTimeParseException e) {
            throw new JoulesException(" Invalid date format or value, use yyyy-MM-dd ");
        }
    }
}
