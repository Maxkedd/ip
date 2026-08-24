import java.util.Scanner;

public class CLANKER {
    public static void main(String[] args) {
        // Read Input
        String line;
        Scanner in = new Scanner(System.in);

        // Constants
        Task[] Tasks = new Task[101];
        int numTasks = 1;
        String seperator = "─".repeat(60);
        String banner = """
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
        String greeting = """
I'm CLANKER.
What shall I help you with today (^;?
""";
        String goodbye = "Bye! See you soon";

        // Greet User
        System.out.println(seperator);
        System.out.println(banner);
        System.out.println(greeting);
        System.out.println(seperator);

        // userInput
        String userInput = in.nextLine();
        while(true) {
            if(userInput.trim().isEmpty()) {
                userInput = in.nextLine();
                continue;
            }

            String[] splitInput = userInput.split(" ");
            String command = splitInput[0].toLowerCase();
            System.out.println(seperator);

            switch (command) {
                case "bye":
                    System.out.println(goodbye);
                    return;
                case "list":
                    System.out.println("Here is your list: ");
                    for(int i = 1; i < numTasks; i++) {
                        System.out.printf("%d.[%s] %s\n", i, Tasks[i].getStatusIcon(), Tasks[i].getDescription());
                    }
                    break;
                case "mark", "unmark":
                    int target;
                    try {
                        target = Integer.parseInt(splitInput[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: 'mark' requires a valid number!");
                        break;
                    }
                    if(target <= 0 || target >= numTasks) {
                        System.out.println("Error: out of bounds!");
                        break;
                    }
                    if(command.equals("mark")) {
                        System.out.println("OK! Marked as done: ");
                        System.out.println("  [X] " + Tasks[target].getDescription());
                        Tasks[target].setDone(true);
                    }
                    else {
                        System.out.println("OK! Marked as not done: " + target);
                        System.out.println("  [ ] " + Tasks[target].getDescription());
                        Tasks[target].setDone(false);
                    }
                    break;
                default:
                    System.out.println("added: " + userInput);
                    Tasks[numTasks] = new Task(userInput);
                    Tasks[numTasks++].setDone(false);
                    break;
            }

            System.out.println(seperator);
            userInput = in.nextLine();
        }
    }
}
