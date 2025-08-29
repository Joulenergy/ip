package joules.task;

import joules.Store;

/**
 * Represents a todo task with a description and completion status.
 * A {@code Todo} extends {@link Task} and does not have a date associated
 * with it. It can be stored and displayed in a formatted way.
 */
public class Todo extends Task {
    /**
     * Constructs a {@code Todo} with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task,
     * including its type, status, and description.
     *
     * @return Formatted string of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Stores the todo task in the persistent storage.
     * The task is stored in the format:
     * <pre>
     * T | isDone | description
     * </pre>
     */
    @Override
    public void store() {
        String storeString = "T | " + super.storeString();
        Store.storeTask(storeString);
    }
}
