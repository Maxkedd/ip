import java.io.IOException;

import Exceptions.InvalidCommandException;
import Task.Deadline;
import Task.Event;
import Task.ToDo;

/** Handles user commands and records successful task changes for replay. */
public class Ui {

    private final TasksList tasks;
    private final Storage storage = new Storage();

    private static final String GOODBYE = "Bye! See you soon";
    private static final String LINE_BREAK = "─".repeat(60);

    /** Uses the same task list that startup restores and later commands update. */
    public Ui(TasksList tasks) {
        this.tasks = tasks;
    }

    /** Splits once so spaces within a task description are preserved. */
    private String[] separateInput(String input) {
        String cleanedInput = input.strip();
        return cleanedInput.split("\\s+", 2);
    }

    /** Processes new user input and records successful task changes. */
    public boolean processCommand(String userInput) {
        return processCommand(userInput, true);
    }

    /** Processes a command, recording it in the command history to print upon restarting. */
    public boolean processCommand(String userInput, boolean recordHistory) {
        boolean tasksChanged = false;
        try {
            String[] separatedInput = separateInput(userInput);
            String command = separatedInput[0].toLowerCase();
            String originalCommand = separatedInput[0];
            System.out.println(LINE_BREAK);

            switch (command) {
            case "bye":
                System.out.println(GOODBYE);
                return true;

            case "help":
                System.out.println(HelpText.COMMAND_LIST);
                break;

            case "list":
                if (tasks.isEmpty()) {
                    System.out.println("Add tasks first");
                    break;
                }

                System.out.println("Here is your list: ");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. ", i + 1);
                    tasks.get(i).printResponse();
                }
                break;

            case "mark", "unmark", "delete":
                if (separatedInput.length != 2) {
                    throw new InvalidCommandException("Please provide a task number!");
                }
                int target;
                try {
                    target = Integer.parseInt(separatedInput[1].trim());
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException("Error: 'mark' requires a valid number!");
                }

                if (target <= 0 || target > tasks.size()) {
                    System.out.println("Error: out of bounds!");
                    break;
                }

                // The displayed task numbers start at 1; list indexes start at 0.
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

                tasksChanged = true;
                break;

            case "todo", "deadline", "event":
                if (separatedInput.length < 2 || separatedInput[1].trim().isEmpty()) {
                    throw new InvalidCommandException("Error! Please use the right format!");
                }
                String taskToAdd = separatedInput[1].trim();
                switch (command) {
                case "todo":
                    tasks.add(new ToDo(taskToAdd));
                    break;

                case "deadline": {
                    int idxBy = taskToAdd.indexOf("/");

                    if (idxBy == -1) {
                        throw new InvalidCommandException("Error! Try this format: deadline task /by date");
                    }
                    String description = taskToAdd.substring(0, idxBy).trim();
                    String due = taskToAdd.substring(idxBy + 1).trim();

                    if (description.isEmpty() || due.isEmpty()) {
                        throw new InvalidCommandException("Error! Please provide valid task and due date!");
                    }
                    tasks.add(new Deadline(description, due));
                    break;
                }

                case "event": {
                    String[] segments = taskToAdd.split("/", 3);

                    if (segments.length < 3) {
                        throw new InvalidCommandException("Error! Try this format: event task /from date /to date");
                    }
                    String description = segments[0].trim();
                    String start = segments[1].trim();
                    String end = segments[2].trim();

                    if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
                        throw new InvalidCommandException("Error! Please provide valid task and start and end dates!");
                    }
                    tasks.add(new Event(description, start, end));
                    break;
                }
                }
                tasksChanged = true;
                System.out.println("Task added: ");
                tasks.getLast().printResponse();
                System.out.printf("You have %d tasks added to list\n", tasks.size());
                break;

            default:
                throw new InvalidCommandException(originalCommand + " is not a valid command! -_-");
            }

            // invalid commands and processing saved commands must not become part of the saved history.
            if (recordHistory && tasksChanged) {
                storage.appendCommand(userInput);
            }

        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage() + HelpText.COMMAND_LIST);
        } catch (IOException e) {
            System.out.println("Could not save tasks. Your changes are only in memory.");
        }

        System.out.println(LINE_BREAK);
        return false;
    }
}
