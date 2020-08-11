package strings_implementation.StringBuilder;

import static java.lang.System.out;

/**
 * reverse() method is used to reverse the string
 */
public class ReverseDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("hello world");
        out.println(sb.reverse());
    }
}
