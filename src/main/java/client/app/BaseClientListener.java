package client.app;

import common.listener.BaseListener;
import common.listener.Listener;
import common.stream.StreamIO;
import common.transport.TransportConnection;
import common.transport.TransportFactory;

import java.io.IOException;

/**
 * Incoming message listener class
 */
public class BaseClientListener extends BaseListener implements Listener {

    public BaseClientListener(int portIn, StreamIO streamIO, TransportFactory transportFactory) {
        super(portIn, streamIO, transportFactory);
    }

    /**
     * Listen and receive messages
     */
    @Override
    protected void worker() throws IOException {

        TransportConnection transportConnection = transportListener.accept();
        String message = transportConnection.receive();
        streamIO.print(message);
        transportConnection.close();
    }
}
