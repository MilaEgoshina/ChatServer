package com.example.common.stream;


/**
 * The StreamIO interface defines methods for printing text and reading input from a stream.
 */
public interface StreamIO {

    /**
     * Print the specified text to the stream.
     * @param text the text to print
     */
    void print(String text);

    /**
     * Read input from the stream.
     * @return the input read from the stream
     */
    String read();
}
