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

public class BaseBotWorker implements BotWorker{

    private StreamIO streamIO;
    private TransportFactory transportFactory;
    List<Client> clients;

    public BaseBotWorker(StreamIO streamIO, TransportFactory transportFactory) {
        this.streamIO = streamIO;
        this.transportFactory = transportFactory;
        this.clients = new ArrayList<>();
    }

    /**
     * Add countBot bots to the server ipServer:portServer in the intervalConnect
     * @param ipServer
     * @param portServer
     * @param startPort
     * @param countBot
     * @param intervalCount
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

                MessageSender messageSender = new BaseMessageSender(ipServer, portServer, transportFactory);
                Listener listener = new BaseClientListener(port, streamIO, transportFactory);
                Client client = new BaseClient(streamIO,listener,messageSender,port);
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
     * Timer triggers users to send messages every intervalConnect milliseconds for timerSendMessage milliseconds
     * @param timerSendMessage
     * @param intervalSend
     */
    @Override
    public void startSendMessage(int timerSendMessage, int intervalSend) {


    }

    /**
     * Delete bots, each one leaves the chat after intervalExit milliseconds
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
