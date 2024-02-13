package server.app;

import common.Service;
import common.command.CommandFactory;
import common.command.CommandsInterface;
import common.enums.CommandMessages;
import common.json.bodymessage.BodyMessage;
import common.listener.Listener;
import common.stream.StreamIO;
import common.transport.TransportConnection;
import common.transport.TransportFactory;
import server.Server;
import server.client.ChatClientsHashMap;
import server.client.ChatInterface;
import server.handler.MessageQueue;
import server.handler.MessageQueueExecutor;
import server.handler.RequestHandler;
import server.handler.ResponseHandler;
import server.lastmessages.CircularFifoBufferLastMessages;
import server.lastmessages.LastMessages;
import server.sender.MessageSender;
import server.sender.SimpleMessageSender;

import java.io.IOException;

public class BaseServer implements Server {

    private LastMessages lastMessages; //list of last messages
    private ChatInterface chatInterface; //users in the chat
    private StreamIO streamIO; //interface for working with input/output
    private boolean stopped = false; //if server is stopped

    //server input and output queues
    MessageQueue<TransportConnection> requestQueue;
    MessageQueue<BodyMessage> responseQueue;

    private Listener listener;

    public BaseServer(int port, StreamIO streamIO, TransportFactory transportFactory){

        this.streamIO = streamIO;
        Service service = Service.getInstance();

        //list of users
        chatInterface = new ChatClientsHashMap();

        //queue of responses
        responseQueue = new MessageQueueExecutor<BodyMessage>(service.getMaxThreadsResponse(),
                service.getMaxThreadsResponse(),
                new ResponseHandler(chatInterface, transportFactory));

        //list of last messages
        lastMessages = new CircularFifoBufferLastMessages(service.getLastMessagesLength());

        //sender of messages
        MessageSender messageSender = new SimpleMessageSender(lastMessages,responseQueue);

        //list of system commands
        CommandsInterface commands = getCommands(messageSender,chatInterface);

        // queue of requests
        requestQueue = new MessageQueueExecutor<TransportConnection>(service.getMaxThreadsResponse(),
                service.getMaxThreadsResponse(),new RequestHandler(commands));

    }

    /**
     * Method for users commands initialization
     * @param messageSender initialization for message sending
     * @param chatInterface list of users
     * @return list of system commands
     */
    private CommandsInterface getCommands(MessageSender messageSender, ChatInterface chatInterface){

        CommandsInterface clientsCommands = CommandFactory.getCommandsCollection();

        clientsCommands.add(CommandFactory.getExitCommand(messageSender,chatInterface));
        clientsCommands.add(CommandFactory.getHelpCommand(clientsCommands,messageSender,chatInterface));
        clientsCommands.add(CommandFactory.getCountUserCommand(messageSender,chatInterface));

        CommandsInterface systemCommands = CommandFactory.getCommandsCollection();
        systemCommands.add(CommandFactory.getLoginCommand(messageSender,chatInterface));
        systemCommands.add(CommandFactory.getMessageCommand(messageSender,chatInterface,clientsCommands));

        return systemCommands;
    }
    /**
     * Start server
     */
    @Override
    public void start() {

        streamIO.print("Server start");
        startListener();

        String message = "";
        while (!((stopped) || message.equals(CommandMessages.EXIT.getTextCommand()))){
            message = streamIO.read();
        }

        stop();
    }

    /**
     * Stop server
     */
    @Override
    public void stop() {

        stopped = true;
        listener.interrupt();
        closeAll();
        streamIO.print("Server was stopped");
    }

    /**
     * Start waiting for connections
     */
    private void startListener(){

        listener.setDaemon(true);
        listener.start();
    }

    /**
     * Close all threads and free resources
     */
    private void closeAll(){

        try {
            requestQueue.shutdown();
            responseQueue.shutdown();
            lastMessages.clear();
            chatInterface.clear();
        }catch (Exception e){

            e.printStackTrace();
            System.err.println("Connection closed with error.");
        }
    }

}
