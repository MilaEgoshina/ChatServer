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
 * Class responsible for handling message sending functionality within a server application.
 */
public class SimpleMessageSender implements MessageSender{

    private LastMessages lastMessages;
    private MessageQueue<BodyMessage> messageQueue;

    /**
     * Constructor for SimpleMessageSender.
     *
     * @param lastMessages The instance of LastMessages for managing the last messages.
     * @param messageQueue The message queue for storing and processing BodyMessages.
     */
    public SimpleMessageSender(LastMessages lastMessages, MessageQueue<BodyMessage> messageQueue) {
        this.lastMessages = lastMessages;
        this.messageQueue = messageQueue;
    }

    /**
     * Sends a message from a user to all other clients except the sender.
     *
     * @param nickname The nickname of the user sending the message.
     * @param message The message content to be sent.
     * @param chatClient The ChatInterface containing information about connected clients.
     * @throws IOException if an I/O error occurs during message sending.
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
     * Sends a message to a specific chat client.
     *
     * @param client The ChatClient to whom the message is sent.
     * @param message The message content to be sent.
     * @throws IOException if an I/O error occurs during message sending.
     */
    @Override
    public void sendMessage(ChatClient client, String message) throws IOException {

        BodyMessage bodyMessage = MessageFactory.createBodyMessage(CommandMessages.MESSAGE.getTextCommand(),
                client.getNickname(), message, client.getIp(),client.getPort());

        addMessageQueue(bodyMessage);
    }


    /**
     * Sends the last messages to a specific client upon connecting.
     *
     * @param client The ChatClient to whom the last messages are sent.
     * @throws IOException if an I/O error occurs during message sending.
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

    /**
     * Creates a formatted message by concatenating the nickname and message content.
     *
     * @param nickname The nickname of the sender.
     * @param message The message content.
     * @return The formatted message string.
     */
    private String messageWrapper(String nickname, String message){

        return nickname + " : " + message;
    }

    /**
     * Adds a message to the list of last messages.
     *
     * @param message The message to be added to the last messages list.
     */
    private void addLastMessage(String message){
        lastMessages.add(message);
    }

    /**
     * Adds a message to the message queue for processing.
     *
     * @param bodyMessage The BodyMessage object to be added to the message queue.
     */
    private void addMessageQueue(BodyMessage bodyMessage){
        messageQueue.add(bodyMessage);
    }
}
