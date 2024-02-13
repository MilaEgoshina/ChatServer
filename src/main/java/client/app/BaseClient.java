package client.app;

import client.Client;
import client.sender.MessageSender;
import common.enums.CommandMessages;
import common.json.bodymessage.BodyMessage;
import common.json.serializer.MessageFactory;
import common.listener.Listener;
import common.stream.StreamIO;

import java.io.IOException;

/**
 * Implementation of base client class
 */
public class BaseClient implements Client {

    StreamIO streamIO; //interface for input - output processing
    Listener listener; //listener for input messages from thread
    MessageSender messageSender;
    boolean stopped = false; // indicator of whether client is working or nor


    //parameters of current client
    int clientPort;
    String nickname;

    public BaseClient(StreamIO streamIO, Listener listener, MessageSender messageSender, int clientPort) {
        this.streamIO = streamIO;
        this.listener = listener;
        this.messageSender = messageSender;
        this.clientPort = clientPort;
    }

    /**
     * Start work of client
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
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Losing connection to the server");
        }
    }

    /**
     * Method for authorization
     * @param nickname for user authorization
     * @return true if authorization was successful
     */
    @Override
    public boolean login(String nickname) throws IOException {

        this.nickname = nickname;
        BodyMessage bodyMessage = MessageFactory.createBodyMessage(CommandMessages.LOGIN.getTextCommand(),
                nickname, String.valueOf(clientPort), "",clientPort);
        return messageSender.sendMessage(bodyMessage);
    }

    /**
     * Method for log out from chat and close it
     */
    @Override
    public void logoutAndStop() throws IOException {

        this.stopped = true;
        sendMessage(CommandMessages.EXIT.getTextCommand());
        listener.interrupt();
    }

    /**
     * Method for sending message by client
     * @param message from client
     */
    @Override
    public void sendMessage(String message) throws IOException {

        BodyMessage bodyMessage = MessageFactory.createBodyMessage(CommandMessages.MESSAGE.getTextCommand(),
                nickname,message, "" ,clientPort);
        sendBodyMessage(bodyMessage);
    }

    /**
     * Method for sending a message as an object by the client
     * @param bodyMessage message as an object
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
