package client.sender;

import common.Service;
import common.enums.Status;
import common.json.bodymessage.BodyMessage;
import common.json.bodymessage.BodyMessageJsonSerializer;
import common.json.serializer.JsonSerializer;
import common.transport.TransportConnection;
import common.transport.TransportFactory;

import java.io.IOException;

public class BaseMessageSender implements MessageSender{


    private String ipServer;
    private int serverPort;
    private TransportFactory transportFactory;
    private JsonSerializer<BodyMessage> jsonSerializer;

    public BaseMessageSender(String ipServer, int serverPort, TransportFactory transportFactory) {
        this.ipServer = ipServer;
        this.serverPort = serverPort;
        this.transportFactory = transportFactory;
        this.jsonSerializer = new BodyMessageJsonSerializer();
    }

    /**
     * Method for creating command to server
     * @param bodyMessage message from client
     * @return true if command was created successfully
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
