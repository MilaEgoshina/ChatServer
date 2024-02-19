package server.client;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChatClientTest {

    ChatClient chatClient;

    @Before
    public void setUp() throws Exception {
        chatClient = new ChatClient();
    }

    @Test
    public void testSetGetNickName() throws Exception {
        chatClient.setNickname("alex");

        Assert.assertEquals(chatClient.getNickname(), "alex");
    }

    @Test
    public void testSetGetIp() throws Exception {
        chatClient.setIp("127.0.0.1");

        Assert.assertEquals(chatClient.getIp(), "127.0.0.1");
    }

    @Test
    public void testSetGetPort() throws Exception {
        chatClient.setPort(60000);

        Assert.assertEquals(chatClient.getPort(), 60000);
    }
}
