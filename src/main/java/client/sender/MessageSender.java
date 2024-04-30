package client.sender;

import common.json.bodymessage.BodyMessage;

import java.io.IOException;

/**
 * The MessageSender interface defines methods for sending messages with the specified body message.
 */
public interface MessageSender {

    /**
     * Sends a message with the specified BodyMessage payload.
     *
     * @param bodyMessage the BodyMessage containing the message payload
     * @return true if the message is sent successfully, false otherwise
     * @throws IOException if an error occurs while sending the message
     */
    boolean sendMessage(BodyMessage bodyMessage) throws IOException;
}
