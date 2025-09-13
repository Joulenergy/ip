package joules.contact;

import joules.ItemList;

/**
 * Represents a list of {@link Contact} objects.
 * A {@code ContactList} extends the generic {@link ItemList} base class and
 * provides operations specific to managing contacts.
 */
public class ContactList extends ItemList<Contact> {
    /**
     * Constructs an empty {@code ContactList} with the specified initial capacity.
     *
     * @param size the initial capacity of the list
     */
    public ContactList(int size) {
        super(size);
    }

    /**
     * Returns a formatted string of all contacts whose details match the given keyword.
     * <p>
     * Each matching contact is numbered in order of appearance.
     * If no contacts match, the string {@code "None found"} is returned.
     * </p>
     *
     * @param keyword The keyword to search for in contact details.
     * @return A formatted string of matching contacts, or {@code "None found"} if none match.
     */
    public String getMatchingContactListString(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword for matching must be non-empty";
        return getMatchingListString(c -> c.matchContact(keyword));
    }
}
