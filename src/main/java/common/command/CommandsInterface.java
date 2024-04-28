package common.command;


import java.util.Collection;

/**
 * The CommandsInterface provides methods to manage a collection of commands.
 */
public interface CommandsInterface {

    /**
     * Add a command to the collection.
     * @param command The command to add.
     */
    void add(Command command);

    /**
     * Remove a command by its name from the collection.
     * @param name The name of the command to remove.
     */
    void remove(String name);

    /**
     * Get the size of the collection.
     * @return The size of the collection.
     */
    int size();

    /**
     * Get all commands in the collection.
     * @return A collection of all commands.
     */
    Collection<Command> getAllCommands();

    /**
     * Check if the collection contains a command with the specified name.
     *
     * @param name The name of the command to check for.
     * @return true if the collection contains the command, false otherwise.
     */
    boolean contains(String name);

    /**
     * Get a command by its name.
     *
     * @param name The name of the command.
     * @return The command object with the specified name.
     */
    Command getByName(String name);
}
