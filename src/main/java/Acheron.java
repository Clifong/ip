import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Acheron {
    private CommandParser commandParser;
    private StorageManager storageManager;
    private TaskList taskList;
    private  UI ui;

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
