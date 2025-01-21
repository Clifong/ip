package Acheron.Storage;
import Acheron.Tasks.TaskList;
import Acheron.Tasks.Tasks;
import Acheron.Tasks.ToDos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageWriterTest {
    @Test
    public void StorageManagerNonexistentPath(){
        String error = "java.lang.NullPointerException: Cannot invoke \"java.nio.file.Path.getFileSystem()\" because \"path\" is null";
        try {
            StorageManager storageManager = new StorageManager("", new TaskList());
            storageManager.updateSavedFile(new TaskList());
        } catch (Exception e) {
            assertEquals(error, e.toString());
        }
    }

    @Test
    public void StorageManagerNonsensicalTask(){
        String error = "Tasks cannot have null name!";
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new ToDos(null, false));
            StorageManager storageManager = new StorageManager("./data/duke.txt", taskList);
            storageManager.updateSavedFile(taskList);
        } catch (Exception e) {
            assertEquals(error, e.toString());
        }
    }

}