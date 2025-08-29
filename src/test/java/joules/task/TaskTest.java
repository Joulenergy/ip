package joules.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testDefaultTaskStatus() {
        Task task = new TestTask("read book");
        assertEquals(" ", task.getStatusIcon(), "New task should not be marked done");
    }

    @Test
    public void testMark() {
        Task task = new TestTask("read book");
        task.mark();
        assertEquals("X", task.getStatusIcon(), "Task should be marked as done");
    }

    @Test
    public void testUnmark() {
        Task task = new TestTask("read book");
        task.mark();
        task.unmark();
        assertEquals(" ", task.getStatusIcon(), "Task should be unmarked");
    }
}
