package com.example.common.command;

import com.example.common.Service;
import com.example.common.enums.Status;
import com.example.common.json.bodymessage.BodyMessage;
import com.example.server.client.ChatInterface;
import com.example.server.sender.MessageSender;

import java.io.IOException;

/**
 * Class for user command - EXIT
 */
public class ExitCommand extends BaseCommand{

    /**
     * Constructor for the ExitCommand.
     *
     * @param messageSender The MessageSender object for sending messages.
     * @param chatInterface The ChatInterface containing information about clients in the chat.
     */
    public ExitCommand(MessageSender messageSender, ChatInterface chatInterface) {
        super(messageSender, chatInterface);
        this.name = "EXIT";
        this.description = "Exit chat";
    }

    /**
     * Executes the ExitCommand by removing the user from the chat and sending a goodbye message to all clients.
     *
     * @param bodyMessage The BodyMessage containing the command details.
     * @return The execution status of the command (SUCCESS or ERROR).
     * @throws IOException if an I/O error occurs during message sending.
     */
    @Override
    public Status execute(BodyMessage bodyMessage) throws IOException {

        String nickname = bodyMessage.getNickname();
        chatInterface.removeUser(nickname);
        messageSender.sendMessageToClient(nickname, Service.getInstance().getGoodbyeMessageForAll(), chatInterface);
        return Status.SUCCESS;
    }
}
