import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Acheron {
    private static ArrayList<Tasks> tasks = new ArrayList<>();
    public static void main(String[] args) {
        BufferedReader reader;

        String logo = "____________________________________________________________\n"
                + " Hello! I'm Acheron\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        if (Files.notExists(Path.of("./data"))) {
            new File("./data").mkdir();
        } if (Files.notExists(Path.of("./data/duke.txt"))) {
            Path file = Paths.get("./data/duke.txt");
            try {
                Files.createFile(file);
            } catch (Exception e) {
                System.out.println("Failed to create folder. Check you file permissions");
                return;
            }
        }

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("./data/duke.txt"));
            String line = fileReader.readLine();
            while (line != null) {
                TaskWriter.createSavedTask(line, tasks);
                line = fileReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        System.out.println(logo);

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

                String genericText = "____________________________________________________________\n" +
                        "%s\n" +
                        "____________________________________________________________";

                //Marking
                if (input.contains("mark")) {
                    String[] split = input.split(" ");
                    int num = Integer.parseInt(split[1]) - 1;
                    if (split[0].equals("unmark")) {
                        tasks.get(num).unmark();
                        System.out.println(genericText.formatted("OK, I've marked this task as not done yet:\n" + tasks.get(num)));
                    } else {
                        tasks.get(num).mark();
                        System.out.println(genericText.formatted("Nice! I've marked this task as done:\n" + tasks.get(num)));;
                    }
                    updateSavedFile();
                    continue;
                }

                //Bye
                if (input.equals("bye")) {
                    System.out.println(genericText.formatted("Bye. Hope to see you again soon!"));
                    break;
                }

                //list
                if (input.equals("list")) {
                    String listOfTasks = "";
                    for (int i = 0; i < tasks.size(); i++) {
                        if (i < tasks.size() - 1) {
                            listOfTasks += String.format("%d. %s\n", i + 1, tasks.get(i));
                        } else {
                            listOfTasks += String.format("%d. %s", i + 1, tasks.get(i));
                        }
                    }
                    System.out.println(genericText.formatted("Here are the tasks in your list:\n" + listOfTasks));

                    continue;
                }

                //delete
                if (input.contains("delete")) {
                    String[] split = input.split(" ");
                    int num = Integer.parseInt(split[1]) - 1;
                    Tasks removeTask = tasks.get(num);
                    tasks.remove(num);
                    System.out.println(genericText.formatted(
                                            "Noted. I've removed this task:\n" +
                                                    removeTask +
                                                    "\n" +
                                                    "Now you have " + tasks.size() +" tasks in the list."
                            )
                    );
                    updateSavedFile();
                    continue;
                }

                Tasks newTask = TaskWriter.createTask(input, tasks);
                System.out.println(genericText.formatted(
                                "Got it. I've added this task:\n" +
                                        newTask +
                                        "\n" +
                                        "Now you have " + tasks.size() +" tasks in the list."
                        )
                );
                updateSavedFile();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateSavedFile() throws Exception {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("./data/duke.txt", false));
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                String savedContent = tasks.get(i).saveTask(i == tasks.size() - 1);
                stringBuilder.append(savedContent);
            }
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (Exception e) {
            throw e;
        }
    }
}
