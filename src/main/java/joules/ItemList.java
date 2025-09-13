package joules;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Represents a list of items such as tasks or contacts that Joules stores
 * and provides methods to manipulate them.
 * A {@code ItemList} stores tasks in an internal {@link ArrayList} and allows
 * adding, removing, retrieving, counting, and printing tasks.
 */
public abstract class ItemList<T> {
    /** Internal list storing the items */
    private final ArrayList<T> items;

    /**
     * Constructs a {@code ItemList} with an initial capacity.
     *
     * @param size The initial capacity of the list.
     */
    public ItemList(int size) {
        this.items = new ArrayList<>(size);
    }

    /**
     * Adds an item to the item list.
     *
     * @param item The item to add.
     */
    public void add(T item) {
        assert item != null : "Cannot add null item to list";
        this.items.add(item);
    }

    /**
     * Removes a item from the item list at the specified position.
     *
     * @param num The 1-based index of the item to remove.
     */
    public void remove(int num) {
        assert num > 0 && num <= itemCount() : "Cannot remove an item not in list";
        this.items.remove(num - 1);
    }

    /**
     * Retrieves the item at the specified position in the item list.
     *
     * @param num The 1-based index of the item to retrieve.
     * @return The item at the specified position.
     */
    public T get(int num) {
        assert num > 0 && num <= itemCount() : "Task index out of bounds for retrieval: " + num;
        return this.items.get(num - 1);
    }

    /**
     * Returns the number of items in the item list.
     *
     * @return The total number of items.
     */
    public int itemCount() {
        return this.items.size();
    }

    /**
     * Returns all items in the list in a formatted string.
     * Each item is prefixed with its 1-based index.
     *
     * @return Formatted item list string or {@code "None found"}
     */
    public String getListString() {
        assert itemCount() >= 0 : "Item count should never be negative";
        if (itemCount() == 0) {
            return "None found";
        }
        StringBuilder itemList = new StringBuilder();
        for (int i = 1; i <= itemCount(); i++) {
            itemList.append(String.format(" %d.%s%n", i, get(i)));
        }
        return String.valueOf(itemList);
    }

    /**
     * Returns a formatted string of all items in this {@code ItemList}
     * that satisfy the given predicate. Each matching item is numbered
     * in order of appearance. If no items match, the string
     * {@code "None found"} is returned.
     *
     * @param matcher A {@link Predicate} used to test whether an item
     *                should be included in the results.
     * @return A formatted string of matching items, or {@code "None found"}
     *         if none match.
     */
    public String getMatchingListString(Predicate<T> matcher) {
        int found = 0;
        StringBuilder itemList = new StringBuilder();
        for (int i = 1; i <= itemCount(); i++) {
            T t = get(i);
            assert t != null : "Item at index " + i + " should not be null";
            if (matcher.test(t)) {
                found += 1;
                itemList.append(String.format(" %d.%s%n", found, t));
            }
        }
        return found == 0 ? " None found" : String.valueOf(itemList);
    }
}
