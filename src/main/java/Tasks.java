public abstract class Tasks {
    private boolean done;
    private String name;

    public Tasks(String name, boolean done) {
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

