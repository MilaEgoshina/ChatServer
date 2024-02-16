package server.handler;

/**
 * Interface for processing messages from the queue
 */
public interface MessageHandler<T> {

    void handle(T message);
}
