package common.command;

import junit.framework.Assert;
import org.junit.Test;

public class CommandsCollectionTest {

    @Test
    public void testAdd_One_SizeOneReturned() throws Exception {
        CommandsInterface commands = new CommandsCollection();

        Command command = new CountUserCommand(null, null);
        commands.add(command);

        Assert.assertEquals(commands.size(), 1);
    }

    @Test
    public void testAdd_Two_SizeTwoReturned() throws Exception {
        CommandsInterface commands = new CommandsCollection();

        Command command = new CountUserCommand(null, null);
        commands.add(command);
        command = new ExitCommand(null, null);
        commands.add(command);

        Assert.assertEquals(commands.size(), 2);
    }

    @Test
    public void testRemove_AddOneRemoveOne_SizeZeroReturned() throws Exception {
        CommandsInterface commands = new CommandsCollection();

        Command command = new CountUserCommand(null, null);
        commands.add(command);
        commands.remove(command.getName());

        Assert.assertEquals(commands.size(), 0);
    }

    @Test
    public void testSize_Default_SizeZeroReturned() throws Exception {
        CommandsInterface commands = new CommandsCollection();

        Assert.assertEquals(commands.size(), 0);
    }

    @Test
    public void testGetCommands_EqualSizes_TrueReturned() throws Exception {
        CommandsInterface commands = new CommandsCollection();

        Assert.assertEquals(commands.size(), commands.getAllCommands().size());
    }

    @Test
    public void testContains_CountUser_TrueReturned() throws Exception {
        CommandsInterface commands = new CommandsCollection();

        Command command = new CountUserCommand(null, null);
        commands.add(command);

        Assert.assertTrue(commands.contains(command.getName()));
    }

    @Test
    public void testContains_abc_FalseReturned() throws Exception {
        CommandsInterface commands = new CommandsCollection();

        Command command = new CountUserCommand(null, null);
        commands.add(command);

        Assert.assertFalse(commands.contains("abc"));
    }

    @Test
    public void testGetByName_CountUser_TrueReturned() throws Exception {
        CommandsInterface commands = new CommandsCollection();
        Command command = new CountUserCommand(null, null);
        commands.add(command);

        command = commands.getByName(command.getName());

        Assert.assertNotNull(command);
    }

    @Test
    public void testGetByName_abc_TrueReturned() throws Exception {
        CommandsInterface commands = new CommandsCollection();

        Command command = new CountUserCommand(null, null);
        commands.add(command);
        command = commands.getByName("abc");

        Assert.assertNull(command);
    }
}
