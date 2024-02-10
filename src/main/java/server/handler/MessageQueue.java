package server.handler;

/**
 * Message processing queue interface
 */
public interface MessageQueue<T> {

    void add(T message);

     T getNextMessage();

     void shutdown();

     int getCountThreads();
}
