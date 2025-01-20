package Acheron.UI;

public class UI {

    private static String genericTask = "____________________________________________________________\n" +
                                 "%s\n" +
                                 "____________________________________________________________";

    public UI() {
        String logo = " Hello! I'm Acheron.Acheron\n"
                    + " What can I do for you?";
        displayText(logo);
    }

    public static void displayText(String text) {
        System.out.println(genericTask.formatted(text));
    }
}
