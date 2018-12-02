package StringsImplementation.StringBuilder;

import static java.lang.System.out;

public class SubstringDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("hello there");
        out.println(sb.substring(2));
        out.println(sb.substring(0, 5));
    }
}
