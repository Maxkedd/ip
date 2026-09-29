import java.io.IOException;

import Exceptions.InvalidCommandException;
import Task.ToDo;

/** Handles user commands and records successful task changes for replay. */
public class Ui {

    private final TasksList tasks;
    private final Parser parser = new Parser();
    private final Storage storage = new Storage();

    private static final String GOODBYE = "Bye! See you soon";
    private static final String LINE_BREAK = "─".repeat(60);

    /** Uses the same task list that startup restores and later commands update. */
    public Ui(TasksList tasks) {
        this.tasks = tasks;
    }

    /** Processes new user input and records successful task changes. */
    public boolean processCommand(String userInput) {
        return processCommand(userInput, true);
    }

    /** Processes a command, recording it in the command history to print upon restarting. */
    public boolean processCommand(String userInput, boolean recordHistory) {
        boolean tasksChanged = false;
        try {
            System.out.println(LINE_BREAK);
            String command = parser.parseCommand(userInput);

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

                tasks.List();
                break;

            case "mark", "unmark", "delete":
                int target = parser.parseTaskNumber(userInput);
                tasks.targetingCommand(command, target);
                tasksChanged = true;
                break;

            case "todo", "deadline", "event":
                tasks.add(parser.parseTask(userInput));
                tasksChanged = true;
                System.out.println("Task added: ");
                tasks.getLast().printResponse();
                System.out.printf("You have %d tasks added to list\n", tasks.size());
                break;

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
