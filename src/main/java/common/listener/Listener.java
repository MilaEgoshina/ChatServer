package common.listener;


/**
 * Interface to listen for a new coming connections
 */
public interface Listener {

    void start();

    void interrupt();

    void setDaemon(boolean on);
}
