package com.example.server.handler;

import com.example.common.Service;
import com.example.common.json.bodymessage.BodyMessage;
import com.example.common.transport.TransportConnection;
import com.example.common.transport.TransportFactory;
import com.example.server.client.ChatInterface;

import java.io.IOException;

/**
 * This class is responsible for handling outgoing responses to be sent to clients.
 * It receives a BodyMessage object containing the response message, and uses a ChatInterface
 * and TransportFactory to create a connection and send the response to the specified client.
 */
public class ResponseHandler implements MessageHandler<BodyMessage>{

    private ChatInterface chatInterface;
    private TransportFactory transportFactory;

    /**
     * Constructor for ResponseHandler class.
     *
     * @param chatInterface The interface for managing client communication in the server.
     * @param transportFactory The factory for creating TransportConnections.
     */
    public ResponseHandler(ChatInterface chatInterface, TransportFactory transportFactory) {
        this.chatInterface = chatInterface;
        this.transportFactory = transportFactory;
    }

    /**
     * Handles the outgoing response message to be sent to a client.
     * It creates a TransportConnection using the TransportFactory, and sends the response message to the client.
     * If there is an IOException, it logs an error message and removes the user from the chatInterface.
     *
     * @param message The BodyMessage object containing the response message, IP, and port.
     */
    @Override
    public void handle(BodyMessage message) {

        try {

            TransportConnection transportConnection = transportFactory.createConnection(message.getIp(),message.getPort(),
                    Service.getInstance().getEncoding());
            transportConnection.send(message.getText());
        } catch (IOException e) {
            System.err.println("Break the connection with the client");
            chatInterface.removeUser(message.getNickname());
        }

    }
}
