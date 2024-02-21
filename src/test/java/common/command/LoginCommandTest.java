package common.command;

import common.Service;
import common.json.bodymessage.BodyMessage;
import junit.framework.Assert;
import org.junit.Test;
import server.client.ChatClient;
import server.client.ChatInterface;
import server.sender.MessageSender;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LoginCommandTest {

    @Test
    public void testExecute() throws Exception {
        MessageSender messageSender = mock(MessageSender.class);
        ChatInterface chatClients = mock(ChatInterface.class);
        BodyMessage bodyMessage = mock(BodyMessage.class);
        ChatClient chatClient = mock(ChatClient.class);

        Command command = CommandFactory.getLoginCommand(messageSender, chatClients);
        command.execute(bodyMessage);

        verify(bodyMessage).getNickname();
        verify(bodyMessage).getIp();
        verify(bodyMessage).getPort();
        verify(chatClients).containsUser(eq(bodyMessage.getNickname()));
        verify(chatClients).addUser(any(ChatClient.class));
        verify(messageSender).sendLastMessages(any(ChatClient.class));
        verify(messageSender).sendMessageToClient(eq(bodyMessage.getNickname()), eq(Service.getInstance().getHelloMessageForAll()),
                eq(chatClients));
    }

    @Test
    //имя команды не должно быть пустым
    public void testGetName_NotEmpty() throws Exception {
        Command command = CommandFactory.getLoginCommand(null, null);

        Assert.assertNotNull(command.getName());
        Assert.assertTrue(command.getName().length() > 0);
    }

    @Test
    //описание команды не должно быть пустым
    public void testGetDescription() throws Exception {
        Command command = CommandFactory.getLoginCommand(null, null);

        Assert.assertNotNull(command.getDescription());
        Assert.assertTrue(command.getDescription().length() > 0);
    }
}
