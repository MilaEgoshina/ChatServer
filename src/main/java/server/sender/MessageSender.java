package server.sender;


import server.client.ChatClient;
import server.client.ChatInterface;

import java.io.IOException;

/**
 * Interface for working with messages
 */
public interface MessageSender {

    void sendMessageToClient(String nickname, String message, ChatInterface chatClient) throws IOException;

    void sendMessage(ChatClient client, String message) throws IOException;

    void sendLastMessages(ChatClient client) throws IOException;

}
