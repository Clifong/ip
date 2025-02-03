package Acheron.Utility;

import Acheron.Exceptions.Exceptions;
import Acheron.Storage.StorageManager;
import Acheron.Tasks.TaskList;
import Acheron.Tasks.TaskWriter;
import Acheron.UI.UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * A utility class used to parse the user's input correctly
 */
public class CommandParser {
    private StorageManager storageManager;
    private  TaskList taskList;

    /**
     * A constructor of the class
     * @param storageManager A storage manager instace
     * @param taskList A task list instance
     */
    public CommandParser(StorageManager storageManager, TaskList taskList) {
        this.storageManager = storageManager;
        this.taskList = taskList;
    }

    public void receiveInput(String input) {
        try {
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
                return;
            }

            //Finding
            if (input.contains("find")) {
                int space = input.indexOf(" ");
                String keyword = input.substring(space + 1);
                taskList.findAllTaskWithKeyword(keyword);
                return;
            }

            //Bye
            if (input.equals("bye")) {
                UI.displayText("Bye. Hope to see you again soon!");
                return;
            }

            //list
            if (input.equals("list")) {
                UI.displayText(taskList.toString());
                return;
            }

            //delete
            if (input.contains("delete")) {
                String[] split = input.split(" ");
                int num = Integer.parseInt(split[1]) - 1;
                taskList.removeTask(num);
                storageManager.updateSavedFile(taskList);
                return;
            }

            //add task
            TaskWriter.createTask(input, taskList);
            storageManager.updateSavedFile(taskList);
        } catch (Exception e) {
            UI.displayText(e.toString());
        }
    }
}
