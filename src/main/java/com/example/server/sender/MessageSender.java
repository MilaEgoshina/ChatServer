package com.example.server.sender;

import com.example.server.client.ChatClient;
import com.example.server.client.ChatInterface;

import java.io.IOException;

/**
 * Interface for sending messages to clients in a chat application.
 */
public interface MessageSender {

    /**
     * Sends a message to a specific client identified by their nickname.
     *
     * @param nickname The nickname of the client to whom the message is sent.
     * @param message The message to be sent to the client.
     * @param chatClient The ChatInterface managing the client connections.
     * @throws IOException if an I/O error occurs while sending the message.
     */
    void sendMessageToClient(String nickname, String message, ChatInterface chatClient) throws IOException;

    /**
     * Sends a message to a specific client.
     *
     * @param client The ChatClient to whom the message is sent.
     * @param message The message to be sent to the client.
     * @throws IOException if an I/O error occurs while sending the message.
     */
    void sendMessage(ChatClient client, String message) throws IOException;

    /**
     * Sends the last messages to a specific client upon connecting.
     *
     * @param client The ChatClient to whom the last messages are sent.
     * @throws IOException if an I/O error occurs while sending the last messages.
     */
    void sendLastMessages(ChatClient client) throws IOException;

}
