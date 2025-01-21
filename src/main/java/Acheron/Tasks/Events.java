package Acheron.Tasks;

import Acheron.Exceptions.TaskExceptions;
import Acheron.Tasks.Tasks;
import Acheron.Utility.DateFormatter;

import java.time.LocalDate;

public class Events extends Tasks {

    private LocalDate from;
    private LocalDate to;

    public Events(String name, boolean done, String from, String to) throws TaskExceptions {
        super(name, done);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateFormatter.formatDate(from) + " to: " + DateFormatter.formatDate(to) + ")";
    }

    @Override
    public String saveTask(boolean last) {
        return  "E" + super.saveTask(last) + "|" + from.toString() + "|" + to.toString() + (last ? "" : "\n");
    }
}
