package StringsImplementation.StringBuilder;

import static java.lang.System.out;

/**
 * charAt(int index) and setCharAt(int index, char c)
 */

public class charAtAndSetCharAtDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        out.println("buffer before = " + sb);
        out.println("charAT(1) before =" + sb.charAt(1));

        sb.setCharAt(1, 'i');
        sb.setLength(2);
        out.println("buffer after = " + sb);
        out.println("charAt(1)after = " + sb.charAt(1));
    }
}
