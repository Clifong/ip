package Acheron.Tasks;

import Acheron.Exceptions.BadDateExceptions;
import Acheron.Exceptions.CorruptedFileException;
import Acheron.Exceptions.EventExceptions;
import Acheron.Exceptions.Exceptions;
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
        Tasks newTask = null;
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
                if (!dataValidator(deadline)) {
                    throw new BadDateExceptions();
                }
                assert deadline.length() == 10;
                newTask = new Deadlines(taskName, false, deadline);
            } catch (BadDateExceptions e) {
                throw e;
            }
            catch (Exception e) {
                throw new EventExceptions();
            }
        } else if (input.contains("event")) {
            try {
                String taskWithDate = input.substring(input.indexOf(' ') + 1, input.length());
                String taskName = taskWithDate.substring(0, taskWithDate.indexOf("/from") - 1);
                String from = taskWithDate.substring(taskWithDate.indexOf("from") + 5, taskWithDate.indexOf("/to") - 1);
                String to = taskWithDate.substring(taskWithDate.indexOf("to") + 3, taskWithDate.length());
                if (!dataValidator(from) || !dataValidator(to)) {
                    throw new BadDateExceptions();
                }
                assert from.length() == 10;
                assert to.length() == 10;
                newTask = new Events(taskName, false, from, to);
            } catch (BadDateExceptions e) {
                throw e;
            }
            catch (Exception e) {
                throw new EventExceptions();
            }
        } else {
            throw new Exceptions();
        }
        taskList.addTask(newTask);
    }

    /**
     * A utility method to check if the date in the input is correctly formatted
     * @param date A date we are checking
     * @return True if the date is correctly formatted. Otherwise false
     */
    private static boolean dataValidator(String date) {
        assert date.length() == 10;
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
     * A method to create a task from the saved file
     * @param input A text from the saved file
     * @param taskList A task list object
     * @throws Exception Throws any errors that occur in some of the usbmethods
     */
    public static void createSavedTask(String input, TaskList taskList) throws Exception {
        try {
            String[] split = input.split("\\|");
            boolean done = split[1].equals("X");
            String taskName = split[2];
            if (split[0].equals("T")) {
                taskList.addTaskFromStorage(new ToDos(taskName, done));
            } else if (split[0].equals("D")) {
                String by = split[3];
                taskList.addTaskFromStorage(new Deadlines(taskName, done, by));
            } else if (split[0].equals("E")) {
                String from = split[3];
                String to = split[4];
                taskList.addTaskFromStorage(new Events(taskName, done, from, to));
            } else {
                throw new CorruptedFileException();
            }
        } catch (Exception e) {
            throw new CorruptedFileException();
        }
    }
}
