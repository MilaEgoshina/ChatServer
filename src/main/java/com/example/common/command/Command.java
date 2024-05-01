package com.example.common.command;

import com.example.common.enums.Status;
import com.example.common.json.bodymessage.BodyMessage;

import java.io.IOException;

/**
 * The Command interface represents a command that can be executed based on a BodyMessage.
 */
public interface Command {


    /**
     * Executes the command based on the provided BodyMessage.
     *
     * @param bodyMessage The BodyMessage containing the command details.
     * @return The execution status of the command.
     * @throws IOException if an I/O error occurs during command execution.
     */
    Status execute(BodyMessage bodyMessage) throws IOException;

    /**
     * Gets the name of the command.
     *
     * @return The name of the command.
     */
    String getName();

    /**
     * Gets the description of the command.
     *
     * @return The description of the command.
     */
    String getDescription();

}
