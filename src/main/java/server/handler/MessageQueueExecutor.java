package server.handler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Class for processing message queue via ThreadPoolExecutor
 */
public class MessageQueueExecutor<T> implements MessageQueue<T>{
    ThreadPoolExecutor threadPoolExecutor;
    MessageHandler<T> messageHandler;

    BlockingQueue<T> messageQueue = new LinkedBlockingQueue<>();

    public MessageQueueExecutor(int minThreads, int maxThreads, MessageHandler<T> messageHandler){

        this.messageHandler = messageHandler;
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

        //Creates a new ThreadPoolExecutor with the given initial parameters, the default thread factory and
        // the default rejected execution handler.
        threadPoolExecutor = new ThreadPoolExecutor(minThreads,maxThreads, 1, TimeUnit.MINUTES, queue);
    }

    /**
     * Add message - request to queue
     * @param message
     */
    @Override
    public void add(T message) {

        messageQueue.add(message);
        //Executes the given task sometime in the future
        threadPoolExecutor.execute(new MessageHandlerRunnable(message,messageHandler));
    }

    @Override
    public T getNextMessage() {

        try {
            return messageQueue.take();
        } catch (InterruptedException e) {
            return null;
        }

    }

    /**
     * Stop processing of queue
     */
    @Override
    public void shutdown() {

        //Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted.
        threadPoolExecutor.shutdown();
    }

    /**
     * Return number of threads in pool
     */
    @Override
    public int getCountThreads() {
        return threadPoolExecutor.getPoolSize();
    }
}
