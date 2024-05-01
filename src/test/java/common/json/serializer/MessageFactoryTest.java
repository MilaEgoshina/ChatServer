package common.json.serializer;

import com.example.common.json.bodymessage.BodyMessage;
import com.example.common.json.serializer.MessageFactory;
import junit.framework.Assert;
import org.junit.Test;

public class MessageFactoryTest {

    @Test
    public void testCreateBodyMessage_Create_NotNullReturned() throws Exception {
        BodyMessage bodyMessage = MessageFactory.createBodyMessage("MESSAGE", "jack", "hello", "127.0.0.1", 59990);

        Assert.assertNotNull(bodyMessage);
    }
}
