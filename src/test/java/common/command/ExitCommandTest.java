package common.command;

import common.Service;
import common.json.bodymessage.BodyMessage;
import junit.framework.Assert;
import org.junit.Test;
import server.client.ChatInterface;
import server.sender.MessageSender;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ExitCommandTest {

    @Test
    public void testExecute() throws Exception {
        MessageSender messageSender = mock(MessageSender.class);
        ChatInterface chatClients = mock(ChatInterface.class);
        BodyMessage bodyMessage = mock(BodyMessage.class);

        Command command = CommandFactory.getExitCommand(messageSender, chatClients);
        command.execute(bodyMessage);

        verify(bodyMessage).getNickname();
        verify(chatClients).removeUser(eq(bodyMessage.getNickname()));
        verify(messageSender).sendMessageToClient(eq(bodyMessage.getNickname()),
                eq(Service.getInstance().getGoodbyeMessageForAll()), eq(chatClients));
    }

    @Test
    public void testGetName_NotEmpty() throws Exception {
        Command command = CommandFactory.getExitCommand(null, null);

        Assert.assertNotNull(command.getName());
        Assert.assertTrue(command.getName().length() > 0);
    }

    @Test
    public void testGetDescription() throws Exception {
        Command command = CommandFactory.getExitCommand(null, null);

        Assert.assertNotNull(command.getDescription());
        Assert.assertTrue(command.getDescription().length() > 0);
    }
}
