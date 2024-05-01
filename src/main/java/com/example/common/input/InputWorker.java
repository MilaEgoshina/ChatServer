package com.example.common.input;

import com.example.common.stream.StreamIO;
import com.example.common.transport.TransportFactory;

/**
 * The InputWorker class is responsible for handling user input for various network related parameters.
 */
public class InputWorker {

    private StreamIO streamIO;
    private TransportFactory transportFactory;

    /**
     * Constructor for InputWorker class.
     *
     * @param streamIO The streamIO object for input/output operations.
     * @param transportFactory The transportFactory object for creating network transports.
     */
    public InputWorker(StreamIO streamIO, TransportFactory transportFactory) {
        this.streamIO = streamIO;
        this.transportFactory = transportFactory;
    }

    /**
     * Method to get the port number for the input server.
     * @return The port number for the input server.
     */
    public int getPortServer(){
        return getPort("Input Server port", "Port is incorrect or used. Try again", true);
    }

    /**
     * Method to get the IP address of the server.
     * @return The IP address of the server.
     */
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
     * Method to get the nickname of the user.
     *
     * @param welcomeMessage The welcome message prompting the user to enter their nickname.
     * @return The nickname entered by the user.
     */
    public String getNickName(String welcomeMessage){

        streamIO.print(welcomeMessage);
        return streamIO.read();
    }


    /**
     * Private method to get the port number with error handling and validation.
     *
     * @param welcomeMessage The welcome message prompting the user to enter the port number.
     * @param errorMessage The error message displayed if the port number is incorrect.
     * @param isUsedValidation Flag indicating whether to check if the port is already in use.
     * @return The validated port number entered by the user.
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
     * Private method to get the IP address with error handling and validation.
     *
     * @param welcomeMessage The welcome message prompting the user to enter the IP address.
     * @param errorMessage The error message displayed if the IP address is incorrect.
     * @return The validated IP address entered by the user.
     */
    private String getIp(String welcomeMessage, String errorMessage){
        InputIp inputIp = new InputIp();
        inputIp.setHeaderWelcomeMessage(welcomeMessage);
        inputIp.setHeaderErrorMessage(errorMessage);
        inputIp.setStreamIO(streamIO);

        return inputIp.getValue();
    }
}
