package server.handler;

/**
 * Class - implementation of message processing as a usual Thread.
 */
public class SimpleHandleThread<T> extends Thread implements HandleThread{

    private MessageQueue<T> queue;

    private boolean isProcessing = false;

    MessageHandler<T> messageHandler;

    /**
     * Constructor for SimpleHandleThread.
     *
     * @param messageQueue The message queue for storing incoming messages.
     * @param messageHandler The message handler to process the messages.
     */
    public SimpleHandleThread(MessageQueue<T> messageQueue, MessageHandler<T> messageHandler){

        this.queue = messageQueue;
        this.messageHandler = messageHandler;
    }

    /**
     * Check if the thread is currently processing messages.
     *
     * @return true if the thread is actively processing messages.
     */
    @Override
    public boolean isProcessing() {
        return this.isProcessing;
    }

    /**
     * Stop the execution of the current thread.
     */
    @Override
    public void killThread() {
        this.interrupt();
    }

    /**
     * Main processing loop of the thread.
     * It continuously retrieves and processes messages from the message queue until interrupted.
     */
    public void run(){

        while (!isInterrupted()){

            T message = queue.getNextMessage();
            if(!isInterrupted() && message != null){

                this.isProcessing = true;
                messageHandler.handle(message);
                this.isProcessing = false;
            }
        }
    }
}
