package common.command;

import common.enums.Status;
import common.json.bodymessage.BodyMessage;
import lombok.EqualsAndHashCode;
import server.client.ChatClient;
import server.client.ChatInterface;
import server.sender.MessageSender;

import java.io.IOException;


/**
 * Class for user command COUNT_USERS
 */
@EqualsAndHashCode
public class CountUserCommand extends BaseCommand{


    public CountUserCommand(MessageSender messageSender, ChatInterface chatInterface) {
        super(messageSender, chatInterface);
        this.name = "COUNT_USER";
        this.description = "Displays the number of users in the chat";
    }

    @Override
    public Status execute(BodyMessage bodyMessage) throws IOException {

        ChatClient client = chatInterface.getUser(bodyMessage.getNickname());
        messageSender.sendMessage(client, String.valueOf(chatInterface.countUsers()));
        return Status.SUCCESS;
    }
}
