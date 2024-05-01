package com.example.server.handler;

/**
 * The MessageQueue interface represents a queue for storing and retrieving messages of type T.
 */
public interface MessageQueue<T> {

    /**
     * Adds a message to the queue.
     *
     * @param message the message to be added
     */
    void add(T message);

    /**
     * Gets the next message in the queue.
     *
     * @return the next message in the queue
     */
     T getNextMessage();

    /**
     * Shuts down the message queue, stopping any further messages from being added or retrieved.
     */
     void shutdown();

    /**
     * Gets the number of threads currently accessing the message queue.
     *
     * @return the number of threads accessing the message queue
     */
     int getCountThreads();
}
