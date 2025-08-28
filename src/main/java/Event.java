import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedFrom = this.from.format(displayFormat);
        String formattedTo = this.to.format(displayFormat);
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    @Override
    public void store() {
        String storeString = "E | " + super.storeString() + " | " + this.from + " | " + this.to;
        Store.storeTask(storeString);
    }
}
