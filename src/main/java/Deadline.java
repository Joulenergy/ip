import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public void store() {
        String storeString = "D | " + super.storeString() + " | " + this.by;
        Store.storeTask(storeString);
    }
}
