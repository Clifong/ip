import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandParser {

    public CommandParser(StorageManager storageManager, TaskList taskList) {

        try {
            while (true) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String input = "";
                try {
                    input = bufferedReader.readLine();
                } catch (Exception e) {
                    System.out.println("Something went wrong!");
                    break;
                }

                //Marking and umarking
                if (input.contains("mark")) {
                    String[] split = input.split(" ");
                    int num = Integer.parseInt(split[1]) - 1;
                    if (split[0].equals("unmark")) {
                        taskList.unmarkTask(num);
                    } else {
                        taskList.markTask(num);
                    }
                    storageManager.updateSavedFile(taskList);
                    continue;
                }

                //Bye
                if (input.equals("bye")) {
                    UI.displayText("Bye. Hope to see you again soon!");
                    break;
                }

                //list
                if (input.equals("list")) {
                    taskList.listAllTasks();
                    continue;
                }

                //delete
                if (input.contains("delete")) {
                    String[] split = input.split(" ");
                    int num = Integer.parseInt(split[1]) - 1;
                    taskList.removeTask(num);
                    storageManager.updateSavedFile(taskList);
                    continue;
                }

                //add task
                TaskWriter.createTask(input, taskList);
                StorageManager.updateSavedFile(taskList);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
