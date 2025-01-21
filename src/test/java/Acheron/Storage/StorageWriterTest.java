package Acheron.Storage;
import Acheron.Tasks.TaskList;
import Acheron.Tasks.Tasks;
import Acheron.Tasks.ToDos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageWriterTest {
    static final String NONEXISTTENTPATHERROR =  "java.lang.NullPointerException:" +
            "Cannot invoke \"java.nio.file.Path.getFileSystem()\" because \"path\" is null";
    static final String NONSENSICALTASKERROR = "Tasks cannot have null name!";

    @Test
    public void StorageManagerNonexistentPath(){
        try {
            StorageManager storageManager = new StorageManager("", new TaskList());
            storageManager.updateSavedFile(new TaskList());
        } catch (Exception e) {
            assertEquals(NONEXISTTENTPATHERROR, e.toString());
        }
    }

    @Test
    public void StorageManagerNonsensicalTask(){
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new ToDos(null, false));
            StorageManager storageManager = new StorageManager("./data/duke.txt", taskList);
            storageManager.updateSavedFile(taskList);
        } catch (Exception e) {
            assertEquals(NONSENSICALTASKERROR, e.toString());
        }
    }

}