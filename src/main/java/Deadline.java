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

    public String getTaskIcon() {
        return ("D");
    }

    public void printResponse() {
        System.out.printf("  [%s][%s]%s (by: %s)\n", this.getTaskIcon(), this.getStatusIcon(),
                this.description, this.by);
    }
}
