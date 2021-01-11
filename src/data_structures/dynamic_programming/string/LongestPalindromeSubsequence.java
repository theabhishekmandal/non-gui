package data_structures.dynamic_programming.string;

import java.util.Arrays;
import java.util.List;

/**
 * This is based on dynamic programming 1) Overlapping Subproblems 2) Optimal Substructure
 *
 *  In this we start by calculating palindrome by length, so starting from length 1 to string length
 *  we find the max length which can have max palindrome subsequence
 *
 * 1) if two chars equal then it is given by: L(arr[i][j]) = 2 + arr[i + 1][j - 1]
 * 2) if two chars are not equal then : L(arr[i][j]) = max(arr[i + 1][j], arr[i][j - 1])
 *
 * Eg: if string input="agbdba"
 *  -   for length 1 every string is palindrome, so populate arr[i][j] = 1 where i == j
 *  -   now if there is a match then
 *              a  g  b  d  b  a
 *              0  1  2  3  4  5
 *
 *       suppose for length 6, i = 0 and j = 5 both a==a so new value will be
 *       arr[0][5] = 2 + arr[1][4] i.e 2 + characters matched at before subsequence(1, 4)
 *
 *
 *       and suppose for same length 6, i = 0 and j = 5 if both characters were different then
 *       arr[0][5] = max(arr[0][4], arr[1][5]) i.e taking max between excluding the left index
 *       and excluding the right index, because we want to take max subsequence of the two subsequences
 *
 */
public class LongestPalindromeSubsequence {
    public static void main(String[] args) {
        List<String> inputList = Arrays.asList("aabaa", "agbdba");

        inputList.forEach(x -> System.out.println(x + " longest palindromic substring length = " + getLongestPalindromicSubstring(x)));
    }

    private static int getLongestPalindromicSubstring(String input) {

        int[][] arr = new int[input.length()][input.length()];
        // every character of length one is automatically a palindromic substring
        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = 1;
        }

        // col value tells the range of the substring length
        for (int length = 2; length <= arr.length; length++) {
            for (int i = 0; i < arr.length - length + 1; i++) {
                int j = length + i - 1;
                char first = input.charAt(i);
                char second = input.charAt(j);
                if (first == second) {
                    arr[i][j] = 2 + arr[i + 1][j - 1];
                } else {
                    arr[i][j] = Math.max(arr[i + 1][j], arr[i][j - 1]);
                }
            }
        }

        return arr[0][arr.length - 1];
    }
}
