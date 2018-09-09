package String_Handling;

/**
 *  This is an example of charAt() and getChars() method, charAt() is used to get single character at a time
 *  whereas getChars() is used to take variable length of characters.
 */
import java.util.Arrays;

import static java.lang.System.*;
public class CharAtAndGetCharsDemo {
    public static void main(String[] args) {
        char c = "abc".charAt(0);
        out.println(c);

        /*
            syntax of getChars method is: void getChars(int sourceStart, int sourceEnd, char target[], int targetStart)
         */

        String s = "This is a demo of getChars method.";
        char buf[] = new char[4];
        s.getChars(10, 14, buf, 0);
        out.println(Arrays.toString(buf));

    }
}
