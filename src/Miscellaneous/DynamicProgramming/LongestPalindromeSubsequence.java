package Miscellaneous.DynamicProgramming;
import java.util.Scanner;

/**
 * This is based on dynamic programming 1) Overlapping Subproblems 2) Optimal Substructure
 *
 * 1) if two chars equal then it is given by: L(arr[i][j]) = 2 + arr[i + 1][j - 1]
 * 2) if two chars are not equal then : L(arr[i][j]) = max(arr[i + 1][j], arr[i][j - 1])
 */
public class LongestPalindromeSubsequence {
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        String hel = s.next();
        int[][] arr = new int[hel.length()][hel.length()];
        // every character of length one is automatically a palindromic substring
        for(int i = 0; i < arr.length; i++){
            arr[i][i] = 1;
        }

        // col value tells the range of the substring length
        for(int col = 2; col <= arr.length; col++){
            for(int i = 0; i < arr.length - col + 1; i++){
                int j = col + i - 1;
                char first = hel.charAt(i);
                char second = hel.charAt(j);
                // no need of this
                //if(first == second && col == 2) arr[i][j] = 2;
                if(first == second) arr[i][j] = 2 + arr[i + 1][j - 1];
                else arr[i][j] = Math.max(arr[i + 1][j], arr[i][j - 1]);
            }
        }
        System.out.println(arr[0][arr.length - 1]);
    }
}
