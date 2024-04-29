package common.transport;


import java.io.IOException;

/**
 * The TransportFactory interface defines methods for creating transport listeners and connections.
 */
public interface TransportFactory {

    /**
     * Create a transport listener that listens on the specified port with the given maximum count of connections.
     *
     * @param port the port number to listen on
     * @param maxCountConnections the maximum number of connections allowed
     * @param encoding the encoding used for communication
     * @return a TransportListener object
     * @throws IOException if an error occurs during creation
     */
    TransportListener createListener(int port, int maxCountConnections, String encoding) throws IOException;

    /**
     * Create a transport connection to the specified IP address and port with the given encoding.
     *
     * @param ip the IP address to connect to
     * @param port the port number to connect to
     * @param encoding the encoding used for communication
     * @return a TransportConnection object
     * @throws IOException if an error occurs during creation
     */
    TransportConnection createConnection(String ip, int port, String encoding) throws IOException;

}
