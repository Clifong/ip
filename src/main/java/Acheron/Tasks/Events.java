package Acheron.Tasks;

import Acheron.Exceptions.TaskExceptions;
import Acheron.Tasks.Tasks;
import Acheron.Utility.DateFormatter;

import java.time.LocalDate;

/**
 * Represents a events task
 */
public class Events extends Tasks {

    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor for the event class
     * @param name The name of the tasks
     * @param done Whether the tasks is done or not. Required when generating tasks from the saved file
     * @param from When the task starts
     * @param to WHen the task ends
     * @throws TaskExceptions Throws an exception if a wrong input is supplied
     */
    public Events(String name, boolean done, String from, String to) throws TaskExceptions {
        super(name, done);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Overrides the to string method with a custom version
     * @return A string format of what the task should produce
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateFormatter.formatDate(from) + " to: " + DateFormatter.formatDate(to) + ")";
    }

    /**
     * Used to generate the string content of the task for saving
     * @param last Indicates if the task is the last task in the task list object. Needed so the
     *             file writer does not add an unecessary new line which can cause file corruption
     * @return The content of the task
     */
    @Override
    public String saveTask(boolean last) {
        return  "E" + super.saveTask(last) + "|" + from.toString() + "|" + to.toString() + (last ? "" : "\n");
    }
}
