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

    /**
     * Constructor for the CountUserCommand.
     *
     * @param messageSender The MessageSender object for sending messages.
     * @param chatInterface The ChatInterface containing information about clients in the chat.
     */
    public CountUserCommand(MessageSender messageSender, ChatInterface chatInterface) {
        super(messageSender, chatInterface);
        this.name = "COUNT_USER";
        this.description = "Displays the number of users in the chat";
    }

    /**
     * Executes the CountUserCommand by sending the count of users to the requesting client.
     *
     * @param bodyMessage The BodyMessage received with the command request.
     * @return The execution status of the command (SUCCESS or ERROR).
     * @throws IOException if an I/O error occurs during message sending.
     */
    @Override
    public Status execute(BodyMessage bodyMessage) throws IOException {

        ChatClient client = chatInterface.getUser(bodyMessage.getNickname());
        messageSender.sendMessage(client, String.valueOf(chatInterface.countUsers()));
        return Status.SUCCESS;
    }
}
