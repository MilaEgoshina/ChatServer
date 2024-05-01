package server.lastmessages;

import com.example.server.lastmessages.CircularFifoBufferLastMessages;
import com.example.server.lastmessages.LastMessages;
import junit.framework.Assert;
import org.junit.Test;

public class CircularFifoBufferLastMessagesTest {

    @Test
    public void testClear_AddOneClear_SizeZeroReturned() throws Exception {
        LastMessages lastMessages = new CircularFifoBufferLastMessages(100);
        lastMessages.add("asd");

        lastMessages.clear();

        Assert.assertEquals(lastMessages.getLastMessages().size(), 0);
    }

    @Test
    public void testAdd_One_SizeOneReturned() throws Exception {
        LastMessages lastMessages = new CircularFifoBufferLastMessages(100);

        lastMessages.add("asd");

        Assert.assertEquals(lastMessages.getLastMessages().size(), 1);
    }

    @Test
    public void testAdd_Three_SizeTwoReturned() throws Exception {
        LastMessages lastMessages = new CircularFifoBufferLastMessages(2);

        lastMessages.add("1");
        lastMessages.add("2");
        lastMessages.add("3");

        Assert.assertEquals(lastMessages.getLastMessages().size(), 2);
    }

    @Test
    public void testGetLastMessages_AddTwoConcatEqual_TrueReturned() throws Exception {
        LastMessages lastMessages = new CircularFifoBufferLastMessages(2);
        lastMessages.add("1");
        lastMessages.add("2");

        StringBuilder stringBuilder = new StringBuilder();
        for (String lastMessage : lastMessages.getLastMessages()) {
            stringBuilder.append(lastMessage).append(";");
        }

        Assert.assertEquals(stringBuilder.toString(), "1;2;");
    }
}
