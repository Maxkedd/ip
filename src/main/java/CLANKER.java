import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class CLANKER {

    public static void main(String[] args) {
        // Init
        List<ToDo> tasks = new ArrayList<>();
        Greeting greeting = new Greeting();
        Command command = new Command(tasks);
        Scanner in = new Scanner(System.in);

        // Greet
        greeting.greetUser();

        // userInput
        String userInput = in.nextLine();
        while (true) {
            if (userInput.trim().isEmpty()) {
                userInput = in.nextLine();
                continue;
            }

            if (command.processCommand(userInput)) {
                return;
            }
            userInput = in.nextLine();
        }
    }
}
