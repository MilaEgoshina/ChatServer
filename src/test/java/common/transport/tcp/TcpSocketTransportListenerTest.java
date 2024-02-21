package common.transport.tcp;

import common.Service;
import common.transport.TransportConnection;
import common.transport.TransportFactory;
import common.transport.TransportListener;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.SocketException;

public class TcpSocketTransportListenerTest {

    Thread thread;
    TransportConnection transportConnection;
    TransportListener transportListener;

    //run the client in a thread
    private Thread getThread(){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    TransportFactory transportFactory = new TcpSocketTransportFactory();
                    transportFactory.createConnection("127.0.0.1",60000, Service.getInstance().getEncoding());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Before
    public void setUp() throws Exception {
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        transportListener = transportFactory.createListener(60000, 1000,
                Service.getInstance().getEncoding());
    }

    @After
    public void tearDown() throws Exception {
        if (thread != null) thread.interrupt();
        if (transportConnection != null) transportConnection.close();
        if (transportListener != null) transportListener.close();
    }

    /**
     * Start the listener and client to connect
     * Accept of listener must receive a non-empty object and type TcpSocketTransportConnection
     */
    @Test
    public void testAccept() throws Exception {
        thread = getThread();
        thread.start();

        transportConnection = transportListener.accept();

        Assert.assertNotNull(transportConnection);
        Assert.assertTrue(transportConnection instanceof TcpSocketTransportConnection);
    }

    @Test(expected = SocketException.class)
    //after closing socket, trying to get a connection should throw a SocketException error
    public void testClose() throws Exception {
        transportListener.close();
        transportListener.accept();
    }
}
