package server.handler;

import common.Service;
import common.json.bodymessage.BodyMessage;
import common.transport.TransportConnection;
import common.transport.TransportFactory;
import server.client.ChatClient;
import server.client.ChatInterface;

import java.io.IOException;

/**
 * Class for processing of response based on socket - connection
 */
public class ResponseHandler implements MessageHandler<BodyMessage>{

    private ChatInterface chatInterface;
    private TransportFactory transportFactory;

    public ResponseHandler(ChatInterface chatInterface, TransportFactory transportFactory) {
        this.chatInterface = chatInterface;
        this.transportFactory = transportFactory;
    }

    /**
     * Parsing the message
     * @param message
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
