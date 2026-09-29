package Task;

/** Holds the description shared by all task types. */
public abstract class Task {
    protected final String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

