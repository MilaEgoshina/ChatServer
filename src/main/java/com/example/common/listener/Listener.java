package com.example.common.listener;


/**
 * The Listener interface provides methods for starting, interrupting, and setting a listener as a daemon.
 */
public interface Listener {

    /**
     * Starts the listener.
     */
    void start();

    /**
     * Interrupts the listener.
     */
    void interrupt();

    /**
     * Sets the listener as a daemon if the parameter is true.
     * @param on true to set the listener as a daemon, false otherwise.
     */
    void setDaemon(boolean on);
}
