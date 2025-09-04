package joules.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import joules.Store;

/**
 * Represents a deadline task with a specific due date.
 * A {@code Deadline} extends {@link Task} and adds a {@link LocalDate}
 * field for the due date. It can be stored and displayed with its
 * formatted date.
 */
public class Deadline extends Task {
    /** Due date of the deadline task */
    protected LocalDate by;

    /**
     * Constructs a {@code Deadline} with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by The due date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task,
     * including its type, status, description, and formatted due date.
     *
     * @return Formatted string of the deadline task.
     */
    @Override
    public String toString() {
        // Format date
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = this.by.format(displayFormat);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    /**
     * Stores the deadline task in the persistent storage.
     * The task is stored in the format:
     * <pre>
     * D | isDone | description | dueDate
     * </pre>
     */
    @Override
    public void store() {
        String storeString = "D | " + super.storeString() + " | " + this.by;
        Store.storeTask(storeString);
    }
}
