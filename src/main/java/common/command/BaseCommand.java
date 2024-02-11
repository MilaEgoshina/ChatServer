package common.command;

import server.client.ChatInterface;
import server.sender.MessageSender;

/**
 * Base class for working with commands
 */
public abstract class BaseCommand implements Command{

    protected String name;
    protected String description;
    protected MessageSender messageSender;
    protected ChatInterface chatInterface;

    public BaseCommand(MessageSender messageSender, ChatInterface chatInterface) {
        this.messageSender = messageSender;
        this.chatInterface = chatInterface;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
