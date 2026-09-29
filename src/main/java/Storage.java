import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/** Stores successful commands so they can be replayed at startup. */
public class Storage {
    /** Command history location, relative to the program's working directory. */
    private static final Path FILE_PATH = Path.of("data", "commands.txt");

    /** Appends one command, creating the directory and file if needed. */
    public void appendCommand(String command) throws IOException {
        Files.createDirectories(FILE_PATH.getParent());

        Files.writeString(
                FILE_PATH,
                command + System.lineSeparator(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }

    /** Reads the command history in order, or returns an empty list on first use. */
    public List<String> loadCommands() throws IOException {
        if (!Files.exists(FILE_PATH)) {
            return List.of();
        }

        return Files.readAllLines(FILE_PATH);
    }
}
