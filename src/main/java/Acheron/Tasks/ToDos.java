package Acheron.Tasks;

import Acheron.Exceptions.TaskExceptions;
import Acheron.Tasks.Tasks;

public class ToDos extends Tasks {
    public ToDos(String name, boolean done) throws TaskExceptions {
        super(name, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveTask(boolean last) {
        return "T" + super.saveTask(last) + (last ? "" : "\n");
    }
}
