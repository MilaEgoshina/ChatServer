package com.example.common.command;

import com.example.server.client.ChatInterface;
import com.example.server.sender.MessageSender;

/**
 * The CommandFactory class provides static methods for creating different types of commands.
 */
public class CommandFactory {

    /**
     * Creates and returns a CountUserCommand object.
     *
     * @param messageSender The MessageSender object for sending messages.
     * @param chatInterface The ChatInterface managing clients in the chat.
     * @return A CountUserCommand object.
     */
    public static Command getCountUserCommand(MessageSender messageSender, ChatInterface chatInterface){

        return new CountUserCommand(messageSender, chatInterface);
    }

    /**
     * Creates and returns an ExitCommand object.
     *
     * @param messageSender The MessageSender object for sending messages.
     * @param chatInterface The ChatInterface managing clients in the chat.
     * @return An ExitCommand object.
     */
    public static Command getExitCommand(MessageSender messageSender, ChatInterface chatInterface){

        return new ExitCommand(messageSender,chatInterface);
    }

    /**
     * Creates and returns a HelpCommand object.
     *
     * @param commandsInterface The CommandsInterface for managing commands.
     * @param messageSender The MessageSender object for sending messages.
     * @param chatInterface The ChatInterface managing clients in the chat.
     * @return A HelpCommand object.
     */
    public static Command getHelpCommand(CommandsInterface commandsInterface,MessageSender messageSender, ChatInterface chatInterface){

        return new HelpCommand(messageSender,chatInterface,commandsInterface);
    }

    /**
     * Creates and returns a LoginCommand object.
     *
     * @param messageSender The MessageSender object for sending messages.
     * @param chatInterface The ChatInterface managing clients in the chat.
     * @return A LoginCommand object.
     */
    public static Command getLoginCommand(MessageSender messageSender, ChatInterface chatInterface){

        return new LoginCommand(messageSender,chatInterface);
    }

    /**
     * Creates and returns a MessageCommand object.
     *
     * @param messageSender The MessageSender object for sending messages.
     * @param chatInterface The ChatInterface managing clients in the chat.
     * @param commandsInterface The CommandsInterface for managing commands.
     * @return A MessageCommand object.
     */
    public static Command getMessageCommand(MessageSender messageSender, ChatInterface chatInterface,CommandsInterface commandsInterface){

        return new MessageCommand(messageSender,chatInterface,commandsInterface);
    }

    /**
     * Returns a new instance of CommandsCollection.
     * @return A CommandsCollection object.
     */
    public static CommandsInterface getCommandsCollection(){

        return new CommandsCollection();
    }
}
