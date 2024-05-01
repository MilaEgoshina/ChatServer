package common.input.app;

import com.example.client.Client;
import com.example.client.app.BaseClient;
import com.example.client.sender.MessageSender;
import com.example.common.enums.CommandMessages;
import com.example.common.json.bodymessage.BodyMessage;
import com.example.common.listener.Listener;
import com.example.common.stream.StreamIO;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BaseClientTest {

    StreamIO streamIO;
    MessageSender messageSender;
    Listener listener;
    BodyMessage bodyMessage;

    @Before
    public void setUp() throws Exception {
        streamIO = mock(StreamIO.class);
        messageSender = mock(MessageSender.class);
        listener = mock(Listener.class);
        bodyMessage = mock(BodyMessage.class);
    }

    @Test
    public void testStart() throws Exception {
        when(streamIO.read()).thenReturn(CommandMessages.EXIT.getTextCommand());
        when(messageSender.sendMessage(any(BodyMessage.class))).thenReturn(true);
        Client client = new BaseClient(59989, messageSender, streamIO, listener);

        client.start();

        verify(listener).setDaemon(true);
        verify(listener).start();
        verify(messageSender,atLeastOnce()).sendMessage(any(BodyMessage.class));
    }

    @Test
    public void testLogin() throws Exception {
        Client client = new BaseClient(59989, messageSender, streamIO, listener);

        client.login("Jack");

        verify(messageSender).sendMessage(any(BodyMessage.class));
    }

    @Test
    public void testLogoutAndStop() throws Exception {
        when(messageSender.sendMessage(any(BodyMessage.class))).thenReturn(true);
        Client client = new BaseClient(59989, messageSender, streamIO, listener);

        client.logoutAndStop();

        verify(messageSender).sendMessage(any(BodyMessage.class));
        verify(listener).interrupt();
    }

    @Test
    public void testSendMessage() throws Exception {
        when(messageSender.sendMessage(any(BodyMessage.class))).thenReturn(true);
        Client client = new BaseClient(59989, messageSender, streamIO, listener);

        client.sendMessage("test");

        verify(messageSender).sendMessage(any(BodyMessage.class));
    }
}
