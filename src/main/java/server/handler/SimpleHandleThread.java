package server.handler;

/**
 * Class - implementation of message processing as a usual Thread
 */
public class SimpleHandleThread<T> extends Thread implements  HandleThread{

    private MessageQueue<T> queue;

    private boolean isProcessing = false;

    MessageHandler<T> messageHandler;

    public SimpleHandleThread(MessageQueue<T> messageQueue, MessageHandler<T> messageHandler){

        this.queue = messageQueue;
        this.messageHandler = messageHandler;
    }

    /**
     * @return true if the process is working
     */
    @Override
    public boolean isProcessing() {
        return this.isProcessing;
    }

    /**
     * Kill the current thread
     */
    @Override
    public void killThread() {
        this.interrupt();
    }

    /**
     * Thread processing
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
