package com.example.server.app;

import com.example.server.client.ChatInterface;
import com.example.server.handler.MessageQueue;
import com.example.server.handler.MessageQueueExecutor;
import com.example.server.handler.RequestHandler;
import com.example.server.handler.ResponseHandler;
import com.example.server.lastmessages.CircularFifoBufferLastMessages;
import com.example.server.lastmessages.LastMessages;
import com.example.server.sender.MessageSender;
import com.example.server.sender.SimpleMessageSender;
import com.example.common.Service;
import com.example.common.command.CommandFactory;
import com.example.common.command.CommandsInterface;
import com.example.common.enums.CommandMessages;
import com.example.common.json.bodymessage.BodyMessage;
import com.example.common.listener.Listener;
import com.example.common.stream.StreamIO;
import com.example.common.transport.TransportConnection;
import com.example.common.transport.TransportFactory;
import com.example.server.Server;
import com.example.server.client.ChatClientsHashMap;

/**
 * The BaseServer class implements the Server interface and represents the core server application.
 */
public class BaseServer implements Server {

    private LastMessages lastMessages; //list of last messages
    private ChatInterface chatInterface; //users in the chat
    private StreamIO streamIO; //interface for working with input/output
    private boolean stopped = false; //if server is stopped

    //server input and output queues
    MessageQueue<TransportConnection> requestQueue;
    MessageQueue<BodyMessage> responseQueue;

    private Listener listener;

    /**
     * Constructor for BaseServer.
     *
     * @param port The port on which the server will listen for connections.
     * @param streamIO The StreamIO object for handling input/output operations.
     * @param transportFactory The TransportFactory for creating TransportConnections.
     */
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
        requestQueue = new MessageQueueExecutor<TransportConnection>(service.getMaxThreadsRequest(),
                service.getMaxThreadsRequest(),new RequestHandler(commands));

        //listener of incoming messages
        listener = new BaseServerListener(port,streamIO,transportFactory,requestQueue);

    }

    /**
     * Get the system and client commands for the server.
     *
     * @param messageSender The MessageSender object for sending messages.
     * @param chatInterface The ChatInterface for managing clients in the chat.
     * @return The combined CommandsInterface for system and client commands.
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
