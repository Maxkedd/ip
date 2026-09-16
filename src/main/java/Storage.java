import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import Task.ToDo;

public class Storage {
    private static final Path FILE_PATH = Path.of("data", "Clanker.txt");

    public void printFileContents() throws IOException {
        if (!Files.exists(FILE_PATH)) {
            return;
        }

        for (String line : Files.readAllLines(FILE_PATH)) {
            System.out.println(line);
        }
    }

    public void save(List<ToDo> tasks) throws IOException {
        Files.createDirectories(FILE_PATH.getParent());
        List<String> lines = new ArrayList<>();
        for (ToDo task : tasks) {
            lines.add(task.toFileString());
        }
        Files.write(FILE_PATH, lines);
    }
}
