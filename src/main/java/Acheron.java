import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        System.out.println("Hello from\n" + logo);

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
                    continue;
                }

                //Generic adding of tasls
                Tasks newTask;
                if (input.contains("todo")) {
                    try {
                        String taskName = input.substring(input.indexOf(' ') + 1, input.length());
                        if (taskName.equals("todo")) {
                            throw new ToDoExceptions();
                        }
                        newTask = new ToDos(taskName);
                    } catch (Exception e) {
                        System.out.println(new ToDoExceptions());
                        continue;
                    }
                } else if (input.contains("deadline")) {
                    try {
                        String taskWithDate = input.substring(input.indexOf(' ') + 1, input.length());
                        String taskName = taskWithDate.substring(0, taskWithDate.indexOf("/by") - 1);
                        String deadline = taskWithDate.substring(taskWithDate.indexOf("by") + 3, taskWithDate.length());
                        newTask = new Deadlines(taskName, deadline);
                    } catch (Exception e) {
                        System.out.println(new DeadlineExceptions());
                        continue;
                    }
                } else if (input.contains("event")) {
                    try {
                        String taskWithDate = input.substring(input.indexOf(' ') + 1, input.length());
                        String taskName = taskWithDate.substring(0, taskWithDate.indexOf("/from") - 1);
                        String from = taskWithDate.substring(taskWithDate.indexOf("from") + 5, taskWithDate.indexOf("/to") - 1);
                        String to = taskWithDate.substring(taskWithDate.indexOf("to") + 3, taskWithDate.length());
                        newTask = new Events(taskName, from, to);
                    } catch (Exception e) {
                        System.out.println(new EventExceptions());
                        continue;
                    }
                } else {
                    throw new Exceptions();
                }
                tasks.add(newTask);
                System.out.println(genericText.formatted(
                        "Got it. I've added this task:\n" +
                                newTask +
                                "\n" +
                                "Now you have " + tasks.size() +" tasks in the list."
                        )
                );
            }
        } catch (Exception e) {
            System.out.println(new Exceptions());
        }
    }
}
