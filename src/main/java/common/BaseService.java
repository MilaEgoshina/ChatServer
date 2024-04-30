package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

/**
 * The BaseService class is an abstract class that provides base functionality for service classes.
 * It loads properties from a specified file path using a Properties object.
 * @param <T> the type of the service class
 */
public abstract class BaseService<T> {

    protected Properties properties;

    /**
     * Constructs a BaseService with the specified file path.
     * Loads properties from the file using a Properties object.
     * @param path the file path to load properties from
     */
    protected BaseService(String path){

        this.properties = new Properties();
        try {

            FileInputStream fileInputStream = new FileInputStream(path);
            Reader reader = new InputStreamReader(fileInputStream,"UTF-8");
            properties.load(reader);
        }catch (IOException e){

            e.printStackTrace();
        }
    }
}
