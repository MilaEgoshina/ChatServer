package common.command;

import common.Service;
import common.enums.CommandMessages;
import common.enums.Status;
import common.json.bodymessage.BodyMessage;
import server.client.ChatClient;
import server.client.ChatInterface;
import server.sender.MessageSender;

import java.io.IOException;

/**
 * This class represents a command to log in a client to the chat.
 */
public class LoginCommand extends BaseCommand{

    /**
     * Constructor for the LoginCommand.
     *
     * @param messageSender The MessageSender object for sending messages.
     * @param chatInterface The ChatInterface containing information about clients in the chat.
     */
    public LoginCommand(MessageSender messageSender, ChatInterface chatInterface) {
        super(messageSender, chatInterface);
        this.name = CommandMessages.LOGIN.getTextCommand();
        this.description = "Log in";
    }

    /**
     * Executes the LoginCommand by logging in a client to the chat.
     *
     * @param bodyMessage The BodyMessage containing the login details.
     * @return The execution status of the command.
     * @throws IOException if an I/O error occurs during message sending.
     */
    @Override
    public Status execute(BodyMessage bodyMessage) throws IOException {

        String nickname = bodyMessage.getNickname();
        String ip = bodyMessage.getIp();
        int port = bodyMessage.getPort();

        Status status = chatInterface.containsUser(nickname) ? Status.SUCCESS : Status.ERROR;
        //check if there is client with such name in the chat
        if(status != Status.SUCCESS){

            ChatClient client = new ChatClient(nickname,ip,port);
            messageSender.sendLastMessages(client);
            chatInterface.addUser(client);
            messageSender.sendMessageToClient(nickname, Service.getInstance().getHelloMessageForAll(), chatInterface);
        }
        return status;
    }
}
