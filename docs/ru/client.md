# CLIENT - Клиентская часть

Этот пакет служит для реализации клиентского подключения. Содержит два других пакета. Рассмотрим первый из них:

## Пакет sender:

**ИНТЕРФЕЙСЫ:**

•	**MessageSender** – интерфейс для отправки сообщений клиентом.

**КЛАСС:**

•	**BaseMessageSender implements MessageSender** – имплементация интерфейса для отправки клиентом сообщений.

Содержит поля:
```
private String ipServer;

private int serverPort;

private TransportFactory transportFactory; - через это поле создаем соединение с сервером, используя также приведенные вверху поля ipServer и serverPort. 

private JsonSerializer<BodyMessage> jsonSerializer; (new BodyMessageJsonSerializer()) – через сериалайзер мы сериализуем сообщение как объект – класс (BodyMessage) и получаем строковое значение сообщения пользователя. 
```
Далее создаем соединение с помощью transportFactory и отправляем на сервер это сообщение.

## Пакет app:

Пакет для реализации самого клиентского подключения. Содержит два класса:

•	**BaseClientListener extends BaseListener implements Listener** – базовый класс слушатель для входящих сообщений от других пользователей.
Данный класс никаких дополнительных полей не содержит. Все необходимые поля наследуются от класса BaseListener:
```
public BaseClientListener(int portIn, StreamIO streamIO, TransportFactory transportFactory) {
    super(portIn, streamIO, transportFactory);
}
```

В классе переопределяется один единственный метод, унаследованный от BaseListener – метод `void worker() throws IOException`.

В этом методе создается соединение (TransportConnection) с сервером и далее через это же соединение получаем сообщение.

•	**BaseClient implements Client** – основной базовый класс реализации клиентского соединения.

Поля:
```
StreamIO streamIO; //interface for input - output processing

Listener listener; //listener for input messages from thread

MessageSender messageSender;

boolean stopped = false; // indicator of whether client is working or nor

//parameters of current client
int clientPort;

String nickname;
```

