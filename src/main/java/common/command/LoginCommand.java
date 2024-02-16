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
 * Class for user command - LOGIN
 */
public class LoginCommand extends BaseCommand{


    public LoginCommand(MessageSender messageSender, ChatInterface chatInterface) {
        super(messageSender, chatInterface);
        this.name = CommandMessages.LOGIN.getTextCommand();
        this.description = "Log in";
    }

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
