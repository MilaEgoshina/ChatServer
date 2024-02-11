package common.command;

import common.enums.Status;
import common.json.bodymessage.BodyMessage;
import server.client.ChatClient;
import server.client.ChatInterface;
import server.sender.MessageSender;

import java.io.IOException;

/**
 * Class for interaction with user command - HELP
 */
public class HelpCommand extends BaseCommand{

    CommandsInterface commands;

    public HelpCommand(MessageSender messageSender, ChatInterface chatInterface,CommandsInterface commands) {
        super(messageSender, chatInterface);
        this.name = "HELP";
        this.description = "Displays all chat commands";
        this.commands = commands;
    }

    @Override
    public Status execute(BodyMessage bodyMessage) throws IOException {

        ChatClient client = chatInterface.getUser(bodyMessage.getNickname());
        messageSender.sendMessage(client,getHelpText());
        return Status.SUCCESS;
    }

    /**
     * Get command prompt text
     * @return text with prompt
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
