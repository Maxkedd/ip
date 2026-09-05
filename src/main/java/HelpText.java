public abstract class HelpText {
    public static final String COMMANDLIST = """
            \n
            Valid commands:
            todo 'command' (add todo task)
            deadline 'command' /'due date' (add deadline task with deadline)
            event 'command' /'start date' /'end date' (add event task with from and to date)
            list (list all tasks added)
            mark 'N' (mark task N as done)
            unmark 'N' (mark task N as not done)
            """;
}
