package Acheron.Exceptions;

/**
 * This exception is thrown if trying to delete
 * a task outside the task list
 */
public class InvalidDeleteExceptions extends GenericExceptions {

    /**
     * Override the toString() method so a custom error message is printed out if neede
     * @return Custom string message
     */
    @Override
    public String toString() {
        return "You cannot remove a task with an invalid number!";
    }
}
