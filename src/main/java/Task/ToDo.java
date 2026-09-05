package Task;

public class ToDo extends Task {
    protected boolean isDone;

    public ToDo(String description) {
        super(description);
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getTaskIcon() {
        return ("T");
    }

    public void setDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public void printResponse() {
        System.out.printf("  [%s][%s]%s\n", this.getTaskIcon(), this.getStatusIcon(), this.description);
    }
}