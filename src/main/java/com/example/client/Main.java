package com.example.client;

import com.example.client.app.BaseClient;
import com.example.client.app.BaseClientListener;
import com.example.client.sender.BaseMessageSender;
import com.example.client.sender.MessageSender;
import com.example.common.input.InputWorker;
import com.example.common.listener.Listener;
import com.example.common.stream.ConsoleStreamIO;
import com.example.common.stream.StreamIO;
import com.example.common.transport.TransportFactory;
import com.example.common.transport.tcp.TcpSocketTransportFactory;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        StreamIO streamIO = new ConsoleStreamIO(new Scanner(System.in), System.out);
        TransportFactory transportFactory = new TcpSocketTransportFactory();

        InputWorker inputWorker = new InputWorker(streamIO, transportFactory);
        String ipServer = inputWorker.getIpServer();
        int portToServer = inputWorker.getPortToServer();
        int portClient = inputWorker.getPortClient();

        MessageSender messageSender = new BaseMessageSender(transportFactory, ipServer, portToServer);
        Listener listener = new BaseClientListener(portClient, streamIO, transportFactory);

        try {
            Client client = new BaseClient(portClient, messageSender, streamIO, listener);
            String nickName = inputWorker.getNickName("Введите свое имя");
            while (client.login(nickName)) {
                nickName = inputWorker.getNickName("Это имя уже используется. Введите другое");
            }
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
