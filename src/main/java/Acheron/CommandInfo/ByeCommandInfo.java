package Acheron.CommandInfo;

/**
 * Used to create an object that represents the help command
 * info when bye is used
 */
public class ByeCommandInfo extends GenericCommandInfo {

    @Override
    public String toString() {
        String topHalf = super.toString();
        return topHalf
                + "bye\n"
                + "Say goodbye to the chatbot\n\n"
                + "E.g usage: bye\n";
    }
}
