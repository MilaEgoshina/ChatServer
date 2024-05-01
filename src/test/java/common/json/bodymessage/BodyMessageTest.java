package common.json.bodymessage;

import com.example.common.json.bodymessage.BodyMessage;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class BodyMessageTest {

    BodyMessage bodyMessage;

    @Before
    public void setUp() throws Exception {
        bodyMessage = new BodyMessage();
    }

    @Test
    public void testSetGetCommand() throws Exception {
        bodyMessage.setCommand("EXIT");

        Assert.assertEquals(bodyMessage.getCommand(), "EXIT");
    }

    @Test
    public void testSetGetNickName() throws Exception {
        bodyMessage.setNickname("Jack");

        Assert.assertEquals(bodyMessage.getNickname(), "Jack");
    }

    @Test
    public void testSetGetText() throws Exception {
        bodyMessage.setText("hello");

        Assert.assertEquals(bodyMessage.getText(), "hello");
    }

    @Test
    public void testSetGetIp() throws Exception {
        bodyMessage.setIp("127.0.0.1");

        Assert.assertEquals(bodyMessage.getIp(), "127.0.0.1");
    }

    @Test
    public void testSetGetPort() throws Exception {
        bodyMessage.setIp("59990");

        Assert.assertEquals(bodyMessage.getIp(), "59990");
    }
}
