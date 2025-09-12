package joules.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manipulate them.
 * A {@code TaskList} stores tasks in an internal {@link ArrayList} and allows
 * adding, removing, retrieving, counting, and printing tasks.
 */
public class TaskList {
    /** Internal list storing the tasks */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a {@code TaskList} with an initial capacity.
     *
     * @param size the initial capacity of the task list.
     */
    public TaskList(int size) {
        this.tasks = new ArrayList<>(size);
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The {@link Task} to add.
     */
    public void addTask(Task t) {
        assert t != null : "Cannot add null task to TaskList";
        this.tasks.add(t);
    }

    /**
     * Removes a task from the task list at the specified position.
     *
     * @param num The 1-based index of the task to remove.
     */
    public void removeTask(int num) {
        assert num > 0 && num <= taskCount() : "Cannot remove a task not in list";
        this.tasks.remove(num - 1);
    }

    /**
     * Retrieves the task at the specified position in the task list.
     *
     * @param num The 1-based index of the task to retrieve.
     * @return The {@link Task} at the specified position.
     */
    public Task getTask(int num) {
        assert num > 0 && num <= taskCount() : "Task index out of bounds for retrieval: " + num;
        return this.tasks.get(num - 1);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The total number of tasks.
     */
    public int taskCount() {
        return this.tasks.size();
    }

    /**
     * Returns all tasks in the task list in a formatted string.
     * Each task is prefixed with its 1-based index.
     *
     * @return Formatted task list string or {@code "None found"}
     */
    public String getTaskListString() {
        assert taskCount() >= 0 : "Task count should never be negative";
        if (taskCount() == 0) {
            return "None found";
        }
        StringBuilder taskList = new StringBuilder();
        for (int i = 1; i <= taskCount(); i++) {
            taskList.append(String.format(" %d.%s%n", i, getTask(i)));
        }
        return String.valueOf(taskList);
    }

    /**
     * Returns a formatted string of all tasks in {@code TaskList}
     * whose descriptions contain the given keyword. Each matching task is
     * numbered in order of appearance. If no tasks match, the string
     * {@code "None found"} is returned.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A formatted string of matching tasks, or {@code "None found"} if none match.
     */
    public String getMatchingTaskListString(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword for matching must be non-empty";
        int found = 0;
        StringBuilder taskList = new StringBuilder();
        for (int i = 1; i <= taskCount(); i++) {
            Task t = getTask(i);
            assert t != null : "Task at index " + i + " should not be null";
            if (t.matchDescription(keyword)) {
                found += 1;
                taskList.append(String.format(" %d.%s%n", found, t));
            }
        }
        return found == 0 ? " None found" : String.valueOf(taskList);
    }
}
