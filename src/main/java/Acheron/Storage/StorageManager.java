package Acheron.Storage;

import Acheron.Tasks.TaskList;
import Acheron.Tasks.TaskWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageManager {
    private String storagePath;

    public StorageManager(String storagePath, TaskList taskList) throws Exception {
        this.storagePath = storagePath;

        if (Files.notExists(Path.of(storagePath).getParent())) {
            new File(String.valueOf(Path.of(storagePath).getParent())).mkdir();
        }

        if (Files.notExists(Path.of(storagePath))) {
            try {
                Path file = Paths.get(storagePath);
                Files.createFile(file);
            } catch (Exception e) {
                System.out.println("Failed to create folder. Check you file permissions");
                throw e;
            }
        }

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(storagePath));
            String line = fileReader.readLine();
            while (line != null) {
                TaskWriter.createSavedTask(line, taskList);
                line = fileReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void updateSavedFile(TaskList taskList) throws Exception {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("./data/duke.txt", false));
            fileWriter.write(taskList.getAllTasksContent());
            fileWriter.close();
        } catch (Exception e) {
            throw e;
        }
    }
}
