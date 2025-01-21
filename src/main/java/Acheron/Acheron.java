package Acheron;
import Acheron.Storage.StorageManager;
import Acheron.Tasks.TaskList;
import Acheron.UI.UI;
import Acheron.Utility.CommandParser;

public class Acheron {
    private CommandParser commandParser;
    private StorageManager storageManager;
    private TaskList taskList;
    private UI ui;

    public Acheron(String storagePath) {
        try {
            ui = new UI();
            taskList = new TaskList();
            storageManager = new StorageManager(storagePath, taskList);
            commandParser = new CommandParser(storageManager, taskList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Acheron("./data/duke.txt");
    }
}
