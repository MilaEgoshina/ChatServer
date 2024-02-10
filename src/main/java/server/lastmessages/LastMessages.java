package server.lastmessages;


import java.util.Collection;

/**
 * Interface for working with last messages list
 */
public interface LastMessages {

    void clear();

    void add(String message);

    Collection<String> getLastMessages();


}
