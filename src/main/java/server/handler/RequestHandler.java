package server.handler;

import common.command.Command;
import common.command.CommandsInterface;
import common.enums.Status;
import common.json.bodymessage.BodyMessage;
import common.json.bodymessage.BodyMessageJsonSerializer;
import common.json.serializer.JsonSerializer;
import common.transport.TransportConnection;

import java.io.IOException;

/**
 * This class is responsible for handling incoming requests from a TransportConnection.
 * It receives a message from the connection, deserializes it to a BodyMessage object,
 * and executes the command specified in the BodyMessage using the CommandsInterface.
 * It then sends back the status of the command execution to the client.
 */
public class RequestHandler implements MessageHandler<TransportConnection>{

    private CommandsInterface commands;
    private JsonSerializer<BodyMessage> jsonSerializer;

    /**
     * Constructor for RequestHandler class.
     * @param commands The interface containing the available commands to be executed.
     */
    public RequestHandler(CommandsInterface commands) {
        this.commands = commands;
        this.jsonSerializer = new BodyMessageJsonSerializer();
    }

    /**
     * Handles the incoming request from the TransportConnection.
     * It deserializes the received message, executes the command, and sends back the status of the command execution.
     *
     * @param transportConnection The connection from which the request is received.
     */
    @Override
    public void handle(TransportConnection transportConnection) {

        Status status = Status.ERROR;
        try {

            //get the message from connection
            String message = transportConnection.receive();
            BodyMessage bodyMessage = jsonSerializer.deserialize(message);
            String ip = transportConnection.getIp();
            bodyMessage.setIp(ip);
            //check system commands
             if(commands.contains(bodyMessage.getCommand())){

                 Command command = commands.getByName(bodyMessage.getCommand());
                 status = command.execute(bodyMessage);
             }
        }catch (IOException e){

            status = Status.ERROR;
            System.err.println("Error processing request");
        }finally {
            try {
                transportConnection.send(status.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
