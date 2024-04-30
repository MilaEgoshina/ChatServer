package common.input.app;

import client.app.BaseClientListener;
import common.Service;
import common.listener.Listener;
import common.stream.StreamIO;
import common.transport.TransportConnection;
import common.transport.TransportFactory;
import common.transport.TransportListener;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BaseClientListenerTest {

    //a specific set of methods must be called + test printed
    @Test
    public void testRun() throws Exception {
        StreamIO streamIO = mock(StreamIO.class);
        TransportFactory transportFactory = mock(TransportFactory.class);
        TransportListener transportListener = mock(TransportListener.class);
        when(transportFactory.createListener(59989, Service.getInstance().getMaxCountConnections(),
                Service.getInstance().getEncoding())).thenReturn(transportListener);
        TransportConnection transportConnection = mock(TransportConnection.class);
        when(transportListener.accept()).thenReturn(transportConnection);
        when(transportConnection.receive()).thenReturn("test");

        Listener listener = new BaseClientListener(59989, streamIO, transportFactory);
        listener.setDaemon(true);
        listener.start();
        Thread.sleep(1000);
        listener.interrupt();

        verify(streamIO, atLeast(1)).print("test");
        verify(transportConnection, atLeast(1)).close();
        verify(transportListener).close();
    }
}
