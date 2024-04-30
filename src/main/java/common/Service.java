package common;


import lombok.Getter;

/**
 * The Service class provides configuration properties and settings for the application.
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


    /**
     * Initializes the Service with configuration properties from a file.
     */
    private Service(){
        super("src/main/resources/config.properties");

        this.helloMessageForAll = properties.getProperty("helloMessageForAll");
        this.goodbyeMessageForAll = properties.getProperty("goodbyeMessageForAll");
        this.maxCountConnections = Integer.parseInt(properties.getProperty("maxCountConnections"));
        this.lastMessagesLength = Integer.parseInt(properties.getProperty("lastMessagesLength"));
        this.maxThreadsRequest = Integer.parseInt(properties.getProperty("maxThreadsRequest"));
        this.maxThreadsResponse = Integer.parseInt(properties.getProperty("maxThreadsResponse"));
        this.lineSeparator = properties.getProperty("lineSeparator");
    }

    /**
     * Returns the singleton instance of the Service class.
     * @return the instance of the Service class
     */
    public static Service getInstance(){

        if(instance == null)
            instance = new Service();
        return instance;
    }
}
