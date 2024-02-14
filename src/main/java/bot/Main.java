package bot;

import common.stream.ConsoleStreamIO;
import common.stream.StreamIO;
import common.transport.TransportFactory;
import common.transport.tcp.TcpSocketTransportFactory;

import java.util.Scanner;

public class Main {

    //start bot
    public static void main(String[] args) {

        StreamIO streamIO = new ConsoleStreamIO(new Scanner(System.in),System.out);
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        BotWorker botWorker = new BaseBotWorker(streamIO,transportFactory);
        BotService botService = BotService.getInstance();

        //add new users to the chat
        botWorker.addBots(botService.getIpServer(),botService.getPortServer(),
                botService.getStartPort(), botService.getCountUser(), botService.getIntervalConnect());

        printLogAndWait(streamIO,"STOP LOGIN - START SEND", 3000);

        // triggering timer sending of messages by users
        botWorker.startSendMessage(botService.getTimerSendMessage(), botService.getIntervalConnect());
        printLogAndWait(streamIO,"STOP SEND - START LOGOUT", 5000);

        //users left the chat
        botWorker.removeBots(botService.getIntervalConnect());
        printLogAndWait(streamIO, "STOP LOGOUT BOT", 5000);

        System.exit(0);

    }

    private static void printLogAndWait(StreamIO streamIO, String text, int waitTime){

        streamIO.print(text);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
