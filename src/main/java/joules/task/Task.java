package joules.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
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

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    public String storeString() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    public abstract void store();
}