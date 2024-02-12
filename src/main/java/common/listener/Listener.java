package common.listener;


/**
 * Server - listener interface
 */
public interface Listener {

    void start();

    void interrupt();

    void setDaemon(boolean on);
}
