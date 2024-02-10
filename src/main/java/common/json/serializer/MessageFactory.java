package common.json.serializer;


import common.json.bodymessage.BodyMessage;

/**
 * Class creator of BodyMessage class
 */
public class MessageFactory {

    public static BodyMessage createBodyMessage(String command, String nickName, String message, String ip, int port){
        return new BodyMessage(command, nickName, message, ip, port);
    }
}
