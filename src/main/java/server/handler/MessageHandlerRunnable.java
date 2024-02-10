package server.handler;

/**
 * Class - implementation of MessageHandler class as a Runnable
 */
public class MessageHandlerRunnable<T> implements Runnable{

    T message;
    MessageHandler<T> messageHandler;

    public MessageHandlerRunnable(T message, MessageHandler<T> messageHandler) {
        this.message = message;
        this.messageHandler = messageHandler;
    }

    @Override
    public void run() {

        messageHandler.handle(message);
    }
}
