package joules.contact;

import joules.Store;

/**
 * Represents a contact saved by the user.
 * <p>
 * A contact consists of a name and a phone number in the format
 * {@code +<country code><number>}. Contacts can be stored persistently
 * and matched against keywords for searching.
 * </p>
 */
public class Contact {
    /** The name of the contact */
    private String name;

    /** The contact number in international format (+country code + number) */
    private String contact; // +<country code><number>

    /**
     * Constructs a contact with the specified name and number.
     *
     * @param name The contact's name.
     * @param contact The contact number in {@code +<country code><number>} format.
     */
    public Contact(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    /**
     * Stores this contact persistently.
     * <p>
     * The contact is serialized as a string in the format
     * {@code <name> | <contact>} and passed to {@link Store#storeContact(String)}.
     * </p>
     */
    public void store() {
        String storeString = this.name + " | " + this.contact;
        Store.storeContact(storeString);
    }

    /**
     * Returns a string representation of the contact.
     * <p>
     * The format is {@code <name>: <contact>}.
     * </p>
     *
     * @return A string describing the contact.
     */
    @Override
    public String toString() {
        return this.name + ": " + this.contact;
    }

    /**
     * Determines whether this contact matches a given search keyword.
     * <p>
     * A match occurs if the keyword (case-insensitive) is contained
     * in the contact's name or if it appears in the contact number.
     * </p>
     *
     * @param keyword The search keyword to match against.
     * @return {@code true} if the contact name or number contains the keyword,
     *         {@code false} otherwise.
     */
    public boolean matchContact(String keyword) {
        keyword = keyword.toLowerCase();
        return this.name.toLowerCase().contains(keyword)
                || this.contact.contains(keyword);
    }
}




