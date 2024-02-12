package common.transport.tcp;

import common.Service;
import common.transport.TransportConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Implementation of the transport layer via TCP Socket
 */
public class TcpSocketTransportConnection implements TransportConnection {

    private Socket socket;
    private OutputStreamWriter writer;
    private BufferedReader reader;
    private String encoding;

    final String lineSeparator = Service.getInstance().getLineSeparator();

    public TcpSocketTransportConnection(Socket socket, String encoding) throws IOException{

        initSocket(socket,encoding);

    }

    public TcpSocketTransportConnection(String ip, int port, String encoding) throws IOException{

        this.socket = new Socket(ip,port);
        initSocket(socket,encoding);
    }
    private void initSocket(Socket socket, String encoding) throws IOException{

        this.socket = socket;
        this.encoding = encoding;
        initStream();
    }

    private void initStream() throws IOException{

        this.writer = new OutputStreamWriter(socket.getOutputStream(), encoding);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),encoding));
    }


    @Override
    public String getIp() throws IOException {
        return socket.getInetAddress().getHostAddress();
    }

    /**
     * Receive and buffered read stream of chars from server via tcp - socket connection
     * @return string with client message from console
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
