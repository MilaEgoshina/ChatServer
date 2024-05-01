package com.example.client.app;

import com.example.client.Client;
import com.example.client.sender.MessageSender;
import com.example.common.enums.CommandMessages;
import com.example.common.json.bodymessage.BodyMessage;
import com.example.common.json.serializer.MessageFactory;
import com.example.common.listener.Listener;
import com.example.common.stream.StreamIO;

import java.io.IOException;

/**
 * The BaseClient class implements the Client interface and represents a basic client working with a chat application.
 */
public class BaseClient implements Client {

    StreamIO streamIO; //interface for input - output processing
    Listener listener; //listener for input messages from thread
    MessageSender messageSender;
    boolean stopped = false; // indicator of whether client is working or not

    //parameters of current client
    int clientPort;
    String nickname;

    /**
     * Constructs a BaseClient with the specified client port, message sender, stream IO, and listener.
     *
     * @param clientPort the port number of the client
     * @param messageSender the message sender for sending messages
     * @param streamIO the stream IO interface for input-output processing
     * @param listener the listener for input messages from the thread
     */
    public BaseClient(int clientPort, MessageSender messageSender, StreamIO streamIO, Listener listener) {
        this.clientPort = clientPort;
        this.messageSender = messageSender;
        this.streamIO = streamIO;
        this.listener = listener;
    }

    /**
     * Starts the work of the client, including setting up the listener and reading user input.
     */
    @Override
    public void start() {

        try {
            streamIO.print("Welcome to the chat! ");
            startListener(); // launch of listener

            // start reading of user input
            String message;
            while (true){

                message = streamIO.read();
                if((stopped) || (message.equals(CommandMessages.EXIT.getTextCommand()))){
                    break;
                }
                sendMessage(message);
            }
            logoutAndStop();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Losing connection to the server");
        }
    }

    /**
     * Logs in with the specified nickname and client port.
     *
     * @param nickname the nickname of the client
     * @return true if the login is successful, false otherwise
     * @throws IOException if an error occurs during login
     */
    @Override
    public boolean login(String nickname) throws IOException {

        this.nickname = nickname;
        BodyMessage bodyMessage = MessageFactory.createBodyMessage(CommandMessages.LOGIN.getTextCommand(),
                nickname, String.valueOf(clientPort), "",clientPort);
        return messageSender.sendMessage(bodyMessage);
    }

    /**
     * Logs out and stops the client by sending an exit command and interrupting the listener.
     * @throws IOException if an error occurs during logout
     */
    @Override
    public void logoutAndStop() throws IOException {

        this.stopped = true;
        sendMessage(CommandMessages.EXIT.getTextCommand());
        listener.interrupt();
    }

    /**
     * Sends a message to the server with the client's nickname and message content.
     * @param message the message content to send
     * @throws IOException if an error occurs during message sending
     */
    @Override
    public void sendMessage(String message) throws IOException {

        BodyMessage bodyMessage = MessageFactory.createBodyMessage(CommandMessages.MESSAGE.getTextCommand(),
                nickname,message, "" ,clientPort);
        sendBodyMessage(bodyMessage);
    }

    /**
     * Sends a body message to the server.
     * @param bodyMessage the body message to send
     * @throws IOException if an error occurs during message sending
     */
    private void sendBodyMessage(BodyMessage bodyMessage) throws IOException{

        if(!messageSender.sendMessage(bodyMessage)){

            System.err.println("This message was not processed on the server");
        }
    }

    /**
     * Launch the input message listener
     */
    @Override
    public void startListener() {

        listener.setDaemon(true);
        listener.start();
    }
}
