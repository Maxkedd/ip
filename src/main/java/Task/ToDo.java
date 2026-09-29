package Task;

/** A task with a completion status; also supplies shared behaviour for dated tasks. */
public class ToDo extends Task {
    private boolean isDone;

    public ToDo(String description) {
        super(description);
        this.isDone = false;
    }

    /** Returns the completion marker used when displaying a task. */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getTaskIcon() {
        return "T";
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /** Prints the task type, completion marker, and description. */
    public void printResponse() {
        System.out.printf("  [%s][%s]%s\n", this.getTaskIcon(), this.getStatusIcon(), this.description);
    }
}
