package common.command;

import common.enums.CommandMessages;
import common.enums.Status;
import common.json.bodymessage.BodyMessage;
import server.client.ChatInterface;
import server.sender.MessageSender;

import java.io.IOException;


/**
 * The MessageCommand class represents a command that handles user messages in the chat application.
 */
public class MessageCommand extends BaseCommand{

    /**
     * Constructs a MessageCommand with the given MessageSender, ChatInterface, and CommandsInterface.
     *
     * @param messageSender The MessageSender object for sending messages.
     * @param chatInterface The ChatInterface managing clients in the chat.
     * @param commands The CommandsInterface containing all chat commands.
     */
    CommandsInterface commands;
    public MessageCommand(MessageSender messageSender, ChatInterface chatInterface, CommandsInterface commands) {
        super(messageSender, chatInterface);
        this.name = CommandMessages.MESSAGE.getTextCommand();
        this.description = "Message from user";
        this.commands = commands;
    }

    /**
     * Executes the MessageCommand by processing the body message received from a user.
     *
     * If the message corresponds to a command, it executes the command; otherwise, it sends the message to the chat.
     * @param bodyMessage The BodyMessage containing the user's message details.
     * @return The execution status of the command (SUCCESS or ERROR).
     * @throws IOException if an I/O error occurs during message sending.
     */
    @Override
    public Status execute(BodyMessage bodyMessage) throws IOException {

        Status status = Status.SUCCESS;
        String text = bodyMessage.getText();
        //first we check user commands
        if(commands.contains(text)){
            Command command = commands.getByName(text);
            command.execute(bodyMessage);
        }else{
            messageSender.sendMessageToClient(bodyMessage.getNickname(),text,chatInterface);
        }
        return status;
    }
}
