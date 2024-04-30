package server.handler;

import common.command.Command;
import common.command.CommandsInterface;
import common.enums.Status;
import common.json.bodymessage.BodyMessage;
import common.transport.TransportConnection;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RequestHandlerTest {

    @Test
    public void testHandle() throws Exception {
        TransportConnection transportConnection = mock(TransportConnection.class);
        CommandsInterface commands = mock(CommandsInterface.class);
        Command systemCommand = mock(Command.class);
        MessageHandler<TransportConnection> messageHandler = new RequestHandler(commands);

        when(transportConnection.receive()).thenReturn("{\"command\":\"MESSAGE\",\"nickname\":\"jack\",\"text\":\"hello\",\"ip\":\"127.0.0.1\",\"port\":59990}");
        when(commands.contains(anyString())).thenReturn(true);
        when(commands.getByName(anyString())).thenReturn(systemCommand);
        when(systemCommand.execute(any(BodyMessage.class))).thenReturn(Status.SUCCESS);

        messageHandler.handle(transportConnection);

        verify(transportConnection).receive();
        verify(transportConnection).getIp();
        verify(systemCommand).execute(any(BodyMessage.class));
        verify(transportConnection).send(anyString());
    }
}
