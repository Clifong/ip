import java.util.ArrayList;
import java.util.Arrays;

public class TaskWriter {

    public static Tasks createTask(String input, ArrayList<Tasks> myTasks) throws Exception {
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
                newTask = new Deadlines(taskName, false, deadline);
            } catch (Exception e) {
                System.out.println(e);
                throw new DeadlineExceptions();
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
                newTask = new Events(taskName, false, from, to);
            } catch (Exception e) {
                throw new EventExceptions();
            }
        } else {
            throw new Exceptions();
        }
        myTasks.add(newTask);
        return newTask;
    }

    private static boolean dataValidator(String date) {
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

    public static void createSavedTask(String input, ArrayList<Tasks> myTasks) throws Exception {
        try {
            String[] split = input.split("\\|");
            boolean done = split[1].equals("X");
            String taskName = split[2];
            if (split[0].equals("T")) {
                myTasks.add(new ToDos(taskName, done));
            } else if (split[0].equals("D")) {
                String by = split[3];
                myTasks.add(new Deadlines(taskName, done, by));
            } else if (split[0].equals("E")) {
                String from = split[3];
                String to = split[4];
                myTasks.add(new Events(taskName, done, from, to));
            } else {
                throw new CorruptedFileException();
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new CorruptedFileException();
        }
    }
}
