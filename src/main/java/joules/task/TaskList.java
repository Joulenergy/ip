package joules.task;

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

    public void printTaskList() {
        for (int i = 1; i <= taskCount(); i++) {
            System.out.printf(" %d.%s%n", i , getTask(i));
        }
    }

    public void printMatchingTaskList(String keyword) {
        int found = 0;
        for (int i = 1; i <= taskCount(); i++) {
            Task t = getTask(i);
            if (t.matchDescription(keyword)) {
                found += 1;
                System.out.printf(" %d.%s%n", found , t);
            }
        }
        if (found == 0) {
            System.out.println(" None found");
        }
    }
}
