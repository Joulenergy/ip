package joules.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testStringConversion() {
        Todo todo = new Todo("ip commits");
        assertEquals("[T][ ] ip commits", todo.toString());
    }
}
