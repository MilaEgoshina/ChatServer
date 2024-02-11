package common.command;

import common.enums.CommandMessages;
import common.enums.Status;
import common.json.bodymessage.BodyMessage;
import server.client.ChatInterface;
import server.sender.MessageSender;

import java.io.IOException;


/**
 * Class for a system command to send a MESSAGE
 */
public class MessageCommand extends BaseCommand{


    CommandsInterface commands;
    public MessageCommand(MessageSender messageSender, ChatInterface chatInterface, CommandsInterface commands) {
        super(messageSender, chatInterface);
        this.name = CommandMessages.MESSAGE.getTextCommand();
        this.description = "Message from user";
        this.commands = commands;
    }

    @Override
    public Status execute(BodyMessage bodyMessage) throws IOException {

        Status status = Status.SUCCESS;
        String text = bodyMessage.getText();
        if(commands.contains(text)){
            Command command = commands.getByName(text);
            command.execute(bodyMessage);
        }else{
            messageSender.sendMessageToClient(bodyMessage.getNickname(),text,chatInterface);
        }
        return status;
    }
}
