package bot;

import client.Client;
import client.app.BaseClient;
import client.app.BaseClientListener;
import client.sender.BaseMessageSender;
import client.sender.MessageSender;
import common.listener.Listener;
import common.stream.StreamIO;
import common.transport.TransportFactory;
import common.validate.ValidatorFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * The BaseBotWorker class implements the BotWorker interface for managing bots in the chat application.
 */
public class BaseBotWorker implements BotWorker{

    private StreamIO streamIO;
    private TransportFactory transportFactory;
    List<Client> clients;

    /**
     * Constructs a BaseBotWorker with the specified StreamIO and TransportFactory.
     * @param streamIO the StreamIO object for input-output processing
     * @param transportFactory the TransportFactory object for creating connections
     */
    public BaseBotWorker(StreamIO streamIO, TransportFactory transportFactory) {
        this.streamIO = streamIO;
        this.transportFactory = transportFactory;
        this.clients = new ArrayList<>();
    }

    /**
     * Add bots to the server with specified parameters.
     *
     * @param ipServer the IP address of the server
     * @param portServer the port number of the server
     * @param startPort the starting port number for bots
     * @param countBot the number of bots to add
     * @param intervalCount the interval between adding bots
     */
    @Override
    public void addBots(String ipServer, int portServer, int startPort, int countBot, int intervalCount) {

        for(int i = 1; i <= countBot; i++){
            try {

                Thread.sleep(intervalCount);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            int port = startPort + 1;
            if(ValidatorFactory.getNotUsedPortValidation(String.valueOf(port),transportFactory).validate()){

                MessageSender messageSender = new BaseMessageSender(transportFactory, ipServer, portServer);
                Listener listener = new BaseClientListener(port, streamIO, transportFactory);
                Client client = new BaseClient(port, messageSender, streamIO, listener);
                try {
                    if(client.login(String.format("User_%s", i))){

                        client.startListener();
                        clients.add(client);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Start sending messages with a specified timer and interval for sending messages.
     *
     * @param timerSendMessage the timer for sending messages
     * @param intervalSend the interval between message sends
     */
    @Override
    public void startSendMessage(int timerSendMessage, int intervalSend) {
        BotTimerTask botTimerTask = new BotTimerTask();
        botTimerTask.setClients(clients);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(botTimerTask, 0, intervalSend);
        try {
            Thread.sleep(timerSendMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }

    /**
     * Remove bots from the server at a specified interval.
     * @param intervalExit the interval for removing bots
     */
    @Override
    public void removeBots(int intervalExit) {

        for(Client client : clients){

            try {
                Thread.sleep(intervalExit);
                client.logoutAndStop();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
