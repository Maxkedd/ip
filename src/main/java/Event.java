public class Event extends Deadline{
    String from;

    public Event(String description, String from, String by) {
        super(description, by);
        this.from = from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTaskIcon() {
        return ("E");
    }

    public void printResponse() {
        System.out.printf("  [%s][%s]%s (from: %s to: %s)\n", this.getTaskIcon(), this.getStatusIcon(),
                this.description, this.from, this.by);
    }
}
