package client.sender;

import common.json.bodymessage.BodyMessage;

import java.io.IOException;

/**
 * Interface for sending messages by client
 */
public interface MessageSender {

    boolean sendMessage(BodyMessage bodyMessage) throws IOException;
}
