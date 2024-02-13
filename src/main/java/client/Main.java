package client;

import client.app.BaseClient;
import client.app.BaseClientListener;
import client.sender.BaseMessageSender;
import client.sender.MessageSender;
import common.input.InputWorker;
import common.listener.Listener;
import common.stream.ConsoleStreamIO;
import common.stream.StreamIO;
import common.transport.TransportFactory;
import common.transport.tcp.TcpSocketTransportFactory;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        StreamIO streamIO = new ConsoleStreamIO(new Scanner(System.in),System.out);
        TransportFactory transportFactory = new TcpSocketTransportFactory();

        InputWorker inputWorker = new InputWorker(streamIO,transportFactory);
        String ipServer = inputWorker.getIpServer();
        int portToServer = inputWorker.getPortToServer();
        int portClient = inputWorker.getPortClient();

        MessageSender messageSender = new BaseMessageSender(ipServer,portToServer,transportFactory);
        Listener listener = new BaseClientListener(portClient,streamIO,transportFactory);

        try{

            Client client = new BaseClient(streamIO, listener,messageSender,portClient);
            String nickname = inputWorker.getNickName("Please, entry your nickname:");
            while (!client.login(nickname)){

                nickname = inputWorker.getNickName("This name is already used. Please, enter other one");
            }

            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
