import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        // Format date
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = this.by.format(displayFormat);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    @Override
    public void store() {
        String storeString = "D | " + super.storeString() + " | " + this.by;
        Store.storeTask(storeString);
    }
}
