package common.transport;


import java.io.IOException;

/**
 * Interface for server - listener
 */
public interface TransportListener {

    TransportConnection accept() throws IOException;

    void close() throws IOException;

}
