package com.example.common.listener;


import com.example.common.Service;
import com.example.common.stream.StreamIO;
import com.example.common.transport.TransportFactory;
import com.example.common.transport.TransportListener;

import java.io.IOException;

/**
 * BaseListener class implements a basic listener functionality for receiving and sending messages.
 * It extends Thread class and implements the Listener interface.
 */
public abstract class BaseListener extends Thread implements Listener{

    protected TransportListener transportListener;
    protected StreamIO streamIO;

    /**
     * Constructor for BaseListener class.
     *
     * @param portIn the input port number to listen for incoming connections
     * @param streamIO the StreamIO object for handling input and output streams
     * @param transportFactory the TransportFactory object for creating a listener
     */
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
     * Continuously waits for incoming messages and processes them using the worker method.
     */
    public void run(){

        try {
            while (!interrupted()){

                worker();
            }
            transportListener.close();

        }catch (IOException e){

            System.err.println("Error receiving message");
            e.printStackTrace();
        }

    }

    /**
     * Abstract method that must be implemented by subclasses to define the processing of incoming messages.
     * @throws IOException if an error occurs while receiving or processing the message
     */
    protected abstract void worker() throws IOException;
}
