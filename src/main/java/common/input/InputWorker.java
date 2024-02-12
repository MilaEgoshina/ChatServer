package common.input;

import common.stream.StreamIO;
import common.transport.TransportFactory;

/**
 * Class for initializing data entry
 */
public class InputWorker {

    private StreamIO streamIO;
    private TransportFactory transportFactory;

    public InputWorker(StreamIO streamIO, TransportFactory transportFactory) {
        this.streamIO = streamIO;
        this.transportFactory = transportFactory;
    }

    public int getPortServer(){
        return getPort("Input Server port", "Port is incorrect or used. Try again", true);
    }

    public String getIpServer(){

        return getIp("Please,enter IP - address of server","Incorrect server IP - address" +
                "input. Please, try again");
    }

    /**
     * Method to initialize the port that connects to the server
     * @return number of port for connection to server
     */
    public int getPortToServer(){

        return getPort("Enter the port to connect to the server: ",
                "The port is entered incorrectly. Please, try again", false);
    }

    /**
     * Method to initialize the port for incoming messages
     * @return number of port
     */
    public int getPortClient(){

        return getPort("Enter the port for incoming messages: ",
                "The port is entered incorrectly or is already in use. Try again", true);
    }

    /**
     * Method for getting username from client
     * @param welcomeMessage
     * @return username
     */
    public String getNickName(String welcomeMessage){

        streamIO.print(welcomeMessage);
        return streamIO.read();
    }


    /**
     * Method for port initialization
     * @param welcomeMessage
     * @param errorMessage - message if any error occurs
     * @param isUsedValidation
     * @return initialized number of port
     */
    private int getPort(String welcomeMessage, String errorMessage, Boolean isUsedValidation){
        InputPort inputPort = new InputPort();
        inputPort.setHeaderWelcomeMessage(welcomeMessage);
        inputPort.setHeaderErrorMessage(errorMessage);
        inputPort.setUsedValidation(isUsedValidation);
        inputPort.setTransportFactory(transportFactory);
        inputPort.setStreamIO(streamIO);

        return inputPort.getValue();
    }

    /**
     * Method for IP initialization
     * @param welcomeMessage
     * @param errorMessage
     * @return initialized IP
     */
    private String getIp(String welcomeMessage, String errorMessage){
        InputIp inputIp = new InputIp();
        inputIp.setHeaderWelcomeMessage(welcomeMessage);
        inputIp.setHeaderErrorMessage(errorMessage);
        inputIp.setStreamIO(streamIO);

        return inputIp.getValue();
    }
}
