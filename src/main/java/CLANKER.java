import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CLANKER {
    public static void main(String[] args) {
        // Read Input
        Scanner in = new Scanner(System.in);

        // Constants
        List<ToDo> tasks = new ArrayList<>();
        String LINE_BREAK = "─".repeat(60);
        String BANNER = """
        .:...:;;;;;;;:::;;;;....:;;+xx++++;;:..............::::::.........................................::.....
        :::::::;;;;+;::::;::;x$$XXXXxx++;;::...   .   ......:::::.........................................::::...
        :::.:::;;;;;;;..;x$&$XXXXxx+++;;;:....             ..::::.....................................:::::::::..
        :::.:::::;;;;;X$&&$$XXXxx++++;;;:.....               ......................................:::..::::::::.
        :::;;::::::;X&&&$$XXXxx+++++;;;::...                .    .;;;;::...........:;:....:........:....::::::;::
        ::::::::::x&&&&$$XXXXxx+++;;;;;:...                     ;x;++;;::;:.............:........::....::::::::::
        ::.:::::;X&&&&$$XXXXx++++;;;;::...                      x+xXx;;;;;;;..   . ... ...........:........::::;;
        :...:::;$&&&&$$XXXXx++++;;;;;:...                       X+xXx;;::::::.                     . .. ......:..
        ......;&&&&&&$$XXXXx++;+;;;;::...                       :x++;;;:..:::. .  . .                   ..:;;.. \s
        .....;$&&&&&$XXXXXxx++++;;;;::..                        :&X;;;:........   .                       ... ...
        .....X&&&&&&&XXXXXXx++++;;;;:....                       +$$x;;;;:.....    ..                           ..
        ....;&&&&&&&&$$XXXXx++++;;;;::..                         .........      .                            .  \s
        ....X&&&&&&&&&$$XXXxx++++;;;::..                                .      .    .                        .. \s
        ...;&&&&&&&&&&$$XXXXx++++;;;:....                              .  .  .   .                           .. \s
        ...X&&&&&&&&&&$$$XXXxx++;;;;:....                             .  .  .  .     . .. . .. .              . \s
        ...$&&&&&&&&&&&$$XXXxxx++;;;:......:                            .  .   . .  .    .    . . .  .        . \s
        ;:.$&&&&&&&&&&&$$XXXXxx++;;;:.:;++..                        .  .  .     ..  . .  . .  .  .  .   ..    . \s
        ;;;$&&&&&&&&&&$$$XXXXxx+;;;;;xx;;;;...                    .  .  .             . .  . .  .  .  .   .   . \s
        ;;;X&&&&&&&&&&$$$XXXXxx+;;xXX;;x;.;+x+.                      .  .  .  .   .  .  .   .  .  .  . .  .     \s
        ;;;x&&&&&&&&&&&$$$XXXxxxxXX++x;:X$Xx;..                   . .  .  .  .  .   ...  ..  .  .  .   . .      \s
        :;;;&&&&&&&&&&$$$XXXXX$XXxxXx;X&&X+..                   .  .  .  .  .  ..  ..  .   .  .  .  . .  .      \s
        :...X&&&&&&&&&$$XX$$&$XXXXX++&&&X:.                    . .  .  .  .  .   .  ..  .  .  .  .  .   .       \s
        ....;&&&&&&&&&&&&&&&$$$$Xxx&&&&x..                       .  .  .  .  . .  ... .  .  .  .  .  . .        \s
        :;;;x&&&&&&&&&&$&&&&&$$XX&&&&$;...     ......        .. .    .. ..  .  .  ....  . .  .  .  .  .         \s
        :;;;;&&&&&&&&&&&&&&&&&&&&&&&$;..   ............  ...  . . ...  ..  .  .  . ..........  . .  .           \s
        ::::;&&&&&&&&&&&&$&&&&&&&&$X;..  ............  ...........  .. .... ..  .  ...........   .  .           \s
        .....X&&&&&&&&&&&&&&&&&&&X;:.. ...::::......     ......... . .....  .....  ...... ...  .  .  .          \s
        ....:$&&&&&&&&&&&&&&&&&$X;:.....:::::...          ..................  .  . ...... . ..  .  .            \s
        ....:&&&&&&&&&&&&&$$&$$X;;....:::::......      . ....:.............. . ..  .......  .  . .  .           \s
        ....;$&&&&&&&&&&&&$xx+++;;;:.......  ..::;;:::  ...::::::.............  ..    .....   .  .  .           \s
        ;;:::;X$$$$$$$$$$$X+;;;;;;;;:..........:::......::::::::::............  ....     . . .  .  .            \s
        ;;;:::::;;+XXXXXx++;;;;;;;;;;:.................::::;:;;::::............. ..  .  .   .  .  .             \s
        ::::+$X;.............::;;;;;;:.......  ........:::;;;;;;::::::............ ....   .  .  .  .            \s
        ::;$&$XXXX;;;:.....    ..;;;;::... .  . .........::;;;;;;::::::................ .  .  .  .              \s
        ;;$$XXXXXX$X;.;+xx;:.....:;;;;:..... ............::;;;;;;;;;:;;::::.............  . .  .  .             \s
        ;$$$$$XXXXX&&+;;++;;.:..:;;;;;;:::...... .. .. ...:;;;;;;;;;;;;;;::::........  ..   .  .                \s
        &$$X$X$$XXx&&&$X+;;;::::;$&&&$XXXXXx;:..   .  ...  ..:;;;;;;:::::::............  . .  .                 \s
        $XXXX$$$XXxX&&&&&&&$$$$&&&&&&&$XXXX+;:.... .....  ....::........................  .  .  .               \s
        $XX$X$$XXXXX$&&&&&&&&&&&&&&&&&$$XXx+;.... .....  ................................  .                    \s
        XXX$$$$XXXXX+X&&&&&&&&&$&&&&&&&$Xx+;:.... ..   ....:::.......  ..................  .                    \s
        &XX$$XXXXXXx++x&&&&&&&$+X&&&$XXx;;;;;:.....  ...::::........   ...................                      \s
        &$$&XXXXXXXxx++;X&&&&$x;;&&XXxx+;;;;;;::.....::;;:::::.....   .................... .                    \s
        $XXXXXXXxXXxx+;;;;$&&X+;;;XXxx+;;;;;;;;:::;;;;;;;;;::::..   ..  .................                       \s
        $$X$XXxxxXxXx++;;:..;x+;;;+++x+;;;;;;;;;;;;;;;;;;;;::::.   .....................                        \s
        $$$$$$xxXXXxx+;;:..     .;++x&&$XXXXX+;;;;;;;;;;;;;;:..  ......................                         \s
        $$XXXXxXXxX++++;;:.       .+$&&&&&&&&X;;;+xx++;;;;;;.  .......................                          \s
        $$$$XXxxxxXxx++;+:.         .+;x&&&&&&X;;xX$&&$X;:.   ........................                          \s
        $$$$XXxxxxXx++x;;::.           x$$&&&&&$xxXXXx;:.;+xx;:............:........                            \s
        $$$$XXxxxxx+++x;;:...           :$$Xx+;;;::.:;xXXXx;;::::::.:..:::::::::....                            \s
        $XXXXXxXxxx++++;;:...            :$&$XXXXXX$XXXXx+;;;;:::::::::::::::......                             \s
        &$$$$XXXxxxx+++;;:..              .X&&&&&$XXXxx;;;;;;;;;;;;;;;;;;;:::::..                               \s
        $$XXXX$XXXXx+++;;:..                 +$XXXx+;;;;;;;;;;;;;;;;;;;;;;;;::..                                \s
        XX$X$XXXXXXx+;+;;:..                    .;;;;;;;;;;;;;+++++++;;;;;;;:..                                 \s
        xXXXXXXXXXx+++;;:.....                       .;x++++++++++;+++;;;;:.                                    \s
        .x$XX$$$XXXxx+;;;....                           .+xx++++;+;;;;++;+x;..                                  \s
        .;XX$$$$XXXXxx+;;....  .                             ..;: .:.;:... .:;+;:..                             \s
        """;
        String GREETING = """
        I'm CLANKER.
        What shall I help you with today (^;?
        """;
        String GOODBYE = "Bye! See you soon";

        // Greet User
        System.out.println(LINE_BREAK);
        System.out.println(BANNER);
        System.out.println(GREETING);
        System.out.println(LINE_BREAK);

        // userInput
        String userInput = in.nextLine();
        while (true) {
            if (userInput.trim().isEmpty()) {
                userInput = in.nextLine();
                continue;
            }

            if (processCommand(userInput, tasks, LINE_BREAK, GOODBYE)) {
                return;
            }
            userInput = in.nextLine();
        }
    }

    /**
     * Processes one non-empty command and applies its changes to the task list.
     *
     * @param userInput command entered by the user
     * @param tasks task list to read or update
     * @param lineBreak separator printed around command output
     * @param goodbye message printed when the user exits
     * @return true when the command asks the application to exit
     */
    private static boolean processCommand(String userInput, List<ToDo> tasks,
                                          String lineBreak, String goodbye) {
        String[] splitInput = userInput.split(" ", 2);
        String command = splitInput[0].toLowerCase().trim();
        String taskToAdd;
        System.out.println(lineBreak);

        // Reusable temp variables
        ToDo tempToDo;
        String task;
        String from;
        String by;

        switch (command) {
        case "bye":
            System.out.println(goodbye);
            return true;

        case "list":
            if(tasks.size() <= 0) {
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
            int target;
            try {
                target = Integer.parseInt(splitInput[1]) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Error: 'mark' requires a valid number!");
                break;
            }

            if (target < 0 || target >= tasks.size()) {
                System.out.println("Error: out of bounds!");
                break;
            }

            if (command.equals("mark")) {
                System.out.println("OK! Marked as done: ");
                tasks.get(target).setDone(true);
            } else {
                System.out.println("OK! Marked as not done: " + target);
                tasks.get(target).setDone(false);
            }

            tempToDo = tasks.get(target);
            System.out.printf("[%s][%s] %s\n", tempToDo.getTaskIcon(), tempToDo.getStatusIcon(), tempToDo.getDescription());
            break;

        case "todo", "deadline", "event":
            taskToAdd = splitInput[1].toLowerCase().trim();
            switch (command) {
            case "todo":
                tasks.add(new ToDo(taskToAdd));
                break;

            case "deadline":
                int idxBy = taskToAdd.indexOf("/");
                if(idxBy == -1) {
                    System.out.println("Error! Try this format: deadline task /by date");
                    break;
                }
                task = taskToAdd.substring(0, idxBy);
                by = taskToAdd.substring(idxBy + 1);
                tasks.add(new Deadline(task, by));
                break;

            case "event":
                String[] segments = taskToAdd.split("/", 3);
                if(segments.length != 3) {
                    System.out.println("Error! Try this format: event task /from date /to date");
                }
                task = segments[0].trim();
                from = segments[1].trim();
                by = segments[2].trim();
                tasks.add(new Event(task, from, by));
                break;
            }
            tempToDo = tasks.getLast();
            tempToDo.setDone(false);
            System.out.println("Task added: ");
            tempToDo.printResponse();
            System.out.printf("You have %d tasks added to list\n", tasks.size());
            break;

        default:
            System.out.println("Command Invalid!");
            break;
        }

        System.out.println(lineBreak);
        return false;
    }
}
