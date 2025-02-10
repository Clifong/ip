package Acheron.Tasks;

import java.time.LocalDate;

import Acheron.Exceptions.BadDateExceptions;
import Acheron.Exceptions.CorruptedFileException;
import Acheron.Exceptions.DeadlineExceptions;
import Acheron.Exceptions.EventExceptions;
import Acheron.Exceptions.GenericExceptions;
import Acheron.Exceptions.ToDoExceptions;

/**
 * A class that does work to extract the required information out of any input
 * pertaining to the creatioon of any task
 */
public class TaskWriter {

    /**
     * Creates a task
     * @param input The text input by the user
     * @param taskList The task list object
     * @throws Exception Throws any errors that occur in some of the usbmethods
     */
    public static void createTask(String input, TaskList taskList) throws Exception {
        //Generic adding of tasls
        Task newTask = null;
        input = input.strip();
        if (input.contains("todo")) {
            try {
                if (input.equals("todo")) {
                    throw new ToDoExceptions();
                }
                String taskName = input.substring(input.indexOf(" ") + 1, input.length());
                newTask = new ToDos(taskName, false);
            } catch (Exception e) {
                throw new ToDoExceptions();
            }
        } else if (input.contains("deadline")) {
            try {
                String taskWithDate = input.substring(input.indexOf(' ') + 1, input.length());
                String taskName = taskWithDate.substring(0, taskWithDate.indexOf("/by") - 1);
                String deadline = taskWithDate.substring(taskWithDate.indexOf("by") + 3, taskWithDate.length());
                if (!isValidDate(deadline)) {
                    throw new BadDateExceptions();
                }
                assert deadline.length() == 10;
                newTask = new Deadline(taskName, false, deadline);
            } catch (BadDateExceptions e) {
                throw e;
            }
            catch (Exception e) {
                throw new DeadlineExceptions();
            }
        } else if (input.contains("event")) {
            try {
                String taskWithDate = input.substring(input.indexOf(' ') + 1, input.length());
                String taskName = taskWithDate.substring(0, taskWithDate.indexOf("/from") - 1);
                String startDate = taskWithDate.substring(taskWithDate.indexOf("from") + 5,
                        taskWithDate.indexOf("/to") - 1);
                String endDate = taskWithDate.substring(taskWithDate.indexOf("to") + 3, taskWithDate.length());
                if (!isValidDate(startDate) || !isValidDate(endDate) || !isValidStartAndEndDate(startDate, endDate)) {
                    throw new BadDateExceptions();
                }
                assert startDate.length() == 10;
                assert endDate.length() == 10;
                newTask = new Events(taskName, false, startDate, endDate);
            } catch (BadDateExceptions e) {
                throw e;
            }
            catch (Exception e) {
                throw new EventExceptions();
            }
        } else {
            throw new GenericExceptions();
        }
        taskList.addTask(newTask);
    }

    /**
     * A utility method to check if the date in the input is correctly formatted
     * @param date A date we are checking
     * @return True if the date is correctly formatted. Otherwise false
     */
    private static boolean isValidDate(String date) {
        if (!date.contains("-")) {
            return false;
        }
        String[] split = date.split("-");
        if (split.length != 3) {
            return false;
        }
        if (split[0].length() != 4 || split[1].length() != 2 || split[2].length() != 2) {
            return false;
        }
        return true;
    }

    /**
     * A utility method to check if the start date is less than end date
     * @param startDate is the start date of event
     * @param endDate is the end date of event
     * @return True is the start date is before, or is the end date. Otherwise no
     */
    private static boolean isValidStartAndEndDate(String startDate, String endDate) {
        try {
            LocalDate startDateObject = LocalDate.parse(startDate);
            LocalDate endDateObject = LocalDate.parse(endDate);
            if (startDateObject.getYear() < endDateObject.getYear()) {
                return true;
            } else if (startDateObject.getYear() == endDateObject.getYear()) {
                if (startDateObject.getMonth().getValue() < endDateObject.getMonth().getValue()) {
                    return true;
                } else if (startDateObject.getMonth().getValue() == endDateObject.getMonth().getValue()) {
                    return startDateObject.getDayOfMonth() <= endDateObject.getDayOfMonth();
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * A method to create a task from the saved file
     * @param input A text from the saved file
     * @param taskList A task list object
     * @throws Exception Throws any errors that occur in some of the usbmethods
     */
    public static void createSavedTask(String input, TaskList taskList) throws Exception {
        try {
            String[] split = input.split("\\|");
            boolean isDone = split[1].equals("X");
            String taskName = split[2];
            if (split[0].equals("T")) {
                taskList.addTaskFromStorage(new ToDos(taskName, isDone));
            } else if (split[0].equals("D")) {
                String endDate = split[3];
                taskList.addTaskFromStorage(new Deadline(taskName, isDone, endDate));
            } else if (split[0].equals("E")) {
                String startDate = split[3];
                String endDate = split[4];
                taskList.addTaskFromStorage(new Events(taskName, isDone, startDate, endDate));
            } else {
                throw new CorruptedFileException();
            }
        } catch (Exception e) {
            throw new CorruptedFileException();
        }
    }
}
