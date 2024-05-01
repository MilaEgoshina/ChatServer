package common.transport.tcp;

import com.example.common.Service;
import com.example.common.transport.TransportConnection;
import com.example.common.transport.TransportFactory;
import com.example.common.transport.TransportListener;
import com.example.common.transport.tcp.TcpSocketTransportConnection;
import com.example.common.transport.tcp.TcpSocketTransportFactory;
import com.example.common.transport.tcp.TcpSocketTransportListener;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TcpSocketTransportFactoryTest {

    TransportFactory transportFactory;
    TransportListener transportListener;
    TransportConnection transportConnection;

    @Before
    public void setUp() throws Exception {
        transportFactory = new TcpSocketTransportFactory();
    }

    @After
    public void tearDown() throws Exception {
        if (transportListener != null) transportListener.close();
        if (transportConnection != null) transportListener.close();
    }

    @Test
    public void testCreateListener() throws Exception {
        transportListener = transportFactory.createListener(59990, 500, Service.getInstance().getEncoding());

        Assert.assertNotNull(transportListener);
        Assert.assertTrue(transportListener instanceof TcpSocketTransportListener);
    }

    @Test
    public void testCreateConnection() throws Exception {
        transportListener = transportFactory.createListener(59990, 500, Service.getInstance().getEncoding());
        transportConnection = transportFactory.createConnection("127.0.0.1", 60000, Service.getInstance().getEncoding());

        Assert.assertNotNull(transportConnection);
        Assert.assertTrue(transportConnection instanceof TcpSocketTransportConnection);
    }
}
