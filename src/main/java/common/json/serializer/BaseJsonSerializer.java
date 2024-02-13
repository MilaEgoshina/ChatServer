package common.json.serializer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Base class for Json serializer implementation
 */
public abstract class BaseJsonSerializer<T> implements JsonSerializer<T>{

    private Class<T> clazz;

    public BaseJsonSerializer(Class<T> clazz){

        this.clazz = clazz;
    }

    @Override
    public String serialize(T object) throws JsonProcessingException{

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Override
    public T deserialize(String object) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(object,clazz);
    }

}
