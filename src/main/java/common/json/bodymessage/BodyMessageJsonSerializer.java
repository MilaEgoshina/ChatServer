package common.json.bodymessage;


import common.json.serializer.BaseJsonSerializer;
import common.json.serializer.JsonSerializer;

/**
 * Implementation of Json serializer via BodyMessage
 */
public class BodyMessageJsonSerializer extends BaseJsonSerializer<BodyMessage> implements JsonSerializer<BodyMessage> {

    public BodyMessageJsonSerializer() {
        super(BodyMessage.class);
    }
}
