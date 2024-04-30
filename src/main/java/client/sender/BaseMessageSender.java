package client.sender;

import common.Service;
import common.enums.Status;
import common.json.bodymessage.BodyMessage;
import common.json.bodymessage.BodyMessageJsonSerializer;
import common.json.serializer.JsonSerializer;
import common.transport.TransportConnection;
import common.transport.TransportFactory;

import java.io.IOException;

/**
 * The BaseMessageSender class implements the MessageSender interface for sending messages with a body message payload.
 */
public class BaseMessageSender implements MessageSender{

    private String ipServer;
    private int serverPort;
    private TransportFactory transportFactory;
    private JsonSerializer<BodyMessage> jsonSerializer;

    /**
     * Constructs a BaseMessageSender with the specified transport factory, server IP, and port number.
     *
     * @param transportFactory the TransportFactory for creating connections
     * @param ipServer the IP address of the server
     * @param serverPort the port number of the server
     */
    public BaseMessageSender(TransportFactory transportFactory, String ipServer, int serverPort) {
        this.transportFactory = transportFactory;
        this.ipServer = ipServer;
        this.serverPort = serverPort;
        this.jsonSerializer = new BodyMessageJsonSerializer();
    }

    /**
     * Sends a message with the specified BodyMessage payload to the server.
     *
     * @param bodyMessage the BodyMessage payload to send
     * @return true if the message was sent successfully, false otherwise
     * @throws IOException if an error occurs during communication
     */
    @Override
    public boolean sendMessage(BodyMessage bodyMessage) throws IOException {

        String bodyMessageString = jsonSerializer.serialize(bodyMessage);
        TransportConnection transportConnection = transportFactory.createConnection(ipServer,serverPort,
                Service.getInstance().getEncoding());
        transportConnection.send(bodyMessageString);

        Status status = Status.valueOf(transportConnection.receive());
        transportConnection.close();
        return status == Status.SUCCESS;
    }
}
