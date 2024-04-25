package server.handler;


/**
 * This interface defines thread for processing message from queue.
 */
public interface HandleThread {

    /**
     * Checks if the thread is currently processing.
     *
     * @return true if the thread is processing, false otherwise.
     */
    boolean isProcessing();

    /**
     * Stops the thread execution.
     */
    void killThread();

    /**
     * Starts the thread execution.
     */
    void start();
}
