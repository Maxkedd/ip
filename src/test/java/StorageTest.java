import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import Task.Deadline;
import Task.Event;
import Task.ToDo;

/** Runs dependency-free persistence checks in an isolated working directory. */
public class StorageTest {
    private static final Path SAVE_FILE = Path.of("data", "commands.txt");

    /** Checks saving immediately after commands, then exercises the console entry point. */
    public static void main(String[] args) throws Exception {
        Storage storage = new Storage();
        if (!storage.loadCommands().isEmpty()) {
            throw new AssertionError("Run this check in an empty working directory");
        }
        ArrayList<ToDo> tasks = new ArrayList<>();
        Command command = new Command(tasks);
        command.processCommand("list");
        if (Files.exists(SAVE_FILE)) {
            throw new AssertionError("Listing must not create a save file");
        }
        command.processCommand("todo read café book");
        expectLines("todo read café book");
        command.processCommand("deadline return book /June 6th");
        command.processCommand("event meeting /2pm /4pm");
        command.processCommand("mark 2");
        command.processCommand("unmark 2");
        command.processCommand("delete 1");
        command.processCommand("mark 2");
        List<String> history = List.of("todo read café book", "deadline return book /June 6th",
                "event meeting /2pm /4pm", "mark 2", "unmark 2", "delete 1", "mark 2");
        expectLines(history.toArray(String[]::new));
        command.processCommand("mark 99");
        command.processCommand("todo");
        command.processCommand("deadline missing date");
        command.processCommand("event missing times");
        command.processCommand("delete invalid");
        command.processCommand("unknown");
        command.processCommand("list");
        command.processCommand("help");
        command.processCommand("bye");
        expectLines(history.toArray(String[]::new));

        ArrayList<ToDo> restored = new ArrayList<>();
        Command replay = new Command(restored);
        for (String savedCommand : storage.loadCommands()) {
            replay.processCommand(savedCommand, false);
        }
        if (restored.size() != 2) {
            throw new AssertionError("Replay did not preserve deletion");
        }
        // Check task data directly; storage no longer serializes individual tasks.
        if (!(restored.get(0) instanceof Deadline deadline)
                || !deadline.getDescription().equals("return book")
                || !deadline.getBy().equals("June 6th")
                || !deadline.getStatusIcon().equals(" ")) {
            throw new AssertionError("Replay did not restore the deadline and its unmarked status");
        }
        if (!(restored.get(1) instanceof Event event)
                || !event.getDescription().equals("meeting")
                || !event.getFrom().equals("2pm")
                || !event.getTo().equals("4pm")
                || !event.getStatusIcon().equals("X")) {
            throw new AssertionError("Replay did not restore the event and its marked status");
        }
        expectLines(history.toArray(String[]::new));

        String input = "todo console task\nmark 3\nlist\nbye\n";
        String transcript = runConsole(input);
        Files.writeString(Path.of("ui-transcript.txt"), transcript);
        for (String expected : List.of("1.   [D][ ]return book (by: June 6th)",
                "2.   [E][X]meeting (from: 2pm to: 4pm)",
                "[T][X]console task", "Bye! See you soon")) {
            if (!transcript.contains(expected)) {
                throw new AssertionError("Missing console output: " + expected);
            }
        }
        ArrayList<String> updatedHistory = new ArrayList<>(history);
        updatedHistory.add("todo console task");
        updatedHistory.add("mark 3");
        expectLines(updatedHistory.toArray(String[]::new));

        String restart = runConsole("bye\n");
        if (!restart.contains("3.   [T][X]console task")) {
            throw new AssertionError("Startup must list tasks restored from the previous session");
        }
        expectLines(updatedHistory.toArray(String[]::new));
        System.out.println("PASS: command history, replay, and startup persistence checks");
    }

    /** Runs a console session while capturing output and restoring standard streams. */
    private static String runConsole(String input) {
        var originalIn = System.in;
        var originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            Clanker.main(new String[0]);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
        return output.toString(StandardCharsets.UTF_8);
    }

    /** Compares the complete history to detect overwritten or duplicated commands. */
    private static void expectLines(String... expected) throws Exception {
        List<String> actual = Files.readAllLines(SAVE_FILE);
        if (!actual.equals(List.of(expected))) {
            throw new AssertionError("Unexpected command history: " + actual);
        }
    }
}
