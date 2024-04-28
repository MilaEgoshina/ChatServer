package server.lastmessages;


import java.util.Collection;

/**
 * The LastMessages interface represents a collection of last messages in a chat application.
 */
public interface LastMessages {

    /**
     * Clears all the last messages from the collection.
     */
    void clear();

    /**
     * Adds a new message to the collection of last messages.
     *
     * @param message The message to be added.
     */
    void add(String message);

    /**
     * Returns a collection of the last messages.
     *
     * @return The collection of last messages.
     */
    Collection<String> getLastMessages();


}
