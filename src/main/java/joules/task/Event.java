package joules.task;

import joules.Store;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date.
 * An {@code Event} extends {@link Task} and includes {@link LocalDate}
 * fields for the start and end dates. It can be stored and displayed
 * with formatted dates.
 */
public class Event extends Task {
    /** Start date of the event */
    protected LocalDate from;

    /** End date of the event */
    protected LocalDate to;

    /**
     * Constructs an {@code Event} with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task,
     * including its type, status, description, and formatted start and end dates.
     *
     * @return Formatted string of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedFrom = this.from.format(displayFormat);
        String formattedTo = this.to.format(displayFormat);
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    /**
     * Stores the event task in the persistent storage.
     * The task is stored in the format:
     * <pre>
     * E | isDone | description | fromDate | toDate
     * </pre>
     */
    @Override
    public void store() {
        String storeString = "E | " + super.storeString() + " | " + this.from + " | " + this.to;
        Store.storeTask(storeString);
    }
}
