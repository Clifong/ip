package Acheron.Tasks;

import java.time.LocalDate;

import Acheron.Exceptions.TaskExceptions;
import Acheron.Utility.DateFormatter;

/**
 * Represents a deadlines task
 */
public class Deadlines extends Tasks {
    private LocalDate end;

    /**
     * Constructor for the deadlines class
     * @param name The name of the tasks
     * @param done Whether the tasks is done or not. Required when generating tasks from the saved file
     * @param end When the tasks' deadline is
     * @throws TaskExceptions Throws an exception if a wrong input is supplied
     */
    public Deadlines(String name, boolean done, String end) throws TaskExceptions {
        super(name, done);
        this.end = LocalDate.parse(end);
    }

    /**
     * Overrides the to string method with a custom version
     * @return A string format of what the task should produce
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateFormatter.formatDate(end) + ")";
    }

    /**
     * Used to generate the string content of the task for saving
     * @param last Indicates if the task is the last task in the task list object. Needed so the
     *             file writer does not add an unecessary new line which can cause file corruption
     * @return The content of the task
     */
    @Override
    public String saveTask(boolean last) {
        return "D" + super.saveTask(last) + "|" + end.toString() + (last ? "" : "\n");
    }
}
