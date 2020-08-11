package strings_implementation.StringBuilder;

import static java.lang.System.out;

/**
 * ensureCapacity(int minimum) ensures the minimum size of the buffer of StringBuilder
 *
 * br.setLength() is used to set the length of the string within a StringBuilder.
 */
public class EnsureCapacityAndSetLengthDemo {
    public static void main(String[] args) {
        StringBuilder br = new StringBuilder();
        br.ensureCapacity(100);
        out.println(br.capacity());

        br.append("hello there how are you");
        br.setLength(br.length() - 4);
        out.println(br);
    }
}

