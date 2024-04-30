package common.stream;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleStreamIOTest {

    @After
    public void tearDown() throws Exception {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void testPrint() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        StreamIO streamIO = new ConsoleStreamIO(new Scanner((System.in)), System.out);

        streamIO.print("hello");

        Assert.assertEquals(byteArrayOutputStream.toString(), "hello\r\n");
    }

    @Test
    public void testRead() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(("59990").getBytes());
        System.setIn(in);
        StreamIO streamIO = new ConsoleStreamIO(new Scanner((System.in)), System.out);

        String result = streamIO.read();

        Assert.assertEquals(result, "59990");
    }
}
