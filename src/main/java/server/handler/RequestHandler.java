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
 * TCP connection-based request processing class
 */
public class RequestHandler implements MessageHandler<TransportConnection>{

    private CommandsInterface commands;
    private JsonSerializer<BodyMessage> jsonSerializer;

    public RequestHandler(CommandsInterface commands) {
        this.commands = commands;
        this.jsonSerializer = new BodyMessageJsonSerializer();
    }

    /**
     * Method for parsing the message
     * @param transportConnection - message to parse from connection
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
