package common.command;

import server.client.ChatInterface;
import server.sender.MessageSender;

/**
 * Abstract class that serves as the base for command implementations.
 */
public abstract class BaseCommand implements Command{

    protected String name;
    protected String description;
    protected MessageSender messageSender;
    protected ChatInterface chatInterface;

    /**
     * Constructor for the BaseCommand class.
     *
     * @param messageSender The MessageSender object for sending messages.
     * @param chatInterface The ChatInterface containing information about clients in the chat.
     */
    public BaseCommand(MessageSender messageSender, ChatInterface chatInterface) {
        this.messageSender = messageSender;
        this.chatInterface = chatInterface;
    }

    /**
     * Get the name of the command.
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get the description of the command.
     * @return The description of the command.
     */
    @Override
    public String getDescription() {
        return description;
    }
}
