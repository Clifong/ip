public class Tasks {
    private boolean done = false;
    private String name;

    public Tasks(String name) {
        this.name = name;
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
}
