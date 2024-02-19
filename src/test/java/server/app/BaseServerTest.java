package server.app;

import common.enums.CommandMessages;
import common.stream.StreamIO;
import common.transport.TransportFactory;
import common.transport.tcp.TcpSocketTransportFactory;
import org.junit.Test;
import server.Server;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseServerTest {

    @Test
    public void testStartAndStop() throws Exception {
        StreamIO streamIO = mock(StreamIO.class);
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        when(streamIO.read()).thenReturn(CommandMessages.EXIT.getTextCommand());

        Server server = new BaseServer(60000, streamIO, transportFactory);
        server.start();
    }
}
