package data_structures.strings;

import java.util.Arrays;

/**
 * Given a string, print all subsequences of that string
 * Ex: if a string="abc" then there will be 2^3 - 1 = 7 subsequences [a, ab, b, bc, c, ac, abc]
 *
 * Approach:
 *  -   first calculate the number of subsequences using the length
 *  -   then for each number and for all indices of string check whether that bit is set or not.
 *  -   Check below for better understanding.
 *
 *  Second method using recursion
 * The basic principle is that we use recursion as follows:
 * 1. we start with the given string and an empty string, eg: ("abc", "")
 * 2. then we do recursion such that eg: ("bc", "") and ("bc", "a")
 *    in the former we are substring from starting index but not adding it,
 *    but in the latter we are adding it.
 * 3. when there is nothing left in the given string then that is our base condition.
 *
 * eg: string = "klm", think of the solution as follows
 *  -   one time we need k to make klm and one time we don't need k as we need lm
 *  -   Similarly, one time we need l to make lm and one time we don't need l as wee need to make m
 *  -   So printAll(input.substring(1), "" + input.charAt(0)) - this is the first condition
 *         printAll(input.subString(1), "") - this is the second condition
 */
public class StringSubsequence {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(getAllSubSequence("abc")));
        System.out.println(Arrays.deepToString(getAllSubSequence("ab")));
        printAll("klm", "");
    }

    private static void printAll(String input , String output) {
        if(input.length() == 0) {
            System.out.println(output);
            return;
        }
        printAll(input.substring(1), output);
        printAll(input.substring(1), output + input.charAt(0));
    }

    private static String[] getAllSubSequence(String one) {
        if (one == null || one.length() == 0) return new String[]{};

        int len = one.length();
        int limit = (2 << len - 1) - 1;
        String[] arr = new String[limit];
        int counter = 0;
        for (int i = 1; i <= limit; i++) {
            StringBuilder br = new StringBuilder();
            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) != 0) {
                    br.append(one.charAt(j));
                }
            }
            arr[counter++] = br.toString();
        }
        return arr;
    }
}
