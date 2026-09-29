package Task;

/** A task that must be completed by a specified date or time. */
public class Deadline extends ToDo {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }

    /** Prints the task details followed by its deadline. */
    @Override
    public void printResponse() {
        System.out.printf("  [%s][%s]%s (by: %s)\n", this.getTaskIcon(), this.getStatusIcon(),
                this.description, this.by);
    }
}
