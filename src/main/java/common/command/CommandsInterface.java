package common.command;


import java.util.Collection;

/**
 * Interface for interaction with list of commands
 */
public interface CommandsInterface {

    void add(Command command);

    void remove(String name);

    int size();

    Collection<Command> getAllCommands();

    boolean contains(String name);

    Command getByName(String name);
}
