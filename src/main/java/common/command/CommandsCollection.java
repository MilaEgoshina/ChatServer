package common.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * The CommandsCollection class implements the CommandsInterface using a HashMap to store commands.
 * This class provides methods to add, remove, and retrieve commands, as well as check for the presence of a command.
 */
public class CommandsCollection implements CommandsInterface{

    Map<String,Command> commands = new HashMap<>();


    /**
     * Adds a command to the collection using the command's name as the key and the command object as the value.
     * @param command The command to be added to the collection.
     */
    @Override
    public void add(Command command) {

        commands.put(command.getName(),command);

    }

    /**
     * Removes a command from the collection using its name as the key.
     * @param name The name of the command to be removed.
     */
    @Override
    public void remove(String name) {

        commands.remove(name);
    }

    /**
     * Returns the number of commands stored in the collection.
     * @return The size of the collection.
     */
    @Override
    public int size() {
        return commands.size();
    }

    /**
     * Returns a collection containing all the commands stored in the collection.
     * @return A Collection of all commands.
     */
    @Override
    public Collection<Command> getAllCommands() {
        return commands.values();
    }

    /**
     * Checks if a command with the specified name exists in the collection.
     *
     * @param name The name of the command to check for.
     * @return true if the collection contains the command, false otherwise.
     */
    @Override
    public boolean contains(String name) {
        return commands.containsKey(name);
    }

    /**
     * Retrieves a command from the collection based on its name.
     *
     * @param name The name of the command to retrieve.
     * @return The command object corresponding to the specified name.
     */
    @Override
    public Command getByName(String name) {
        return commands.get(name);
    }
}
