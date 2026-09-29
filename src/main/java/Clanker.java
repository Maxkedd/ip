import java.io.IOException;
import java.util.Scanner;

/** Restores saved tasks and runs the interactive command loop. */
public class Clanker {

    /** Replays command history before accepting new user input. */
    public static void main(String[] args) {
        TasksList tasks = new TasksList();
        Greeting greeting = new Greeting();
        Ui command = new Ui(tasks);
        Storage storage = new Storage();

        try {
            // Order matters: task numbers in mark/delete refer to earlier commands.
            for (String savedCommand : storage.loadCommands()) {
                if (!savedCommand.isBlank()) {
                    command.processCommand(savedCommand, false);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read saved commands: " + e.getMessage());
            // Stop rather than append new commands to a history we could not restore.
            return;
        }

        try (Scanner in = new Scanner(System.in)) {
            greeting.greetUser();
            command.processCommand("list");

            while (in.hasNextLine()) {
                String userInput = in.nextLine();

                if (userInput.isBlank()) {
                    continue;
                }

                if (command.processCommand(userInput)) {
                    break;
                }
            }
        }
    }
}
