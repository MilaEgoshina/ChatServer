package common.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Class for interaction with collection of commands
 */
public class CommandsCollection implements CommandsInterface{

    Map<String,Command> commands = new HashMap<>();


    /**
     * Add a new command to the collection
     */
    @Override
    public void add(Command command) {

        commands.put(command.getName(),command);

    }

    /**
     * Delete command from collection by its name
     */
    @Override
    public void remove(String name) {

        commands.remove(name);
    }

    /**
     * Count numbers of commands
     */
    @Override
    public int size() {
        return commands.size();
    }

    /**
     * Get all commands collection
     */
    @Override
    public Collection<Command> getAllCommands() {
        return commands.values();
    }

    /**
     * Check if there is such command in collection
     */
    @Override
    public boolean contains(String name) {
        return commands.containsKey(name);
    }

    /**
     * Get command by its name
     */
    @Override
    public Command getByName(String name) {
        return commands.get(name);
    }
}
