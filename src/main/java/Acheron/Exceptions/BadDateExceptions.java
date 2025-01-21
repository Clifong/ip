package Acheron.Exceptions;

/**
 * This exception is thrown only if the input date is not of the format YYYY-MM-DD
 */
public class BadDateExceptions extends Exceptions {

    /**
     * Override the toString() method so a custom error message is printed out if needed
     * @return Custom string message
     */
    @Override
    public String toString() {
        return "Date is wrongly formatted! Make sure it follows the YYYY-MM-DD format";
    }
}
