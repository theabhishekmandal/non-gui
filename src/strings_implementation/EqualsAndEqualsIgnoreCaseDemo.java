package strings_implementation;

import static java.lang.System.out;
public class EqualsAndEqualsIgnoreCaseDemo {
    public static void main(String[] args) {
        String hel = "HELLO";
        String one = "hello";
        out.println(hel.equals(one));
        out.println(hel.equalsIgnoreCase(one));
    }
}
