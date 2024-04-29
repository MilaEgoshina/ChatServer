package common.transport;


import java.io.IOException;

/**
 * The TransportListener interface defines methods for managing a transport listener that accepts connections.
 */
public interface TransportListener {

    /**
     * Accept a connection on the listener and return a TransportConnection object for communication.
     *
     * @return a TransportConnection object for the accepted connection
     * @throws IOException if an error occurs while accepting the connection
     */
    TransportConnection accept() throws IOException;

    /**
     * Close the transport listener and release any allocated resources.
     * @throws IOException if an error occurs while closing the listener
     */
    void close() throws IOException;

}
