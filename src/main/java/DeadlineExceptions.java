public class DeadlineExceptions extends  Exceptions {
    @Override
    public String toString() {
        return "Check your deadline input.\n" +
                "Make sure the text follows the format\n" +
                "deadline [Some text] /by [sometext]\n" +
                "(Note that the [ ] is not needed! E.g deadline guide souls /by Tuesday";
    }
}
