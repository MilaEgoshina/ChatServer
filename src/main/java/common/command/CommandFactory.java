package common.command;

import server.client.ChatInterface;
import server.sender.MessageSender;

/**
 * Class for fabric creation of commands
 */
public class CommandFactory {

    public static Command getCountUserCommand(MessageSender messageSender, ChatInterface chatInterface){

        return new CountUserCommand(messageSender, chatInterface);
    }

    public static Command getExitCommand(MessageSender messageSender, ChatInterface chatInterface){

        return new ExitCommand(messageSender,chatInterface);
    }

    public static Command getHelpCommand(CommandsInterface commandsInterface,MessageSender messageSender, ChatInterface chatInterface){

        return new HelpCommand(messageSender,chatInterface,commandsInterface);
    }

    public static Command getLoginCommand(MessageSender messageSender, ChatInterface chatInterface){

        return new LoginCommand(messageSender,chatInterface);
    }

    public static Command getMessageCommand(MessageSender messageSender, ChatInterface chatInterface,CommandsInterface commandsInterface){

        return new MessageCommand(messageSender,chatInterface,commandsInterface);
    }

    public static CommandsInterface getCommandsCollection(){

        return new CommandsCollection();
    }
}
