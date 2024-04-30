package common.transport.tcp;

import common.transport.TransportConnection;
import common.transport.TransportFactory;
import common.transport.TransportListener;

import java.io.IOException;

/**
 * The TcpSocketTransportFactory class implements the TransportFactory interface for creating TCP socket
 * listeners and connections.
 */
public class TcpSocketTransportFactory implements TransportFactory {

    /**
     * Create a TCP socket transport listener on the specified port with the given maximum count of connections and encoding.
     *
     * @param port the port number to listen on
     * @param maxCountConnections the maximum number of connections allowed
     * @param encoding the encoding used for communication
     * @return a TcpSocketTransportListener object
     * @throws IOException if an error occurs during listener creation
     */
    @Override
    public TransportListener createListener(int port, int maxCountConnections, String encoding) throws IOException {
        return new TcpSocketTransportListener(port, maxCountConnections, encoding);
    }

    /**
     * Create a TCP socket transport connection to the specified IP address and port with the given encoding.
     *
     * @param ip the IP address to connect to
     * @param port the port number to connect to
     * @param encoding the encoding used for communication
     * @return a TcpSocketTransportConnection object
     * @throws IOException if an error occurs during connection creation
     */
    @Override
    public TransportConnection createConnection(String ip, int port, String encoding) throws IOException {
        return new TcpSocketTransportConnection(ip, port, encoding);
    }
}
