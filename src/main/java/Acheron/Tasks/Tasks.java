package Acheron.Tasks;

import Acheron.Exceptions.TaskExceptions;

public abstract class Tasks {
    private boolean done;
    private String name;

    public Tasks(String name, boolean done) throws TaskExceptions {
        if (name == null) {
            throw new TaskExceptions();
        }
        this.name = name;
        this.done = done;
    }
    @Override
    public String toString() {
        if (done) {
            return "[X] %s"
                    .formatted(this.name);
        } else {
            return "[] %s".formatted(this.name);
        }
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String saveTask(boolean last) {
        if (done) {
            return "|" + "X" + "|" + name;
        } else {
            return "|" + "O" + "|" + name;
        }
    };
}

