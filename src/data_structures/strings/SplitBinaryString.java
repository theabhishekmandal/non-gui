package data_structures.strings;

import java.util.Random;

/**
 * Given a binary string str of length N, the task is to find the maximum count of substrings str can be divided into
 * such that all the substrings are balanced i.e. they have equal number of 0s and 1s. If it is not possible to split
 * str satisfying the conditions then print -1.
 *
 * Example:
 *
 *     Input: str = “0100110101”
 *     Output: 4
 *     The required substrings are “01”, “0011”, “01” and “01”.
 *
 *     Input: str = “0111100010”
 *     Output: 3
 */
public class SplitBinaryString {
    public static void main(String[] args) {
        Random random = new Random();
        int n = 5 + random.nextInt(10);
        StringBuilder br = new StringBuilder();
        for (int i = 0; i < n; i++) {
            br.append(random.nextInt(2));
        }
        System.out.println("input string is = " + br  + "\nnumber of equal 0 and 1 character substring is " + countSubStrings(br.toString()));
    }

    private static int countSubStrings(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        int count = 0;
        int zero = 0;
        int one = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '0') {
                zero++;
            } else {
                one++;
            }

            if (one == zero) {
                count++;
            }
        }
        return one != zero ? -1 : count;
    }
}
