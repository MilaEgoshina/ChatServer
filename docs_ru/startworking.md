# Порядок работы server:

1. Инициализируем класс для ввода и вывода информации: 
```
StreamIO streamIO = new ConsoleStreamIO(new Scanner(System.in),System.out);
```
2. Создаем объект класса TcpSocketTransportFactory:
```
TransportFactory transportFactory = new TcpSocketTransportFactory();
```

3. А далее запускаем уже `InputWorker inputWorker = new InputWorker(streamIO,transportFactory)` – для валидации ввода информации от пользователя


4. И с помощью этого класса InputWorker получаем порт, на котором сервер будет слушать входящие соединения:
```
int port = inputWorker.getPortServer();
```
6. И запускаем уже сам сервер:
```
        Server server = new BaseServer(port,streamIO,transportFactory);
        server.start();
```

# Порядок работы client:

1. Инициализируем класс для ввода и вывода информации: 
```
StreamIO streamIO = new ConsoleStreamIO(new Scanner(System.in),System.out);
```

2. Создаем объект класса TcpSocketTransportFactory:
```
TransportFactory transportFactory = new TcpSocketTransportFactory();
```
3. А далее запускаем уже `InputWorker inputWorker = new InputWorker(streamIO,transportFactory)` – для валидации ввода информации от пользователя


4. С помощью InputWorker получаем порты для подключения к серверу, порт на самом клиента и IP сервера для подключения
```        
String ipServer = inputWorker.getIpServer();
int portToServer = inputWorker.getPortToServer();
int portClient = inputWorker.getPortClient();
```

5. Далее создаем экземпляр класса BaseMessageSender для старта отправки сообщений на сервер
```
MessageSender messageSender = new BaseMessageSender(ipServer,portToServer,transportFactory);
```
6. Устанавливаем “прослушку” для входящих сообщений на порту клиента:
```
Listener listener = new BaseClientListener(portClient,streamIO,transportFactory);
```
7. Затем уже запускаем клиента. С помощью InputWorker получаем
Nickname клиента. 
```
Client client = new BaseClient(streamIO, listener,messageSender,portClient);
String nickname = inputWorker.getNickName("Please, entry your nickname:");
while (!client.login(nickname)){

    nickname = inputWorker.getNickName("This name is already used. Please, enter other one");
}

client.start();
```