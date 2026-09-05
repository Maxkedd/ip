package Task;

public class Event extends ToDo{
    String from;
    String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return this.from;
    }

    @Override
    public String getTaskIcon() {
        return ("E");
    }

    @Override
    public void printResponse() {
        System.out.printf("  [%s][%s]%s (from: %s to: %s)\n", this.getTaskIcon(), this.getStatusIcon(),
                this.description, this.from, this.to);
    }
}
