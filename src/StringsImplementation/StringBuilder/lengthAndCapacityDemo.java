package StringsImplementation.StringBuilder;

import static java.lang.System.out;

/**
 *  The length() method in StringBuilder returns the total length of the String passed to StringBuilder.
 *  The capacity() method returns the actual capacity of holding the Strings. The default capacity is of 16
 */
public class lengthAndCapacityDemo {
    public static void main(String[] args) {
        StringBuilder br = new StringBuilder("Hey hello how are you doing");
        out.println(br.capacity());
        out.println(br.length());
    }
}
