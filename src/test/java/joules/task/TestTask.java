package joules.task;

/**
 * A simple test implementation of {@link Task} used for testing purposes.
 * <p>
 * This class overrides the {@link #store()} method but does not implement any storage logic.
 */
public class TestTask extends Task {
    /**
     * Constructs a {@code TestTask} with the given description.
     *
     * @param description the description of the task
     */
    public TestTask(String description) {
        super(description);
    }

    /**
     * Stores the task.
     * <p>
     * For {@code TestTask}, this method is intentionally left empty.
     */
    @Override
    public void store() {
    }
}
