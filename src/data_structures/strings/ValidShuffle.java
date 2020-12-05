package data_structures.strings;

import data_structures.Pair;

import java.util.Arrays;
import java.util.List;

/**
 * Given two strings and one result string check whether the result string is a valid shuffle of one and two ?
 * Valid shuffle means the order in which characters are present in the two strings should be same in result string
 *
 * Approach
 *  -   match each character of the result string to either first string or second string
 *  -   if they match then we continue matching otherwise we return false
 *  -   At last if any of the counter are remaining in first or second string then we also return false
 *
 * Eg:
 *  a = "abcd" b = "efgh" result="aebf", as here you can see although all the characters of result string
 *  are present in a and b, then also we return false because rest of the characters are not present in
 *  result string like "cd" and "gh"
 */

public class ValidShuffle {
    public static void main(String[] args) {
        List<Pair<String, Pair<String, String>>> list = Arrays.asList(
                Pair.of("1xy2", Pair.of("xy", "12")),
                Pair.of("y12x", Pair.of("xy", "12"))
        );
        for(var pair : list) {
            String one = pair.getSecond().getFirst();
            String two = pair.getSecond().getSecond();
            String result = pair.getFirst();
            String isValid = (isValidShuffle(one, two, result)) ? " is a Valid" : " is not a Valid";
            System.out.println("result String = " + result + isValid + " shuffle of stringOne = " + one + " stringTwo = " + two);
        }
    }

    private static boolean isValidShuffle(String one, String two, String result) {
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < result.length()) {
            if(j < one.length() && one.charAt(j) == result.charAt(i)) {
                j++;
            }
            else if(k < two.length() && two.charAt(k) == result.charAt(i)) {
                k++;
            }
            else {
                return false;
            }
            i++;
        }
        return j >= one.length() && k >= two.length();
    }
}
