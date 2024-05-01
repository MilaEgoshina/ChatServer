package com.example.server;

import com.example.server.app.BaseServer;
import com.example.common.input.InputWorker;
import com.example.common.stream.ConsoleStreamIO;
import com.example.common.stream.StreamIO;
import com.example.common.transport.TransportFactory;
import com.example.common.transport.tcp.TcpSocketTransportFactory;

import java.util.Scanner;

public class Main {

    //starting the server...
    public static void main(String[] args) {

        StreamIO streamIO = new ConsoleStreamIO(new Scanner(System.in),System.out);
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        InputWorker inputWorker = new InputWorker(streamIO,transportFactory);
        int port = inputWorker.getPortServer();
        Server server = new BaseServer(port,streamIO,transportFactory);
        server.start();
    }
}
