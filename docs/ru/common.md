# COMMON - Общий функционал для обеспечения работы сервера и клиента

## Пакет common:

Общие команды для обеспечения работы сервера и клиента. Содержит:

1.	**Пакет command:**

**Интерфейсы:**

•	**Command** – базовый общий интерфейс для общего поведения остальных имплементируемых от него команд 

•	**CommandsInterface** – операции для работы с коллекцией команд

Методы:
```
void add(Command command);

void remove(String name);

int size();

Collection<Command> getAllCommands();

boolean contains(String name);

Command getByName(String name);
```

**КЛАССЫ:**

• **BaseCommand** – базовый абстрактный класс для всех команд, имплементирующий интерфейс Command.

Поля:
```
protected String name;

protected String description;

protected MessageSender messageSender;

protected ChatInterface chatInterface;
```

Остальные классы наследуются от этого базового абстрактного класса. В них всех реализуется метод `Status execute(BodyMessage bodyMessage) throws IOException` – процесс исполнения команд и статус их выполнения. Список классов:


•	**CountUserCommand extends BaseCommand** – отправляет пользователю информацию о количестве пользователей в чате

•	**ExitCommand extends BaseCommand** – «удаляет» пользователя из чата и отправляет всем остальным уведомление о том, что этот клиент «прощается» со всем чатом

•	**HelpCommand extends BaseCommand** – помимо полей, унаследованных от базового класса, содержит еще поле:

•	**LoginCommand extends BaseCommand** – команда для авторизации пользователя. Если такого пользователя нет в коллекции клиентов, то добавляем его в коллекцию и рассылаем остальным клиентам приветственное сообщение от этого пользователя.

•	**MessageCommand extends BaseCommand** - помимо полей, унаследованных от базового класса, содержит еще поле:
CommandsInterface commands(интерфейс для работы с коллекциями команд)

Этот класс нужен для системной отправки сообщений. В нем из сообщения (как объекта BodyMessage) берется текст и проверяется, есть ли такая команда в коллекции, и если есть, данная команда выполняется, и возвращается результат выполнения в виде статуса.

**Класс CommandFactory**

Класс CommandFactory предоставляет статические методы для создания различных типов команд:

* static Command getCountUserCommand(MessageSender messageSender, ChatInterface chatInterface)


* static Command getExitCommand(MessageSender messageSender, ChatInterface chatInterface)


* static Command getHelpCommand(CommandsInterface commandsInterface, MessageSender messageSender, ChatInterface chatInterface)


* static Command getLoginCommand(MessageSender messageSender, ChatInterface chatInterface)


* static Command getMessageCommand(MessageSender messageSender, ChatInterface chatInterface, CommandsInterface commandsInterface)


* static CommandsInterface getCommandsCollection()

## Пакет input:

В этом пакете происходят операции по валидации (проверки) ввода информации от пользователя. Т. е. этот пакет предназначен для ввода информации от пользователей (IP, port) и контроля этого ввода.

**КЛАССЫ:**

•	**Input<T>** - общий базовый абстрактный класс для конструкции остальных классов ввода информации. Этот класс представляет абстрактный класс ввода, который обрабатывает пользовательский ввод. Он предоставляет методы для получения пользовательских данных и их проверки с помощью ValidatorInterface. Он также содержит приветственные сообщения и сообщения об ошибках, которые будут отображаться пользователю.

Поля:
```
String headerWelcomeMessage;

String headerErrorMessage;

StreamIO streamIO;
```

•	**InputIp extends Input<String>** - класс для проверки ввода IP – address.
Реализуем в нем два абстрактных метода от унаследованного класса:

```
@Override
protected ValidatorInterface getValidator(String text) {

    return ValidatorFactory.getIpValidator(text);
}

@Override
public String getValue() {
    return super.getValueAfterValidation();
}
```
•	**InputPort extends Input<Integer>** - класс для ввода и последующей валидации порта.
В классе есть дополнительные поля:
```
boolean isUsedValidation;

TransportFactory transportFactory;
```
Эти поля нужны для реализации унаследованного от абстрактного класса метода:
```
protected ValidatorInterface getValidator(String text) {
    return ValidatorFactory.getPortValidation(text, Service.getInstance().getMinPort(),
            Service.getInstance().getMaxPort(), isUsedValidation, transportFactory);
}


public static ValidatorInterface getPortValidation(String value, int minPort, int maxPort, Boolean withValidationIsUsed, TransportFactory transportFactory){

    ListValidation portValidation = new ListValidation();
    portValidation.add(getIntegerNumberValidator(value));
    portValidation.add(getRangeNumberValidation(value,minPort,maxPort));
    if(withValidationIsUsed) portValidation.add(getNotUsedPortValidation(value,transportFactory));
    return portValidation;
}
```
•	**InputWorker** – класс отвечает за обработку ввода пользователем различных параметров, связанных с сетью.

Поля:
```
private StreamIO streamIO;
private TransportFactory transportFactory;
```

## Пакет json:
В этом пакете есть еще два пакета – bodymessage и serializer. Как видно из названия, данный пакет предназначен для работы с json -файлами (точнее даже сказать не именно с самими файлами, а с битовыми данными, которые образуются в ходе сериализации файлов или данных), их сериализации и десерализации

**Разберем первый пакет serializer:**

**КЛАССЫ:**

•	**BaseJsonSerializer<T> implements JsonSerializer<T>** - базовый класс для реализации сериализации Json. Этот класс предоставляет базовые функции для сериализации и десериализации объектов в формате JSON и обратно. @param <T> тип объекта для сериализации и десериализации. Имеет поле и конструктор с ним же:

`Class<T> clazz;` (это поле как раз необходимо для метода десериализации)

Класс в дальнейшем используется в BodyMessageJsonSerializer для сериализации и десериализации сообщений.

**ИНТЕРФЕЙСЫ:**

•	**JsonSerializer<T>** - интерфейс для выполнения операций сериализации и десериализации. Содержит методы:

```
    /**
     * Serialize an object to JSON representation.
     *
     * @param object The object to serialize.
     * @return The JSON representation of the object.
     * @throws JsonProcessingException If an error occurs during serialization.
     */
    String serialize(T object) throws JsonProcessingException;

    /**
     * Deserialize a JSON message to an object.
     *
     * @param message The JSON message to deserialize.
     * @return The deserialized object.
     * @throws IOException If an error occurs during deserialization.
     */
    T deserialize(String message) throws IOException;
```

**Класс MessageFactory:**

Класс MessageFactory отвечает за создание экземпляров класса BodyMessage. Содержит метод:
```
    /**
     * Creates a new BodyMessage object with the specified parameters.
     *
     * @param command the command of the message
     * @param nickName the nickname of the sender
     * @param message the content of the message
     * @param ip the IP address of the sender
     * @param port the port number of the sender
     * @return a new BodyMessage object with the specified parameters
     */
    public static BodyMessage createBodyMessage(String command, String nickName, String message, String ip, int port){
        return new BodyMessage(command, nickName, message, ip, port);
    }
```

## Пакет bodymessage:

**КЛАССЫ:**

•	**BodyMessage** – Этот класс представляет тело сообщения, которое можно отправить в формате JSON. Поля:
```
private String command;

private String nickname;

private String ip;

private String text;

private int port;
```
•	**BodyMessageJsonSerializer extends BaseJsonSerializer<BodyMessage> implements JsonSerializer<BodyMessage>** - Этот класс представляет собой специальный сериализатор JSON для класса BodyMessage. Он расширяет класс BaseJsonSerializer и реализует интерфейс JsonSerializer. Он используется для сериализации объектов BodyMessage в формат JSON.

Например, в классе RequestHandler (server.handler) используется этот класс в качестве поля класса. В имплементируемом методе `handle` класс – сериалайзер из полученного строкового сообщения из соединения десериализирует сообщение в объект `BodyMessage`.
А в пакете клиента в классе `BaseMessageSender` этот класс используется наоборот для сериализации сообщения пользователя из BodyMessage в строковое значение.


## Пакет listener:

Предназначен для сервера и клиента и обеспечивает прослушивание соединений на портах с обоих сторон.

**ИНТЕРФЕЙС:**

•	**Listener** – Этот интерфейс предоставляет методы для запуска, прерывания и настройки прослушивателя в качестве демона.

**КЛАСС:**

•	**BaseListener extends Thread implements Listener** – Класс реализует базовую функциональность прослушивателя для получения и отправки сообщений. Он расширяет класс Thread и реализует интерфейс Listener.

Поля:
```
protected TransportListener transportListener (в данном классе реализуем метод accept(), через который сервер прослушивает входящие соединения, но этот класс мы не передаем напрямую в конструктор, а образуем его из TransportFactory);

protected StreamIO streamIO;
```
Также в этом классе есть абстрактный метод `abstract void worker() throws IOException` – который имплементируется в сервере и клиенте отдельно в зависимости от потребностей.
Так как мы унаследуем этот класс от Thread, то можем переопределить в нем метод run().

## Пакет stream:

Пакет для работы и контроля ввода/вывода информации.

**ИНТЕРФЕЙСЫ:**

•	**StreamIO** – Интерфейс определяет методы для печати текста и чтения входных данных из потока.:

```
void print(String text); 

String read();
```
**КЛАССЫ:**

•	**ConsoleStreamIO implements StreamIO.**

Класс ConsoleStreamIO реализует интерфейс StreamIO для взаимодействия с вводом и выводом консоли.
Поля:
```
Scanner scanner; - для чтения данных с консоли

PrintStream out; - для распечатки данных
```

## Пакет transport:
Этот пакет отвечает за передачу данных через сокеты и является основным пакетом для транспортировки информации. В нем также содержится отдельный пакет tcp, в котором поочередно имплементируем следующие интерфейсы:

•	**TransportConnection** – интерфейс для соединений клиент – сервер. В нем в дальнейшем будем использовать класс Socket.

Используется в клиенте в классе `BaseClientListener` в методе `worker()` для создания соединения с сервером, также в классе `BaseMessageSender` в методе `sendMessage(BodyMessage bodyMessage)` – создаем соединение и через него отправляем сообщение 

Используется на сервере в классе `ResponseHandler` – в методе `handle(BodyMessage message)` создаем соединение и отправляем через него текст сообщения.

В классе `RequestHandler` используем TransportConnection в качестве аргументов метода и с помощью него получаем сообщение.

•	**TransportListener** – интерфейс для сервер – слушателя. В нем в дальнейшем будем использовать класс ServerSocket.

Этот интерфейс используется в качестве поля класса в BaseListener.

Также используем этот интерфейс в пакете валидации при проверке – используется ли данный порт уже или нет (в методе validate()). В случае успешного создания экземпляра класса TransportListener вводимый порт считается неиспользованным и проходит валидацию.

•	**TransportFactory** – класс – фабрика для создания слушателя или соединения клиент – сервер.

**КЛАССЫ:**

•	**TcpSocketTransportConnection implements TransportConnection** - Класс, который реализует интерфейс TransportConnection для управления соединением через сокет TCP.

Поля:
```
private Socket socket;

private OutputStreamWriter writer;

private BufferedReader reader;

private String encoding;

final String lineSepaейс исrator = Service.getInstance().getLineSeparator();
```

•	**TcpSocketTransportListener implements TransportListener** - реализация слушателя через TCP Socket

Поля: 
```
private ServerSocket serverSocket;

private String encoding;
```
В методе `TransportConnection accept() throws IOException` возвращается объект типа TransportConnection, так как мы устанавливаем соединение с клиентом на сокете, в котором сервер ожидает (принимает) подключение.


## Пакет validate:

Пакет для валидации данных.

Имеет один единственный интерфейс – **ValidatorInterface**. Метод - `boolean validate()`: общий метод для всех классов пакета, в котором и происходит валидация.

**КЛАССЫ:**

•	**abstract class BaseValidator** – общий базовый абстрактный класс валидации.

Поля:
```
protected String value;
```

•	**IpValidator extends BaseValidator implements ValidatorInterface** - проверка IP на корректность ввода


•	**ListValidation implements ValidatorInterface** - компоновка проверок и проверка как единое

Поля:

`private List<ValidatorInterface> listValidator = new ArrayList<>();`

Используется в классе ValidatorFactory в методе `ValidatorInterface getPortValidation(String value, int minPort, int maxPort, Boolean withValidationIsUsed, TransportFactory transportFactory)`, где мы добавляем в лист валидации все остальные классы – валидаторы, а это проверка на то, что порт является целым числом, находится в нужном диапозоне и не занят другими соединениями.

•	**NotUsedPortsValidator extends BaseValidator implements ValidatorInterface** – класс для проверки порта на занятость. 

Поля:

```TransportFactory transportFactory;```

Это поле необходимо в методе `boolean validate()` для создания объекта типа TransportListener, который является классом – слушателем и прослушивает соединения на входящем порту, соответственно если нам удается создать экземпляр класса TransportListener через TransportFactory, то считается, что вводимый порт прошел валидацию.


•	**RangeNumberValidation extends BaseValidator implements ValidatorInterface** – класс для проверки на вхождение числа в заданный диапазон.

Поля:
```
private int min;

private int max;
```
Как раз эти поля нужны для создания ограничительного диапазона с дальнейшей проверкой.


•	**ValidateIntegerNumber extends BaseValidator implements ValidatorInterface** – класс для проверки на целое неотрицательное число длиной от 4 до 5 сиволов

•	**ValidatorFactory** - класс – фабрика для создания элементов проверки.

Один из важных методов в этом классе – метод `static ValidatorInterface getPortValidation(String value, int minPort, int maxPort, Boolean withValidationIsUsed, TransportFactory transportFactory)`

В нем мы используем класс ListValidation для «сбора» всех видов валидации и конечной проверки вводимого порта. Порт поочередно проходит проверку сначала getIntegerNumberValidator, потом getRangeNumberValidation, и в конце getNotUsedPortValidation
