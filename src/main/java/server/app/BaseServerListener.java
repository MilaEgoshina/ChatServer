package server.app;

import common.listener.BaseListener;
import common.listener.Listener;
import common.stream.StreamIO;
import common.transport.TransportConnection;
import common.transport.TransportFactory;
import server.handler.MessageQueue;

import java.io.IOException;

/**
 * Class - listener for incoming messages
 */
public class BaseServerListener extends BaseListener implements Listener {

    MessageQueue<TransportConnection> requestQueue;

    public BaseServerListener(int portIn, StreamIO streamIO, TransportFactory transportFactory,
                              MessageQueue<TransportConnection> requestQueue) {
        super(portIn, streamIO, transportFactory);
        this.requestQueue = requestQueue;
    }

    @Override
    protected void worker() throws IOException {

        requestQueue.add(transportListener.accept());
    }
}
