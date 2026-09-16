import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import Task.ToDo;

/** Writes the current task list to a text file relative to the working directory. */
public class Storage {
    private static final Path FILE_PATH = Path.of("data", "Clanker.txt");

    /** Replaces the saved list so status changes do not leave outdated entries. */
    public void save(List<ToDo> tasks) throws IOException {
        Files.createDirectories(FILE_PATH.getParent());
        List<String> lines = new ArrayList<>();
        for (ToDo task : tasks) {
            lines.add(task.toFileString());
        }
        Files.write(FILE_PATH, lines);
    }
}
