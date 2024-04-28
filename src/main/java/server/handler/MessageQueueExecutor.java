package server.handler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The MessageQueueExecutor class implements a message queue using a ThreadPoolExecutor
 * to handle incoming messages concurrently. Messages are added to the queue and processed
 * by a MessageHandler in separate threads.
 *
 * @param <T> the type of messages to be handled
 */
public class MessageQueueExecutor<T> implements MessageQueue<T>{
    ThreadPoolExecutor threadPoolExecutor;  // The ThreadPoolExecutor used to handle message processing
    MessageHandler<T> messageHandler; // The MessageHandler used to process messages

    BlockingQueue<T> messageQueue = new LinkedBlockingQueue<>();// The queue to store incoming messages

    /**
     * Constructor for creating a MessageQueueExecutor instance with the given minimum and maximum threads
     * and a MessageHandler.
     *
     * @param minThreads the minimum number of threads to keep in the pool
     * @param maxThreads the maximum number of threads to allow in the pool
     * @param messageHandler the MessageHandler to process incoming messages
     */
    public MessageQueueExecutor(int minThreads, int maxThreads, MessageHandler<T> messageHandler){

        this.messageHandler = messageHandler;
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

        //Creates a new ThreadPoolExecutor with the given initial parameters, the default thread factory and
        // the default rejected execution handler.
        threadPoolExecutor = new ThreadPoolExecutor(minThreads,maxThreads, 1, TimeUnit.MINUTES, queue);
    }

    /**
     * Add a message to the queue to be processed by the ThreadPoolExecutor.
     *
     * @param message the message to be added to the queue
     */
    @Override
    public void add(T message) {

        messageQueue.add(message);
        //Executes the given task sometime in the future
        threadPoolExecutor.execute(new MessageHandlerRunnable(message,messageHandler));
    }

    /**
     * Get the next message from the queue to be processed.
     *
     * @return the next message in the queue
     */
    @Override
    public T getNextMessage() {

        try {
            return messageQueue.take();
        } catch (InterruptedException e) {
            return null;
        }

    }

    /**
     * Shutdown the ThreadPoolExecutor, preventing new tasks from being accepted.
     */
    @Override
    public void shutdown() {

        //Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted.
        threadPoolExecutor.shutdown();
    }

    /**
     * Get the current number of threads in the ThreadPoolExecutor.
     *
     * @return the number of threads in the thread pool
     */
    @Override
    public int getCountThreads() {
        return threadPoolExecutor.getPoolSize();
    }
}
