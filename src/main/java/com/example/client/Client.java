package com.example.client;


import java.io.IOException;

/**
 * The Client interface defines methods for managing a client in the chat application.
 */
public interface Client {

    /**
     * Starts the client, facilitating communication in the chat application.
     */
    void start();

    /**
     * Logs in the client with the specified nickname.
     *
     * @param nickname the nickname of the client
     * @throws IOException if an error occurs during the login process
     * @return true if the login is successful, false otherwise
     */
    boolean login(String nickname) throws IOException;

    /**
     * Logs out the client from the chat application and stops its operation.
     * @throws IOException if an error occurs during the logout process
     */
    void logoutAndStop() throws IOException;

    /**
     * Sends a message from the client to the chat application server.
     * @param message the message to send
     * @throws IOException if an error occurs during message sending
     */
    void sendMessage(String message) throws IOException;

    /**
     * Starts the listener for incoming messages from the chat application server.
     */
    void startListener();
}
