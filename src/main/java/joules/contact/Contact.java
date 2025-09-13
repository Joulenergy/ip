package joules.contact;

import joules.Store;

public class Contact {
    private String name;
    private String contact; // +<country code><number>

    public Contact(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public void store() {
        String storeString = this.name + " | " + this.contact;
        Store.storeContact(storeString);
    }

    @Override
    public String toString() {
        return this.name + ": " + this.contact;
    }

    public boolean matchContact(String keyword) {
        keyword = keyword.toLowerCase();
        return this.name.toLowerCase().contains(keyword)
                || this.contact.contains(keyword);
    }
}




