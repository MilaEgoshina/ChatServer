package common.json.serializer;

import common.json.bodymessage.BodyMessage;
import junit.framework.Assert;
import org.junit.Test;

public class MessageFactoryTest {

    @Test
    public void testCreateBodyMessage_Create_NotNullReturned() throws Exception {
        BodyMessage bodyMessage = MessageFactory.createBodyMessage("MESSAGE", "alex", "hello", "127.0.0.1", 60000);

        Assert.assertNotNull(bodyMessage);
    }
}
