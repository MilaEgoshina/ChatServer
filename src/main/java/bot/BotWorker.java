package bot;

/**
 * The BotWorker interface defines the methods for managing bots in the chat application.
 */
public interface BotWorker {

    /**
     * Add bots to the server with the specified parameters.
     *
     * @param ipServer the IP address of the server
     * @param portServer the port number of the server
     * @param startPort the starting port number for bots
     * @param countBot the number of bots to add
     * @param intervalCount the interval between adding bots
     */
    void addBots(String ipServer, int portServer, int startPort, int countBot, int intervalCount);

    /**
     * Start sending messages with a specified timer and interval for sending messages.
     *
     * @param timerSendMessage the timer for sending messages
     * @param intervalSend the interval between message sends
     */
    void startSendMessage(int timerSendMessage, int intervalSend);

    /**
     * Remove bots from the server at a specified interval.
     * @param intervalExit the interval for removing bots
     */
    void removeBots(int intervalExit);
}
