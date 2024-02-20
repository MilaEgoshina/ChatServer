package server.sender;

import common.json.bodymessage.BodyMessage;
import junit.framework.Assert;
import org.junit.Test;
import server.client.ChatClient;
import server.client.ChatClientsHashMap;
import server.client.ChatInterface;
import server.handler.MessageQueue;
import server.lastmessages.CircularFifoBufferLastMessages;
import server.lastmessages.LastMessages;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SimpleMessageSenderTest {


    /**
     * Test for sending message to all clients
     * Message should be added in list of last messages
     * and also messageQueue.add(T message) should be called once, since Alex1 himself does not need to send a message
     */
    @Test
    public void testSendMessageToClients() throws Exception {
        LastMessages lastMessages = new CircularFifoBufferLastMessages(2);
        ChatInterface chatClients = new ChatClientsHashMap();
        chatClients.addUser(new ChatClient("Alex1","",0));
        chatClients.addUser(new ChatClient("Alex2","",0));
        MessageQueue<BodyMessage> messageQueue = mock(MessageQueue.class);

        MessageSender messageSender = new SimpleMessageSender(lastMessages, messageQueue);
        messageSender.sendMessageToClient("Alex1","Hello", chatClients);

        Assert.assertEquals(lastMessages.getLastMessages().size(), 1);
        verify(messageQueue).add(any(BodyMessage.class));
    }

    /**
     * Testing sending message for a concrete client
     * messageQueue.add(T message) should be called once
     */
    @Test
    public void testSendMessage() throws Exception {
        LastMessages lastMessages = mock(LastMessages.class);
        MessageQueue<BodyMessage> messageQueue = mock(MessageQueue.class);

        MessageSender messageSender = new SimpleMessageSender(lastMessages, messageQueue);
        messageSender.sendMessage(new ChatClient("Alex1", "", 0), "Hello");

        verify(messageQueue).add(any(BodyMessage.class));
    }

    /**
     * Testing sending last messages for concrete client
     * messageQueue.add(T message) should be called once
     */
    @Test
    public void testSendLastMessages() throws Exception {
        LastMessages lastMessages = new CircularFifoBufferLastMessages(2);
        lastMessages.add("1");
        MessageQueue<BodyMessage> messageQueue = mock(MessageQueue.class);

        MessageSender messageSender = new SimpleMessageSender(lastMessages, messageQueue);
        messageSender.sendLastMessages(new ChatClient("Alex1", "", 0));

        verify(messageQueue).add(any(BodyMessage.class));
    }
}
