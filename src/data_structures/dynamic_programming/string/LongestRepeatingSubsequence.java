package data_structures.dynamic_programming.string;

import java.util.Arrays;

/**
 * Given a string, find the longest common subsequence such that two subsequence have same character but at different
 * position.
 * Example if string is "aabaa", so the longest common subsequence will be aaa,
 * first string will be aaa at position(0, 1, 3) and another at (1, 3, 4)
 *
 * Approach:
 *  -   This is an extension of longest common subsequence, except when there is a match of two characters we have to also
 *      check that the indices are different.
 */
public class LongestRepeatingSubsequence {
    public static void main(String[] args) {
        String a = "aabaa";
        System.out.println(getLongestRepeatingSubsequence(a));
    }

    private static int getLongestRepeatingSubsequence(String one) {
        int length = one.length();
        int[][] arr = new int[length + 1][length + 1];
        int count = 0;
        System.out.println(Arrays.toString(arr[count++]));
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= length; j++) {
                char first = one.charAt(i - 1);
                char second = one.charAt(j - 1);
                if (first == second && i != j) {
                    arr[i][j] = 1 + arr[i - 1][j - 1];
                } else {
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
            }
            System.out.println(Arrays.toString(arr[count++]));
        }
        return arr[length][length];
    }
}
