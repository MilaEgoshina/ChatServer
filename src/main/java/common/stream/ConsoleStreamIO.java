package common.stream;


import lombok.AllArgsConstructor;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Class for working with console-based I/O stream
 */
@AllArgsConstructor
public class ConsoleStreamIO implements StreamIO{

    Scanner scanner;
    PrintStream out;

    @Override
    public void print(String text) {

        out.println(text);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
