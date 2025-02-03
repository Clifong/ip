package Acheron.Exceptions;

/**
 * This exception is thrown if an input is wrongly formatted or incomplete
 * when trying to create a deadline task
 */
public class EventExceptions extends GenericExceptions {

    /**
     * Override the toString() method so a custom error message is printed out if neede
     * @return Custom string message
     */
    @Override
    public String toString() {
        return "Check your event input.\n"
                + "Make sure the text follows the format\n"
                + "event [Some text] /from [Some text] /to [Some text]\n"
                + "(Note that the [ ] is not needed! E.g event investigate /from Monday /to Friday";
    }
}
