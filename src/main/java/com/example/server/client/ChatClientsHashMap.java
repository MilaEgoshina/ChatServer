package com.example.server.client;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Class - collection for working with a list of chat users via ConcurrentHashMap.
 */
public class ChatClientsHashMap implements ChatInterface{

    private Map<String,ChatClient> chatClients = new ConcurrentHashMap<>();

    /**
     * Add new user to the chat
     * @param client new client
     */
    @Override
    public void addUser(ChatClient client) {
        chatClients.put(client.getNickname(),client);
    }

    /**
     * Remove user from thw current chat
     * @param nickname of client in chat
     */
    @Override
    public void removeUser(String nickname) {

        chatClients.remove(nickname);
    }

    /**
     * Check if there is such client in chat
     * @param nickname of client in chat
     * @return true if needed client is present
     */
    @Override
    public boolean containsUser(String nickname) {
        return chatClients.containsKey(nickname);
    }

    /**
     * Search user by his name
     * @param nickname of client
     * @return instance of ChatClient class
     */
    @Override
    public ChatClient getUser(String nickname) {
        return chatClients.get(nickname);
    }

    /**
     * Get list of all clients in the chat
     * @return collection of all users
     */
    @Override
    public Collection<ChatClient> getAllUsers() {
        return chatClients.values();
    }

    /**
     * Get the number of users in the chat
     * @return number of users
     */
    @Override
    public int countUsers() {
        return chatClients.size();
    }

    /**
     * Clear all the collection of clients in the chat
     */
    @Override
    public void clear() {
        chatClients.clear();
    }
}
