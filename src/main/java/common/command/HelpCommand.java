package common.command;

import common.enums.Status;
import common.json.bodymessage.BodyMessage;
import server.client.ChatClient;
import server.client.ChatInterface;
import server.sender.MessageSender;

import java.io.IOException;

/**
 * The HelpCommand class represents a command that displays all available chat commands to a client.
 */
public class HelpCommand extends BaseCommand{

    CommandsInterface commands;

    /**
     * Constructs a HelpCommand with the given MessageSender, ChatInterface, and CommandsInterface.
     *
     * @param messageSender The MessageSender object for sending messages.
     * @param chatInterface The ChatInterface managing clients in the chat.
     * @param commands The CommandsInterface containing all chat commands.
     */
    public HelpCommand(MessageSender messageSender, ChatInterface chatInterface,CommandsInterface commands) {
        super(messageSender, chatInterface);
        this.name = "HELP";
        this.description = "Displays all chat commands";
        this.commands = commands;
    }

    /**
     * Executes the HelpCommand by sending the list of available commands to the requesting client.
     *
     * @param bodyMessage The BodyMessage containing the command details.
     * @return The execution status of the command (SUCCESS or ERROR).
     * @throws IOException if an I/O error occurs during message sending.
     */
    @Override
    public Status execute(BodyMessage bodyMessage) throws IOException {

        ChatClient client = chatInterface.getUser(bodyMessage.getNickname());
        messageSender.sendMessage(client,getHelpText());
        return Status.SUCCESS;
    }

    /**
     * Retrieves the help text containing all available commands and their descriptions.
     * @return The formatted help text with command names and descriptions.
     */
    private String getHelpText(){

        StringBuilder builder = new StringBuilder();
        for(Command command : commands.getAllCommands()){

            builder.append(command.getName()).append(" - ").append(command.getDescription()).append(" ; \n");
        }
        if(builder.length() > 0){

            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }
}
