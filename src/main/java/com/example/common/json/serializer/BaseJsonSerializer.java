package com.example.common.json.serializer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Base class for Json serialization implementation.
 * This class provides basic functionality for serializing and deserializing objects to and from JSON format.
 *
 * @param <T> the type of object to serialize and deserialize
 */
public abstract class BaseJsonSerializer<T> implements JsonSerializer<T>{

    private Class<T> clazz;

    /**
     * Constructor for BaseJsonSerializer class.
     * @param clazz the class of the object to serialize and deserialize
     */
    public BaseJsonSerializer(Class<T> clazz){

        this.clazz = clazz;
    }

    /**
     * Serialize an object to a JSON String.
     *
     * @param object the object to serialize
     * @return the JSON String representation of the object
     * @throws JsonProcessingException if an error occurs during serialization
     */
    @Override
    public String serialize(T object) throws JsonProcessingException{

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    /**
     * Deserialize a JSON String to an object.
     *
     * @param object the JSON String to deserialize
     * @return the deserialized object
     * @throws IOException if an error occurs during deserialization
     */
    @Override
    public T deserialize(String object) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(object,clazz);
    }

}
