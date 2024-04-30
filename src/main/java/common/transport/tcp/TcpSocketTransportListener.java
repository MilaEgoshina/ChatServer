package common.transport.tcp;


import common.transport.TransportConnection;
import common.transport.TransportListener;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * The TcpSocketTransportListener class implements the TransportListener interface for managing a TCP socket listener.
 */
public class TcpSocketTransportListener implements TransportListener {

    private ServerSocket serverSocket;

    private String encoding;

    /**
     * Constructs a TcpSocketTransportListener with the specified port number, maximum count of connections, and encoding.
     *
     * @param port the port number to listen on
     * @param maxCountConnections the maximum number of connections allowed
     * @param encoding the encoding used for communication
     * @throws IOException if an error occurs during initialization
     */
    public TcpSocketTransportListener(int port, int maxCountConnections, String encoding) throws IOException{

        this.serverSocket = new ServerSocket(port,maxCountConnections);
        this.encoding = encoding;
    }

    /**
     * Accepts a connection on the listener and creates a new TcpSocketTransportConnection for communication.
     * @return a TransportConnection object for the accepted connection
     * @throws IOException if an error occurs while accepting the connection
     */
    @Override
    public TransportConnection accept() throws IOException {
        return new TcpSocketTransportConnection(serverSocket.accept(),encoding);
    }

    /**
     * Closes the server socket of the transport listener.
     * @throws IOException if an error occurs while closing the listener
     */
    @Override
    public void close() throws IOException {

        serverSocket.close();
    }
}
