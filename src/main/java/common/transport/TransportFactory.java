package common.transport;


import java.io.IOException;

/**
 * Interface for working with the transport layer
 */
public interface TransportFactory {

    TransportListener createListener(int port, int maxCountConnections, String encoding) throws IOException;

    TransportConnection createConnection(String ip, int port, String encoding) throws IOException;

}
