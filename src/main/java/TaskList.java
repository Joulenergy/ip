import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(int size) {
        this.tasks = new ArrayList<>(100);
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void removeTask(int num) {
        this.tasks.remove(num - 1);
    }

    public Task getTask(int num) {
        return this.tasks.get(num - 1);
    }

    public int taskCount() {
        return this.tasks.size();
    }
}
