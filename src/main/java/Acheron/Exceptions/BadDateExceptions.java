package Acheron.Exceptions;

public class BadDateExceptions extends Exceptions {
    @Override
    public String toString() {
        return "Date is wrongly formatted! Make sure it follows the YYYY-MM-DD format";
    }
}
