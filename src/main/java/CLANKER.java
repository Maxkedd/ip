import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Task.ToDo;

public class CLANKER {

    public static void main(String[] args) {
        // Init
        List<ToDo> tasks = new ArrayList<>();
        Greeting greeting = new Greeting();
        Command command = new Command(tasks);

        try (Scanner in = new Scanner(System.in)) {
            greeting.greetUser();

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
