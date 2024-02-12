package common.transport.tcp;


import common.transport.TransportConnection;
import common.transport.TransportListener;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Class for server - listener implementation via TCP Socket
 */
public class TcpSocketTransportListener implements TransportListener {

    private ServerSocket serverSocket;

    private String encoding;


    public TcpSocketTransportListener(int port, int maxCountConnections, String encoding) throws IOException{

        this.serverSocket = new ServerSocket(port,maxCountConnections);
        this.encoding = encoding;
    }

    /**
     * Method to make server listen for incoming connections from clients
     * @return new tcp connections
     */
    @Override
    public TransportConnection accept() throws IOException {
        return new TcpSocketTransportConnection(serverSocket.accept(),encoding);
    }

    /**
     * Free all resources
     */
    @Override
    public void close() throws IOException {

        serverSocket.close();
    }
}
