package joules.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion() {
        Todo todo = new Todo("ip commits");
        assertEquals("[T][ ] ip commits", todo.toString());
    }
}
