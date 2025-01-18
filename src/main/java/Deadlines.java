public class Deadlines extends Tasks{
    private String end;
    public Deadlines(String name, String end) {
        super(name);
        this.end = end;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.end + ")";
    }
}
