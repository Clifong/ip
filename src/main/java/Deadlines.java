public class Deadlines extends Tasks {
    private String end;

    public Deadlines(String name, boolean done, String end) {
        super(name, done);
        this.end = end;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.end + ")";
    }

    @Override
    public String saveTask(boolean last) {
        return "D" + super.saveTask(last) + "|" + this.end + (last ? "" : "\n");
    }
}