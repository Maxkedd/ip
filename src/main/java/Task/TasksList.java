package Task;

import java.util.ArrayList;

import Exceptions.InvalidCommandException;

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

    /** Processes the mark, unmark or delete command */
    public void targetingCommand(String command, int target) {

        if (target <= 0 || target > tasks.size()) {
            throw new InvalidCommandException("Error: out of bounds!");
        }

        // The displayed task numbers start at 1, list indexes start at 0.
        ToDo selectedTask = tasks.get(target - 1);

        if (command.equals("mark")) {
            System.out.println("OK! Marked as done: ");
            selectedTask.setDone(true);
        } else if (command.equals("unmark")) {
            System.out.println("OK! Marked as not done: " + target);
            selectedTask.setDone(false);
        } else {
            System.out.println("OK! deleted task: " + target);
        }

        System.out.printf("[%s][%s] %s\n", selectedTask.getTaskIcon(),
                selectedTask.getStatusIcon(), selectedTask.getDescription());

        if (command.equals("delete")) {
            tasks.remove(target - 1);
        }
    }

    // List out current commands
    public void List() {
        System.out.println("Here is your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. ", i + 1);
            tasks.get(i).printResponse();
        }
    }
}
