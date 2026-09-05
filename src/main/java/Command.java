import java.util.List;

import Task.Deadline;
import Task.Event;
import Task.ToDo;

public class Command {

    private final List<ToDo> tasks;

    // Constants
    private static final String GOODBYE = "Bye! See you soon";
    private static final String LINE_BREAK = "─".repeat(60);

    public Command(List<ToDo> tasks) {
        this.tasks = tasks;
    }
    public boolean processCommand(String userInput) {
        try {
            String cleanedInput = userInput.strip();
            String[] splitInput = cleanedInput.split("\\s+", 2);
            String originalCommand = splitInput[0];
            String command = splitInput[0].toLowerCase().trim();
            String taskToAdd;
            System.out.println(LINE_BREAK);

            // Reusable temp variables
            ToDo tempToDo;
            String task;
            String start;
            String due;
            String end;

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
                    tempToDo = tasks.get(i);
                    System.out.printf("%d. ", i + 1);
                    tempToDo.printResponse();
                }
                break;

            case "mark", "unmark":
                if (splitInput.length != 2) {
                    throw new InvalidCommandException("Please provide a task number!");
                }
                int target;
                try {
                    target = Integer.parseInt(splitInput[1].trim());
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException("Error: 'mark' requires a valid number!");
                }

                if (target <= 0 || target > tasks.size()) {
                    System.out.println("Error: out of bounds!");
                    break;
                }

                if (command.equals("mark")) {
                    System.out.println("OK! Marked as done: ");
                    tasks.get(target - 1).setDone(true);
                } else {
                    System.out.println("OK! Marked as not done: " + target);
                    tasks.get(target - 1).setDone(false);
                }

                tempToDo = tasks.get(target - 1);
                System.out.printf("[%s][%s] %s\n", tempToDo.getTaskIcon(),
                        tempToDo.getStatusIcon(), tempToDo.getDescription());
                break;

            case "todo", "deadline", "event":
                if (splitInput.length < 2 || splitInput[1].trim().isEmpty()) {
                    throw new InvalidCommandException("Error! Please use the right format!");
                }
                taskToAdd = splitInput[1].trim();
                switch (command) {
                case "todo":
                    tasks.add(new ToDo(taskToAdd));
                    break;

                case "deadline":
                    int idxBy = taskToAdd.indexOf("/");

                    if (idxBy == -1) {
                        throw new InvalidCommandException("Error! Try this format: deadline task /by date");
                    }
                    task = taskToAdd.substring(0, idxBy).trim();
                    due = taskToAdd.substring(idxBy + 1).trim();

                    if(task.isEmpty() || due.isEmpty()) {
                        throw new InvalidCommandException("Error! Please provide valid task and due date!");
                    }
                    tasks.add(new Deadline(task, due));
                    break;

                case "event":
                    String[] segments = taskToAdd.split("/", 3);

                    if (segments.length < 3) {
                        throw new InvalidCommandException("Error! Try this format: event task /from date /to date");
                    }
                    task = segments[0].trim();
                    start = segments[1].trim();
                    end = segments[2].trim();

                    if(task.isEmpty() || start.isEmpty() || end.isEmpty()) {
                        throw new InvalidCommandException("Error! Please provide valid task and start and end dates!");
                    }
                    tasks.add(new Event(task, start, end));
                    break;
                }
                tempToDo = tasks.getLast();
                tempToDo.setDone(false);
                System.out.println("Task added: ");
                tempToDo.printResponse();
                System.out.printf("You have %d tasks added to list\n", tasks.size());
                break;

            default:
                throw new InvalidCommandException(originalCommand + " is not a valid command! -_-");
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage() + HelpText.COMMAND_LIST);
        }

        System.out.println(LINE_BREAK);
        return false;
    }
}
