package com.example.common.json.serializer;


import com.example.common.json.bodymessage.BodyMessage;

/**
 * The MessageFactory class is responsible for creating instances of the BodyMessage class.
 */
public class MessageFactory {

    /**
     * Creates a new BodyMessage object with the specified parameters.
     *
     * @param command the command of the message
     * @param nickName the nickname of the sender
     * @param message the content of the message
     * @param ip the IP address of the sender
     * @param port the port number of the sender
     * @return a new BodyMessage object with the specified parameters
     */
    public static BodyMessage createBodyMessage(String command, String nickName, String message, String ip, int port){
        return new BodyMessage(command, nickName, message, ip, port);
    }
}
