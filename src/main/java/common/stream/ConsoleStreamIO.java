package common.stream;


import lombok.AllArgsConstructor;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * The ConsoleStreamIO class implements the StreamIO interface for interacting with the console input and output.
 */
@AllArgsConstructor
public class ConsoleStreamIO implements StreamIO{

    Scanner scanner;
    PrintStream out;

    /**
     * Print the specified text to the output stream.
     * @param text the text to print
     */
    @Override
    public void print(String text) {

        out.println(text);
    }

    /**
     * Read input from the input stream.
     * @return the input read from the stream
     */
    @Override
    public String read() {
        return scanner.nextLine();
    }
}
