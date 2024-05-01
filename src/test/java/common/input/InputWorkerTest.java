package common.input;

import com.example.common.input.InputWorker;
import com.example.common.stream.ConsoleStreamIO;
import com.example.common.stream.StreamIO;
import com.example.common.transport.TransportFactory;
import com.example.common.transport.tcp.TcpSocketTransportFactory;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class InputWorkerTest {

    TransportFactory transportFactory;

    private StreamIO getStream(String Value){
        ByteArrayInputStream in = new ByteArrayInputStream(Value.getBytes());
        System.setIn(in);
        return new ConsoleStreamIO(new Scanner((System.in)), System.out);
    }

    @Before
    public void setUp() throws Exception {
        transportFactory = new TcpSocketTransportFactory();
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(System.in);
    }

    @Test
    public void testGetPortServer_Input599891EqualOutput59989_TrueReturned() throws Exception {
        StreamIO streamIO = getStream("59989");
        InputWorker inputWorker = new InputWorker(streamIO, transportFactory);

        int port = inputWorker.getPortServer();

        Assert.assertEquals(port, 59989);
    }

    @Test
    public void testGetIpServer_Input127001EqualOutput127001_TrueReturned() throws Exception {
        StreamIO streamIO = getStream("127.0.0.1");
        InputWorker inputWorker = new InputWorker(streamIO, transportFactory);

        String ip = inputWorker.getIpServer();

        Assert.assertEquals(ip, "127.0.0.1");
    }

    @Test
    public void testGetPortToServer_Input59989EqualOutput59989_TrueReturned() throws Exception {
        StreamIO streamIO = getStream("59989");
        InputWorker inputWorker = new InputWorker(streamIO, transportFactory);

        int port = inputWorker.getPortToServer();

        Assert.assertEquals(port, 59989);
    }

    @Test
    public void testGetPortClient_Input59989EqualOutput59989_TrueReturned() throws Exception {
        StreamIO streamIO = getStream("59989");
        InputWorker inputWorker = new InputWorker(streamIO, transportFactory);

        int port = inputWorker.getPortClient();

        Assert.assertEquals(port, 59989);
    }

    @Test
    public void testGetNickName_InputJackEqualOutputJack_TrueReturned() throws Exception {
        StreamIO streamIO = getStream("Jack");
        InputWorker inputWorker = new InputWorker(streamIO, transportFactory);

        String nickName = inputWorker.getNickName("");

        Assert.assertEquals(nickName, "Jack");
    }
}
