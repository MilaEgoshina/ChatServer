package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;


/**
 * Class for loading parameters from the configuration properties file
 */
public abstract class BaseService<T> {

    protected Properties properties;

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
