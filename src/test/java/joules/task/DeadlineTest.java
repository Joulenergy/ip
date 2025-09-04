package joules.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        Deadline deadline = new Deadline("watch lecture", LocalDate.parse("2025-08-30"));
        assertEquals("[D][ ] watch lecture (by: Aug 30 2025)", deadline.toString());
    }
}
