package Task;

/** A task that takes place between a start and an end date or time. */
public class Event extends ToDo {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }

    /** Prints the task details followed by its start and end times. */
    @Override
    public void printResponse() {
        System.out.printf("  [%s][%s]%s (from: %s to: %s)\n", this.getTaskIcon(), this.getStatusIcon(),
                this.description, this.from, this.to);
    }
}
