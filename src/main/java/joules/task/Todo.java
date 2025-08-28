package joules.task;

import joules.Store;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void store() {
        String storeString = "T | " + super.storeString();
        Store.storeTask(storeString);
    }
}
