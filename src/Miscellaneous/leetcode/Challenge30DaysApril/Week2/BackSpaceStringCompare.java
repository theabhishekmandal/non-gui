package Miscellaneous.leetcode.Challenge30DaysApril.Week2;


import java.util.Scanner;

/**
 * Week2 day 2
 * ab#####cd#ba#
 * #####a##b#d
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 *
 * Note that after backspacing an empty text, the text will continue empty.
 *
 * Example 1:
 *
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * Example 2:
 *
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * Example 3:
 *
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * Example 4:
 *
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * Note:
 *
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 */

public class BackSpaceStringCompare {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] arr = {"ab#c", "ad#c", "ab##", "c#d#", "a##c", "#a#c", "a#c", "b"};
        for(int i = 0; i < arr.length; i += 2){
            String first = arr[i];
            String second = arr[i + 1];
            boolean isequal = solve(first, second);
            System.out.println(isequal);
        }
    }

    private static boolean solve(String first, String second) {
        first = solveString(first);
        second = solveString(second);
        return first.equals(second);
    }
    private static String solveString(String string){
        StringBuilder br = new StringBuilder();
        int hashCounter = 0;
        for(int i = string.length() - 1; i >= 0; i--){
            if(string.charAt(i) == '#'){
                hashCounter++;
            }
            else{
                if(hashCounter == 0) br.append(string.charAt(i));
                else
                    hashCounter--;
            }
        }
        return br.toString();
    }
}
