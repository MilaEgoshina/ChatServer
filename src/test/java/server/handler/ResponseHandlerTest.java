package server.handler;

import com.example.server.handler.MessageHandler;
import com.example.server.handler.ResponseHandler;
import com.example.common.Service;
import com.example.common.json.bodymessage.BodyMessage;
import com.example.common.json.serializer.MessageFactory;
import com.example.common.transport.TransportConnection;
import com.example.common.transport.TransportFactory;
import com.example.common.transport.tcp.TcpSocketTransportFactory;
import org.junit.Test;
import com.example.server.client.ChatClientsHashMap;
import com.example.server.client.ChatInterface;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResponseHandlerTest {

    @Test
    //in this test we should create TransportConnection and invoke send(String text) method with using BodyMessage - class parameters
    public void testHandle() throws Exception {
        BodyMessage bodyMessage = MessageFactory.createBodyMessage("EXIT","Jack","Bye","127.0.0.1",0);
        TransportFactory transportFactory = mock(TransportFactory.class);
        TransportConnection transportConnection = mock(TransportConnection.class);
        when(transportFactory.createConnection(bodyMessage.getIp(),
                bodyMessage.getPort(), Service.getInstance().getEncoding())).thenReturn(transportConnection);

        ChatInterface chatClients = new ChatClientsHashMap();
        MessageHandler<BodyMessage> messageHandler = new ResponseHandler(chatClients, transportFactory);
        messageHandler.handle(bodyMessage);

        verify(transportConnection).send(bodyMessage.getText());
    }

    @Test
    //If there is a connection error with the user, the user must be removed from the list
    public void testHandle_Exception() throws Exception {
        BodyMessage bodyMessage = MessageFactory.createBodyMessage("EXIT","Jack","Bye","127.0.0.1",0);
        TransportFactory transportFactory = new TcpSocketTransportFactory();

        ChatInterface chatClients = mock(ChatInterface.class);
        MessageHandler<BodyMessage> messageHandler = new ResponseHandler(chatClients, transportFactory);
        messageHandler.handle(bodyMessage);

        verify(chatClients).removeUser(bodyMessage.getNickname());
    }
}
