package common.transport.tcp;

import common.Service;
import common.transport.TransportConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * The TcpSocketTransportConnection class implements the TransportConnection interface for managing a TCP socket connection.
 */
public class TcpSocketTransportConnection implements TransportConnection {

    private Socket socket;
    private OutputStreamWriter writer;
    private BufferedReader reader;
    private String encoding;

    final String lineSeparator = Service.getInstance().getLineSeparator();

    /**
     * Constructs a TcpSocketTransportConnection with an existing socket and encoding.
     *
     * @param socket the socket to use for the connection
     * @param encoding the encoding used for communication
     * @throws IOException if an error occurs during initialization
     */
    public TcpSocketTransportConnection(Socket socket, String encoding) throws IOException{

        initSocket(socket,encoding);

    }
    /**
     * Constructs a TcpSocketTransportConnection with a new socket connected to the specified IP and port.
     *
     * @param ip the IP address to connect to
     * @param port the port number to connect to
     * @param encoding the encoding used for communication
     * @throws IOException if an error occurs during initialization
     */
    public TcpSocketTransportConnection(String ip, int port, String encoding) throws IOException{

        this.socket = new Socket(ip,port);
        initSocket(socket,encoding);
    }

    /**
     *  Initializes the socket connection and encoding for the TransportConnection.
     *
     * @param socket The socket object representing the connection.
     * @param encoding The encoding used for communication.
     * @throws IOException if an error occurs during initialization.
     */
    private void initSocket(Socket socket, String encoding) throws IOException{

        this.socket = socket;
        this.encoding = encoding;
        initStream();
    }

    /**
     * Initializes the input and output streams for the TransportConnection.
     * @throws IOException if an error occurs during stream initialization.
     */
    private void initStream() throws IOException{

        this.writer = new OutputStreamWriter(socket.getOutputStream(), encoding);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),encoding));
    }

    /**
     * Retrieves the IP address of the connection.
     *
     * @return The IP address of the connection.
     * @throws IOException if an error occurs while retrieving the IP address.
     */
    @Override
    public String getIp() throws IOException {
        return socket.getInetAddress().getHostAddress();
    }

    /**
     * Receive and buffered read stream of chars from server via tcp - socket connection.
     *
     * @return string with client message from console.
     * @throws IOException if an error occurs while receiving the message.
     */
    @Override
    public String receive() throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        while (true){

            stringBuilder.append(reader.readLine());
            if(reader.ready()){
                stringBuilder.append(lineSeparator);
            }else break;
        }
        return stringBuilder.toString();
    }

    /**
     * Send stream of chars (message) from client via tcp - socket connection
     * @param message from client
     */
    @Override
    public void send(String message) throws IOException {

        if(writer != null){

            writer.write(message + lineSeparator);
            writer.flush();

        }
    }

    /**
     * Close connection and free the resources
     */
    @Override
    public void close() throws IOException {

        reader.close();
        writer.close();
        socket.close();
    }
}
