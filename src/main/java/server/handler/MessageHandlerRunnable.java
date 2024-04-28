package server.handler;

/**
 * Runnable implementation for handling a message using a MessageHandler.
 * This class provides a way to process a message by passing it to a MessageHandler in a separate thread.
 *
 * @param <T> The type of message to be handled.
 */
public class MessageHandlerRunnable<T> implements Runnable{

    T message;
    MessageHandler<T> messageHandler;

    /**
     * Construct a MessageHandlerRunnable with the specified message and message handler.
     *
     * @param message The message to be handled.
     * @param messageHandler The MessageHandler to handle the message.
     */
    public MessageHandlerRunnable(T message, MessageHandler<T> messageHandler) {
        this.message = message;
        this.messageHandler = messageHandler;
    }

    /**
     * Run method to handle the message using the MessageHandler.
     */
    @Override
    public void run() {

        messageHandler.handle(message);
    }
}
