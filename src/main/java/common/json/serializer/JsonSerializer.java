package common.json.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * Common Json serializer interface
 */
public interface JsonSerializer<T> {

     String serialize(T object) throws JsonProcessingException;

     T deserialize(String message) throws IOException;
}
