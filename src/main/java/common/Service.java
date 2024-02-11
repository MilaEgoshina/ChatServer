package common;


import lombok.Getter;

/**
 * Class for working with properties file and configuration
 */
@Getter
public class Service extends BaseService<Service> {


    private static Service instance;

    private String helloMessageForAll;
    private String goodbyeMessageForAll;
    private String lineSeparator;
    private int maxCountConnections;
    private int lastMessagesLength;
    private int maxThreadsResponse;
    private int maxThreadsRequest;

    private final int minPort = 1025;
    private final int maxPort = 65535;
    private final String encoding = "UTF-8";


    private Service(){
        super("src/main/resources/config.properties");

        this.helloMessageForAll = properties.getProperty("helloMessageForAll");
        this.goodbyeMessageForAll = properties.getProperty("goodbyeMessageForAll");
        this.maxCountConnections = Integer.parseInt(properties.getProperty("maxCountConnections"));
        this.lastMessagesLength = Integer.parseInt(properties.getProperty("lastMessagesLength"));
        this.maxThreadsRequest = Integer.parseInt(properties.getProperty("maxThreadsRequest"));
        this.maxThreadsResponse = Integer.parseInt(properties.getProperty("maxThreadsRequest"));
        this.lineSeparator = properties.getProperty("lineSeparator");
    }

    public static Service getInstance(){

        if(instance == null)
            instance = new Service();
        return instance;
    }
}
