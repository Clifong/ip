public class Events extends Tasks{

    private String from;
    private String to;

    public Events(String name, boolean done, String from, String to) {
        super(name, done);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String saveTask(boolean last) {
        return  "E" + super.saveTask(last) + "|" + this.from + "|" + this.to + (last ? "" : "\n");
    }
}
