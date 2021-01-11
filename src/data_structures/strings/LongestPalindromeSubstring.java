package data_structures.strings;

import java.util.AbstractMap.SimpleEntry;

/**
 * Given a string, find the longest palindrome sub-string out of it.
 *
 * Approach:
 *  -   One approach is the dynamic programming one
 *  -   Second approach is for every even and odd length we will expand in both directions to check if the current
 *      indices have the palindrome sub-string or not.
 */
public class LongestPalindromeSubstring {
    public static void main(String[] args) {
        String str = "forgeeksskeegfor";
        System.out.println("Longest palindrome substring is: " + getLongestPalindromeSubString(str));
    }

    private static String getLongestPalindromeSubString(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        int maxLength = 1;
        int start = 0;
        for (int i = 1; i < input.length(); i++) {
            SimpleEntry<Integer, Integer> even = getLength(input, i, start, maxLength, false);
            start = even.getKey();
            maxLength = even.getValue();

            SimpleEntry<Integer, Integer> odd = getLength(input, i, start, maxLength, true);
            start = odd.getKey();
            maxLength = odd.getValue();
        }
        return input.substring(start, start + maxLength);
    }

    private static SimpleEntry<Integer, Integer> getLength(String input, int i, int start, int maxLength, boolean odd) {
        // if odd then i will become the centre i - 1 will be low and i + 1 is high
        int low = i - 1;
        int high;
        if (odd) {
            high = i + 1;
        } else {
            high = i;
        }

        while (low >= 0 && high < input.length() && input.charAt(low) == input.charAt(high)) {
            int currentPalindromeLength = high - low + 1;
            if (currentPalindromeLength > maxLength) {
                start = low;
                maxLength = currentPalindromeLength;
            }
            low--;
            high++;
        }
        return new SimpleEntry<>(start, maxLength);
    }
}
