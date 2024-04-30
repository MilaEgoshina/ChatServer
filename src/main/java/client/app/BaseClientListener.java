package client.app;

import common.listener.BaseListener;
import common.listener.Listener;
import common.stream.StreamIO;
import common.transport.TransportConnection;
import common.transport.TransportFactory;

import java.io.IOException;

/**
 * The BaseClientListener is a subclass of BaseListener that handles the communication with clients in the chat application.
 */
public class BaseClientListener extends BaseListener implements Listener {

    /**
     * Constructs a BaseClientListener with the specified input port, StreamIO, and TransportFactory.
     *
     * @param portIn the input port number to listen for incoming connections
     * @param streamIO the StreamIO object for handling input and output streams
     * @param transportFactory the TransportFactory object for creating a listener
     */
    public BaseClientListener(int portIn, StreamIO streamIO, TransportFactory transportFactory) {
        super(portIn, streamIO, transportFactory);
    }

    /**
     * Continuously waits for incoming messages from clients, prints the message, and closes the connection.
     * @throws IOException if an error occurs while receiving or handling the message
     */
    @Override
    protected void worker() throws IOException {

        TransportConnection transportConnection = transportListener.accept();
        String message = transportConnection.receive();
        streamIO.print(message);
        transportConnection.close();
    }
}
