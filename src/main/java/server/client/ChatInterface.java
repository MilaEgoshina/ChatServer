package server.client;

import java.util.Collection;

/**
 * Interface for working with chat clients
 */

public interface ChatInterface {

    /**
     * Add new user to the chat
     * @param client new client
     */
    void addUser(ChatClient client);

    /**
     * Remove user from the chat
     * @param nickname name of definite client to remove
     */
    void removeUser(String nickname);

    /**
     * Check if client is in the chat
     * @param nickname name of definite client to search for
     * @return true if such client is in the chat
     */
    boolean containsUser(String nickname);

    /**
     * Search and return user from chat
     * @param nickname use client`s nickname to search
     * @return client from the current chat
     */
    ChatClient getUser(String nickname);

    /**
     * Receive the list of all clients in current chat
     * @return collection of all clients
     */
    Collection<ChatClient> getAllUsers();

    /**
     * Count all users in the chat
     * @return total number of clients in chat
     */
    int countUsers();

    /**
     * Clen whole list
     */
    void clear();
}
