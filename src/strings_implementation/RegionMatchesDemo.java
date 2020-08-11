package strings_implementation;
/**
 *  regionMatches() method compares a specific region inside a string with another specific region in another string.
 *
 *  it's syntax is given by:  boolean regionMatches(int startIndex, String str2, int str2StartIndex, int numChars);
 *                            boolean regionMatches(boolean IgnoreCase, int startIndex, String str2, int str2StartIndex, int numChars);
 *
 */

import static java.lang.System.*;
public class RegionMatchesDemo {
    public static void main(String[] args) {
        String hel = "Hey hello hola hi";
        String one = "Hi";
        out.println(hel.regionMatches(15, one,0, 2 ));
        out.println(hel.regionMatches(true,15, one,0, 2 ));
    }
}
