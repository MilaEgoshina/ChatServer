# SERVER - Серверная часть приложения

## **Пакет client:**

Содержит два класса:

•	**ChatClient** – класс для взаимодействия с клиентом, содержит никнейм, порт и IP – address клиента.

•	**ChatClientsHashMap** – (реализация интерфейса ChatInterface) Класс- коллекция для работы со списком клиентов в чате с помощью ConcurrentHashMap. Содержит операции добавления, удаления клиентов из hashmap + проверка, есть ли такой клиент в коллекции, получение коллекции всех пользователей и подсчет кол-ва клиентов в коллекции.

Один интерфейс:

•	**ChatInterface** – интерфейс для работы с коллекцией клиентов

## **Пакет handler:**

**Интерфейсы:**

•	**MessageHandler <T>** – обработчик сообщений, содержит один метод для имплементации handle(T message)

•	**MessageQueue<T>** - интерфейс, который представляет собой очередь для хранения и получения сообщений типа T.

•	**HandleThread** – предназначен для имплементации обработки сообщений в многопоточной среде.

**Классы:**

•	**MessageQueueExecutor<T> implements MessageQueue<T>** - класс реализует очередь сообщений, используя ThreadPoolExecutor для одновременной обработки входящих сообщений. Сообщения добавляются в очередь и обрабатываются MessageHandler в отдельных потоках. Содержит поля:

```
ThreadPoolExecutor threadPoolExecutor;

MessageHandler<T> messageHandler;

```

В методе `void add(T message)`, который реализован в этом классе в рамках интерфейса MessageQueue, используется экземпляр класса MessageHandlerRunnable – реализует такой же обработчик сообщений, только используя еще интерфейс Runnable для потока.

**Класс RequestHandler implements MessageHandler<TransportConnection>:**

Этот класс отвечает за обработку входящих запросов от TransportConnection. Он получает сообщение от соединения, десериализует его в объект BodyMessage и выполняет команду, указанную в BodyMessage, с помощью CommandsInterface. Затем он отправляет клиенту статус выполнения команды.

Поля:
```
private CommandsInterface commands;

private JsonSerializer<BodyMessage> jsonSerializer;
 ```
В классе реализуется один метод `void handle(TransportConnection transportConnection)` в рамках интерфейса MessageHandler. Этот метод предназначен здесь в этом классе для выполнения команд и получения статуса выполнения команды.

**Класс ResponseHandler implements MessageHandler<BodyMessage>:**

Этот класс отвечает за обработку исходящих ответов, отправляемых клиентам. Он получает объект BodyMessage, содержащий ответное сообщение, и использует ChatInterface и TransportFactory для создания соединения и отправки ответа указанному клиенту.

Поля:
```
private ChatInterface chatInterface;

private TransportFactory transportFactory;
```
В классе реализуется один метод `void handle(BodyMessage message)` в рамках интерфейса MessageHandler. Этот метод обрабатывает исходящее ответное сообщение для отправки клиенту. Он создает TransportConnection с помощью TransportFactory и отправляет ответное сообщение клиенту.
Если возникает исключение IOException, оно регистрирует сообщение об ошибке и удаляет пользователя из интерфейса чата.


**Класс SimpleHandleThread<T> extends Thread implements  HandleThread:**

Реализация через обычный поток обработки сообщения.

Поля: 
```
private MessageQueue<T> queue;

private boolean isProcessing = false;

MessageHandler<T> messageHandler;
```

## Пакет sender:

Обработка сообщений от клиентов

**Интерфейс:**

•	**MessageSender** - Интерфейс для работы с сообщениями клиентами. 

**Класс SimpleMessageSender implements MessageSender**

Имплементация интерфейса MessageSender. 

Поля:
```
private LastMessages lastMessages;

private MessageQueue<BodyMessage> messageQueue;
```
В реализации метода `sendMessageToClient(String nickname, String message, ChatInterface chatClient)` – используется разбивка по отдельным “под” методам sendMessage и sendLastMessages

## Пакет app:

Этот пакет является основной реализацией работы сервера. В нем содержится только два класса:

•	**BaseServer implements Server** (интерфейс Server является базовым интерфейсом для описания поведения серверной части программы) – данный класс является основным классом – реализацией сервера и содержит такие методы как старт и стоп – сервер.

Поля:
```
private LastMessages lastMessages; //list of last messages

private ChatInterface chatInterface; //users in the chat

private StreamIO streamIO; //interface for working with input/output

private boolean stopped = false; //if server is stopped

//server input and output queues:

MessageQueue<TransportConnection> requestQueue;

MessageQueue<BodyMessage> responseQueue;

private Listener listener;
```
Конструктор этого класса содержит только три аргумента:

```public BaseServer(int port, StreamIO streamIO, TransportFactory transportFactory)```

В основном эти аргументы используются для создания экземпляра класса BaseServerListener – класса – слушателя. Он служит для обработки входящих сообщений, используя в своем конструкторе requestQueue

Для отправки сообщений используем экземпляр класса SimpleMessageSender(в его конструкторе используется responseQueue)
Также в конструкторе класса используется экземпляр класса CommandsInterface для получения списка всех команд.

Для получения CommandsInterface был создан метод - ```CommandsInterface getCommands(MessageSender messageSender, ChatInterface chatInterface)```, где в коллекцию команд добавляются все доступные на этот момент команды.

В классе реализуются два важных метода для запуска сервера – 

```void start()``` – старт сервера, стартуем класс – слушатель и через поток ввода/вывода считываем сообщения, до тех пор пока не поступит сообщение о команде выхода из чата или будет изменен булевый флаг об остановке сервера (Boolean stopped)

```void stop()``` – остановка сервера, прерываем класс – слушатель, закрываем все ресурсы, меняем булевый флаг об остановке сервера (stopped = true)

•	**BaseServerListener extends BaseListener implements Listener** – это класс – слушатель входящих сообщений от клиентов.
Содержит поле:

```MessageQueue<TransportConnection> requestQueue;``` - для обработки очереди входящих подключений

Соответственно, конструктор выглядит так:

```
public BaseServerListener(int portIn, StreamIO streamIO, TransportFactory transportFactory,
                          MessageQueue<TransportConnection> requestQueue) {
    super(portIn, streamIO, transportFactory);
    this.requestQueue = requestQueue;
}
```

В классе переопределяется унаследованный от BaseListener абстрактный метод worker() - в нем мы записываем все приходящие на сервер соединения от пользователей в очередь.
В очереди у нас находится список всех соединений с пользователями. 
