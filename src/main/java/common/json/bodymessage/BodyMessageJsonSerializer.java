package common.json.bodymessage;


import common.json.serializer.BaseJsonSerializer;
import common.json.serializer.JsonSerializer;

/**
 * This class is a custom JSON serializer for the BodyMessage class.
 * It extends the BaseJsonSerializer class and implements the JsonSerializer interface.
 * It is used to serialize BodyMessage objects into JSON format.
 */
public class BodyMessageJsonSerializer extends BaseJsonSerializer<BodyMessage> implements JsonSerializer<BodyMessage> {

    /**
     * Constructor for the BodyMessageJsonSerializer class.
     * It initializes the serializer for the BodyMessage class.
     */
    public BodyMessageJsonSerializer() {
        super(BodyMessage.class);
    }
}
