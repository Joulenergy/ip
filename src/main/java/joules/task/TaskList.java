package joules.task;

import joules.ItemList;

/**
 * Represents a list of {@link Task} objects.
 * <p>
 * A {@code TaskList} extends the generic {@link ItemList} base class and
 * provides operations specific to managing tasks.
 * </p>
 */
public class TaskList extends ItemList<Task> {
    /**
     * Constructs an empty {@code TaskList} with the specified initial capacity.
     *
     * @param size the initial capacity of the list
     */
    public TaskList(int size) {
        super(size);
    }

    /**
     * Returns a formatted string of all tasks whose descriptions contain the given keyword.
     * <p>
     * Each matching task is numbered in order of appearance.
     * If no tasks match, the string {@code "None found"} is returned.
     * </p>
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A formatted string of matching tasks, or {@code "None found"} if none match.
     */
    public String getMatchingTaskListString(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword for matching must be non-empty";
        return getMatchingListString(t -> t.matchDescription(keyword));
    }
}
