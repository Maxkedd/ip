import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Runs dependency-free persistence checks in an isolated working directory. */
public class StorageTest {
    private static final Path SAVE_FILE = Path.of("data", "duke.txt");

    /** Checks saving immediately after commands, then exercises the console entry point. */
    public static void main(String[] args) throws Exception {
        Command command = new Command(new ArrayList<>());
        command.processCommand("list");
        if (Files.exists(SAVE_FILE)) {
            throw new AssertionError("Listing must not create a save file");
        }
        command.processCommand("todo read café book");
        expectLines("T | 0 | read café book");
        command.processCommand("deadline return book /June 6th");
        command.processCommand("event meeting /2pm /4pm");
        expectLines("T | 0 | read café book", "D | 0 | return book | June 6th",
                "E | 0 | meeting | 2pm | 4pm");
        command.processCommand("mark 2");
        expectLines("T | 0 | read café book", "D | 1 | return book | June 6th",
                "E | 0 | meeting | 2pm | 4pm");
        command.processCommand("unmark 2");
        expectLines("T | 0 | read café book", "D | 0 | return book | June 6th",
                "E | 0 | meeting | 2pm | 4pm");
        command.processCommand("mark 99");
        command.processCommand("todo");
        command.processCommand("list");
        command.processCommand("help");
        command.processCommand("bye");
        expectLines("T | 0 | read café book", "D | 0 | return book | June 6th",
                "E | 0 | meeting | 2pm | 4pm");

        String input = "todo console task\nmark 1\nlist\nbye\n";
        var originalIn = System.in;
        var originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            CLANKER.main(new String[0]);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
        String transcript = output.toString(StandardCharsets.UTF_8);
        Files.writeString(Path.of("ui-transcript.txt"), transcript);
        for (String expected : List.of("Task added:", "[T][X]console task", "Bye! See you soon")) {
            if (!transcript.contains(expected)) {
                throw new AssertionError("Missing console output: " + expected);
            }
        }
        expectLines("T | 1 | console task");
        System.out.println("PASS: persistence checks and console UI smoke test");
    }

    /** Compares the complete file to detect missing fields and accidental appends. */
    private static void expectLines(String... expected) throws Exception {
        List<String> actual = Files.readAllLines(SAVE_FILE);
        if (!actual.equals(List.of(expected))) {
            throw new AssertionError("Unexpected saved tasks: " + actual);
        }
    }
}
