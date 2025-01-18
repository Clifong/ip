public class EventExceptions extends Exceptions {
    @Override
    public String toString() {
        return "Check your event input.\n" +
                "Make sure the text follows the format\n" +
                "event [Some text] /from [Some text] /to [Some text]\n" +
                "(Note that the [ ] is not needed! E.g event investigate /from Monday /to Friday";
    }
}
