package common.transport;


import java.io.IOException;

/**
 * The TransportConnection interface defines methods for managing a connection for sending and receiving messages.
 */
public interface TransportConnection {

    /**
     * Get the IP address of the connection.
     *
     * @return the IP address of the connection
     * @throws IOException if an error occurs while retrieving the IP address
     */
    String getIp() throws IOException;

    /**
     * Receive a message from the connection.
     *
     * @return the received message
     * @throws IOException if an error occurs while receiving the message
     */
    String receive() throws IOException;

    /**
     * Send a message through the connection.
     *
     * @param message the message to send
     * @throws IOException if an error occurs while sending the message
     */
    void send(String message) throws IOException;

    /**
     * Close the connection.
     * @throws IOException if an error occurs while closing the connection
     */
    void close() throws IOException;
}
