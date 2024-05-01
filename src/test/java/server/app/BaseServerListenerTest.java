package server.app;

import com.example.server.app.BaseServerListener;
import com.example.common.Service;
import com.example.common.listener.Listener;
import com.example.common.stream.StreamIO;
import com.example.common.transport.TransportConnection;
import com.example.common.transport.TransportFactory;
import com.example.common.transport.TransportListener;
import org.junit.Test;
import com.example.server.handler.MessageQueue;

import static org.mockito.Mockito.*;

public class BaseServerListenerTest {

    @Test
    public void testRun() throws Exception {
        MessageQueue messageQueue = mock(MessageQueue.class);
        StreamIO streamIO = mock(StreamIO.class);
        TransportFactory transportFactory = mock(TransportFactory.class);
        TransportListener transportListener = mock(TransportListener.class);
        when(transportFactory.createListener(59989, Service.getInstance().getMaxCountConnections(),
                Service.getInstance().getEncoding())).thenReturn(transportListener);
        TransportConnection transportConnection = mock(TransportConnection.class);
        when(transportListener.accept()).thenReturn(transportConnection);

        Listener listener = new BaseServerListener(59989, streamIO, transportFactory, messageQueue);
        listener.setDaemon(true);
        listener.start();
        Thread.sleep(1000);
        listener.interrupt();

        verify(messageQueue, atLeast(1)).add(any(TransportConnection.class));
        verify(transportListener).close();
    }
}
