package Acheron.Tasks;

import Acheron.Exceptions.TaskExceptions;
import Acheron.Tasks.Tasks;
import Acheron.Utility.DateFormatter;

import java.time.LocalDate;

public class Deadlines extends Tasks {
    private LocalDate end;

    public Deadlines(String name, boolean done, String end) throws TaskExceptions {
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