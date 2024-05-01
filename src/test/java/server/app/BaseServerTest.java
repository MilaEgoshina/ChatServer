package server.app;

import com.example.server.app.BaseServer;
import com.example.common.enums.CommandMessages;
import com.example.common.stream.StreamIO;
import com.example.common.transport.TransportFactory;
import com.example.common.transport.tcp.TcpSocketTransportFactory;
import org.junit.Test;
import com.example.server.Server;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseServerTest {

    @Test
    public void testStartAndStop() throws Exception {
        StreamIO streamIO = mock(StreamIO.class);
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        when(streamIO.read()).thenReturn(CommandMessages.EXIT.getTextCommand());

        Server server = new BaseServer(59990, streamIO, transportFactory);
        server.start();
    }
}
