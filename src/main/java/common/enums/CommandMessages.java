package common.enums;

/**
 * Text for commands in messages
 */
public enum CommandMessages {


    MESSAGE("MESSAGE"),
    EXIT("EXIT"),
    LOGIN("LOGIN");

    private String textCommand;
    CommandMessages(String textCommand){

        this.textCommand = textCommand;
    }

    public String getTextCommand(){

        return textCommand;
    }

}
