package common.command;

import common.Service;
import common.enums.Status;
import common.json.bodymessage.BodyMessage;
import server.client.ChatInterface;
import server.sender.MessageSender;

import java.io.IOException;

/**
 * Class for user command - EXIT
 */
public class ExitCommand extends BaseCommand{


    public ExitCommand(MessageSender messageSender, ChatInterface chatInterface) {
        super(messageSender, chatInterface);
        this.name = "EXIT";
        this.description = "Exit chat";
    }

    @Override
    public Status execute(BodyMessage bodyMessage) throws IOException {

        String nickname = bodyMessage.getNickname();
        chatInterface.removeUser(nickname);
        messageSender.sendMessageToClient(nickname, Service.getInstance().getGoodbyeMessageForAll(), chatInterface);
        return Status.SUCCESS;
    }
}
