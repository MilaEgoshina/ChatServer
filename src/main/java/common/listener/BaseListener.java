package common.listener;


import common.Service;
import common.stream.StreamIO;
import common.transport.TransportFactory;
import common.transport.TransportListener;

import java.io.IOException;

/**
 *  Listener class for incoming messages
 */
public abstract class BaseListener extends Thread implements Listener{

    protected TransportListener transportListener;
    protected StreamIO streamIO;

    public BaseListener(int portIn, StreamIO streamIO, TransportFactory transportFactory){

        try {

            this.streamIO = streamIO;
            transportListener = transportFactory.createListener(portIn,
                    Service.getInstance().getMaxCountConnections(),
                    Service.getInstance().getEncoding());

        }catch (IOException e){

            e.printStackTrace();
        }

    }

    /**
     * Waiting for the message and sending it out
     */
    public void run(){

        try {
            while (!interrupted()){

                worker();
            }
            transportListener.close();

        }catch (IOException e){

            System.err.println("Ошибка при получении сообщения");
            e.printStackTrace();
        }

    }

    protected abstract void worker() throws IOException;
}
