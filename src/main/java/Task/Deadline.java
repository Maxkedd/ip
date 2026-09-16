package Task;

public class Deadline extends ToDo {
    String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String getTaskIcon() {
        return ("D");
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + by;
    }

    @Override
    public void printResponse() {
        System.out.printf("  [%s][%s]%s (by: %s)\n", this.getTaskIcon(), this.getStatusIcon(),
                this.description, this.by);
    }
}
