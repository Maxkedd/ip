import java.util.ArrayList;
import Task.ToDo;

/** Owns the tasks and provides operations for managing them. */
public class TasksList{
    private final ArrayList<ToDo> tasks = new ArrayList<>();

    public void add(ToDo task) {
        tasks.add(task);
    }

    public ToDo get(int index) {
        return tasks.get(index);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ToDo getLast() {
        return tasks.getLast();
    }
}