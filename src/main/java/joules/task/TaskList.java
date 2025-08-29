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
        this.tasks.add(t);
    }

    /**
     * Removes a task from the task list at the specified position.
     *
     * @param num The 1-based index of the task to remove.
     */
    public void removeTask(int num) {
        this.tasks.remove(num - 1);
    }

    /**
     * Retrieves the task at the specified position in the task list.
     *
     * @param num The 1-based index of the task to retrieve.
     * @return The {@link Task} at the specified position.
     */
    public Task getTask(int num) {
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
     * Prints all tasks in the task list to the console.
     * Each task is prefixed with its 1-based index.
     */
    public void printTaskList() {
        for (int i = 1; i <= taskCount(); i++) {
            System.out.printf(" %d.%s%n", i , getTask(i));
        }
    }
}
