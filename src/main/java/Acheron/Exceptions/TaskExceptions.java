package Acheron.Exceptions;

public class TaskExceptions extends Exceptions{
    @Override
    public String toString() {
        return "Tasks cannot have null name!";
    }
}
