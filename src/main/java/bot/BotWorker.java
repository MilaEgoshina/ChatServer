package bot;

public interface BotWorker {

    void addBots(String ipServer, int portServer, int startPort, int countBot, int intervalCount);
    void startSendMessage(int timerSendMessage, int intervalSend);
    void removeBots(int intervalExit);
}
