package common.json.bodymessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a body message that can be sent in a JSON format.
 *
 * It contains the following fields:
 * - command: the command for the message
 * - nickname: the nickname associated with the message
 * - text: the text content of the message
 * - ip: the IP address associated with the message
 * - port: the port number associated with the message
 *
 * This class provides getters and setters for each field, as well as constructors for creating instances of the class.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BodyMessage {

    private String command;
    private String nickname;
    private String text;
    private String ip;
    private int port;
}
