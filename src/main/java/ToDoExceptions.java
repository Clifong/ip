public class ToDoExceptions extends Exceptions {
    @Override
    public String toString() {
        return "Check your to do input.\n" +
                "Make sure the text follows the format\n" +
                "todo [Some text]\n" +
                "(Note that the [ ] is not needed! E.g todo eat peach";
    }
}
