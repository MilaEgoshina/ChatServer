package client;


import java.io.IOException;

/**
 * Interface for interaction with client
 */
public interface Client {

    void start();

    boolean login(String nickname) throws IOException;

    void logoutAndStop() throws IOException;

    void sendMessage(String message) throws IOException;

    void startListener();
}
