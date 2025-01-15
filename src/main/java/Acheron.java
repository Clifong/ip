import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Acheron {
    private static ArrayList<String> tasks = new ArrayList<>();
    public static void main(String[] args) {

        String logo = "____________________________________________________________\n"
                + " Hello! I'm Acheron\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println("Hello from\n" + logo);

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
            if (input.equals("bye")) {
                System.out.println(genericText.formatted("Bye. Hope to see you again soon!"));
                break;
            } else if (input.equals("list")) {
                String listOfTasks = "";
                for (int i = 0; i < tasks.size(); i++) {
                    if (i < tasks.size() - 1) {
                        listOfTasks += String.format("%d. %s\n", i + 1, tasks.get(i));
                    } else {
                        listOfTasks += String.format("%d. %s", i + 1, tasks.get(i));
                    }
                }
                System.out.println(genericText.formatted(listOfTasks));
            } else {
                System.out.println(genericText.formatted("added: " + input));
                tasks.add(input);
            }
        }
    }
}
