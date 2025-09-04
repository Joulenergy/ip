package joules.task;

/**
 * Represents a generic task with a description and completion status.
 * A {@code Task} is the base class for all types of tasks (e.g., {@link Todo},
 * {@link Deadline}, {@link Event}) and provides common functionality such
 * as marking as done, unmarking, and returning a string representation.
 */
public abstract class Task {
    /** Description of the task */
    protected String description;

    /** Completion status of the task; true if done, false otherwise */
    protected boolean isDone;

    /**
     * Constructs a task with the specified description.
     * The task is initially not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "X" if the task is done, otherwise a single space " ".
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * <p>
     * The string includes the task's status (done or not) and description.
     * Example format: "[X] Finish homework" or "[ ] Read book".
     * </p>
     *
     * @return String representing the task's status and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Checks if the task description contains the given keyword, case-insensitive.
     *
     * @param keyword The keyword to search for in the description.
     * @return true If the keyword appears in the description; false otherwise.
     */
    public boolean matchDescription(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns a string suitable for storage in the persistent file.
     * <p>
     * The format includes completion status and description, separated by " | ".
     * Example format: "1 | Finish homework" or "0 | Read book".
     * </p>
     *
     * @return String representing the task for storage.
     */
    public String storeString() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Stores the task in the persistent storage.
     * Subclasses must implement this method to define how they are stored.
     */
    public abstract void store();
}
