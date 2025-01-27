package Acheron.UI;

/**
 * Represents the UI display
 */
public class UI {

    private static String genericTask = "____________________________________________________________\n"
            + "%s\n"
            + "____________________________________________________________";

    /**
     * A constructor of the UI class
     */
    public UI() {
        String logo = " Hello! I'm Acheron\n"
                    + " What can I do for you?";
        displayText(logo);
    }

    /**
     * A method used to display the text on the terminal or in the UI
     * @param text The text to be shown
     */
    public static void displayText(String text) {
        System.out.println(genericTask.formatted(text));
    }
}
