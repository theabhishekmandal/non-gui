package Miscellaneous.Recursion;
import java.util.*;

/**
 * This is program to generate all different type of sub-sequence of a string.
 * For eg: "abc" -> "a", "b", "c", "ab", "ac", "bc", "abc"
 * Note: "ca", "cb", "cab" will not be generated because these are not sub-sequences
 *
 * The basic principle is that we use recursion as follows:
 * 1. we start with the given string and an empty string, eg: ("abc", "")
 * 2. then we do recursion such that eg: ("bc", "") and ("bc", "a")
 *    in the former we are substring from starting index but not adding it,
 *    but in the latter we are adding it.
 * 3. when there is nothing left in the given string then that is our base condition.
 */
public class PrintAllSequence {
    public static void main(String args[]) {
        Scanner s  = new Scanner(System.in);
        String input = s.next();
        printAll(input , "");
    }
    private static void printAll(String input , String output) {
        if(input.length() == 0) {
            System.out.println(output);
            return;
        }
        printAll(input.substring(1), output);
        printAll(input.substring(1), output + input.charAt(0));
    }
}
