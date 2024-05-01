package com.example.common.json.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * This is a common JSON serializer interface.
 */
public interface JsonSerializer<T> {

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
}
