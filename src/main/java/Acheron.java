import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Acheron {
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
            } else {
                System.out.println(genericText.formatted(input));
            }
        }
    }
}
