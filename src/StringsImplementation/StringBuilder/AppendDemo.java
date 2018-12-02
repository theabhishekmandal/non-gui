package StringsImplementation.StringBuilder;

import static java.lang.System.out;

/**
 * append() method can be used to adding values at the end of the string.
 */
public class AppendDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("hello");
        sb.append("Abhishek Mandal");
        out.println(sb);
    }
}
