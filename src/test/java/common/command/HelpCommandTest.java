package common.command;

import com.example.common.command.Command;
import com.example.common.command.CommandFactory;
import com.example.common.command.CommandsInterface;
import com.example.common.json.bodymessage.BodyMessage;
import junit.framework.Assert;
import org.junit.Test;
import com.example.server.client.ChatClient;
import com.example.server.client.ChatInterface;
import com.example.server.sender.MessageSender;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class HelpCommandTest {

    @Test
    public void testExecute() throws Exception {
        CommandsInterface commands = mock(CommandsInterface.class);
        MessageSender messageSender = mock(MessageSender.class);
        ChatInterface chatClients = mock(ChatInterface.class);
        BodyMessage bodyMessage = mock(BodyMessage.class);
        ChatClient chatClient = mock(ChatClient.class);

        when(chatClients.getUser(eq(bodyMessage.getNickname()))).thenReturn(chatClient);

        Command command = CommandFactory.getHelpCommand(commands, messageSender, chatClients);
        command.execute(bodyMessage);

        //verify(bodyMessage).getNickname();
        verify(chatClients).getUser(eq(bodyMessage.getNickname()));
        verify(messageSender).sendMessage(any(ChatClient.class), anyString());
    }

    @Test
    public void testGetName_NotEmpty() throws Exception {
        Command command = CommandFactory.getHelpCommand(null, null, null);

        Assert.assertNotNull(command.getName());
        Assert.assertTrue(command.getName().length() > 0);
    }

    @Test
    public void testGetDescription() throws Exception {
        Command command = CommandFactory.getHelpCommand(null, null, null);

        Assert.assertNotNull(command.getDescription());
        Assert.assertTrue(command.getDescription().length() > 0);
    }
}
