package com.example.server.handler;

/**
 * Interface for processing messages from the queue.
 * Implementations of this interface are responsible for processing incoming messages.
 */
public interface MessageHandler<T> {

    /**
     * Handles the incoming message.
     *
     * @param message the message to be handled
     */
    void handle(T message);
}
