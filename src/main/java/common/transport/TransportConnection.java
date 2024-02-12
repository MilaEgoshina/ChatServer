package common.transport;


import java.io.IOException;

/**
 * Interface for clients connection
 */
public interface TransportConnection {

    String getIp() throws IOException;

    String receive() throws IOException;

    void send(String message) throws IOException;

    void close() throws IOException;
}
