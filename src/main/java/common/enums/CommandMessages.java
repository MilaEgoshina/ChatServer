package common.enums;

/**
 * The CommandMessages enum represents different command messages used in the application.
 */
public enum CommandMessages {


    MESSAGE("MESSAGE"),
    EXIT("EXIT"),
    LOGIN("LOGIN");

    private String textCommand;

    /**
     * Constructor for CommandMessages enum with a text command.
     * @param textCommand the text representation of the command
     */
    CommandMessages(String textCommand){

        this.textCommand = textCommand;
    }

    /**
     * Get the text representation of the command.
     * @return the text representation of the command
     */
    public String getTextCommand(){

        return textCommand;
    }

}
