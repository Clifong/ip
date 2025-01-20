import java.time.LocalDate;

public class Deadlines extends Tasks {
    private LocalDate end;

    public Deadlines(String name, boolean done, String end) {
        super(name, done);
        this.end = LocalDate.parse(end);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateFormatter.formatDate(end) + ")";
    }

    @Override
    public String saveTask(boolean last) {
        return "D" + super.saveTask(last) + "|" + end.toString() + (last ? "" : "\n");
    }
}