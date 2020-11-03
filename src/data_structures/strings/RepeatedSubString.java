package data_structures.strings;

import java.util.Random;

/**
 *
 * Given a non-empty string check if it can be constructed by taking a substring of it and
 * appending multiple copies of the substring together.
 * You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 *
 *
 *
 * Example 1:
 *
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 * Example 2:
 *
 * Input: "aba"
 * Output: False
 * Example 3:
 *
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 *
 * Approach
 *  -   To check if a given substring can construct the original string, first check will substring length can construct
 *      the same length input string or not
 *  -
 */
public class RepeatedSubString {
    private static final Random random = new Random();
    public static void main(String[] args) {
        int t = 1  + random.nextInt(5);
        while(t-- > 0) {
            String string = null;
            if(random.nextBoolean()) {
               string = getRepeatedString();
            }
            else {
                string = getNonRepeatedString();
            }
            System.out.println(
                    "input string " + string + "\n" +
                    "is string can be reconstructed using substring " + isStringCanBeReconstructed(string));
        }
    }

    private static boolean isStringCanBeReconstructed(String string) {
        int length = string.length();
        for(int i = 1; i < length; i++) {
            if(length % i == 0) {
               String subString = string.substring(0, i).repeat(length / i);
               if(string.equals(subString)) {
                  return true;
               }
            }
        }
        return false;
    }

    private static String getNonRepeatedString() {
        StringBuilder builder = new StringBuilder(getRepeatedString());
        builder.insert(random.nextInt(builder.length()), (char)('a' + random.nextInt(26)));
        return builder.toString();
    }

    private static String getRepeatedString() {
        int length = 1 + random.nextInt(3);
        char[] arr = new char[length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = (char)('a' + random.nextInt(26));
        }
        return new String(arr).repeat(3);
    }
}
