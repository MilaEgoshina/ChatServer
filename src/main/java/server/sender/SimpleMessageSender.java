package server.sender;

import common.enums.CommandMessages;
import common.json.bodymessage.BodyMessage;
import common.json.serializer.MessageFactory;
import server.client.ChatClient;
import server.client.ChatInterface;
import server.handler.MessageQueue;
import server.lastmessages.LastMessages;

import java.io.IOException;


/**
 * Class for implementation of message sending
 */
public class SimpleMessageSender implements MessageSender{

    private LastMessages lastMessages;
    private MessageQueue<BodyMessage> messageQueue;

    public SimpleMessageSender(LastMessages lastMessages, MessageQueue<BodyMessage> messageQueue) {
        this.lastMessages = lastMessages;
        this.messageQueue = messageQueue;
    }

    /**
     * Working when user is sending message to all others clients
     */
    @Override
    public void sendMessageToClient(String nickname, String message, ChatInterface chatClient) throws IOException {

        String messageWrap = messageWrapper(nickname,message);
        addLastMessage(message);

        for(ChatClient client : chatClient.getAllUsers()){

            if(!client.getNickname().equals(nickname)){

                sendMessage(client,messageWrap);

            }
        }

    }

    /**
     * Send message to chat client
     */
    @Override
    public void sendMessage(ChatClient client, String message) throws IOException {

        BodyMessage bodyMessage = MessageFactory.createBodyMessage(CommandMessages.MESSAGE.getTextCommand(),
                client.getNickname(), message, client.getIp(),client.getPort());

        addMessageQueue(bodyMessage);
    }


    /**
     * Send last messages to chat client
     */
    @Override
    public void sendLastMessages(ChatClient client) throws IOException {

        StringBuilder builder = new StringBuilder();
        for(String lastMessage : lastMessages.getLastMessages()){

            builder.append(lastMessage).append(" \n");
        }

        if (builder.length() != 0){

            builder.deleteCharAt(builder.length() - 1);
            sendMessage(client,builder.toString());
        }

    }

    private String messageWrapper(String nickname, String message){

        return nickname + " : " + message;
    }

    /**
     * Add message to the list of last messages
     */
    private void addLastMessage(String message){
        lastMessages.add(message);
    }

    /**
     * Add request to the queue
     */
    private void addMessageQueue(BodyMessage bodyMessage){
        messageQueue.add(bodyMessage);
    }
}
