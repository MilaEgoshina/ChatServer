package server.client;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for checking client class
 * Value of setters should be equal to getters
 */
public class ChatClientTest {

    ChatClient chatClient;

    @Before
    public void setUp() throws Exception {
        chatClient = new ChatClient();
    }

    @Test
    public void testSetGetNickName() throws Exception {
        chatClient.setNickname("jack");

        Assert.assertEquals(chatClient.getNickname(), "jack");
    }

    @Test
    public void testSetGetIp() throws Exception {
        chatClient.setIp("127.0.0.1");

        Assert.assertEquals(chatClient.getIp(), "127.0.0.1");
    }

    @Test
    public void testSetGetPort() throws Exception {
        chatClient.setPort(59990);

        Assert.assertEquals(chatClient.getPort(), 59990);
    }
}
