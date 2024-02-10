package server.handler;


/**
 * Thread for processing message from queue
 */
public interface HandleThread {

    boolean isProcessing();

    void killThread();

    void start();
}
