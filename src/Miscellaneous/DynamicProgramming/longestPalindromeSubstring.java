package Miscellaneous.DynamicProgramming;

import java.util.Scanner;
/**
 * Similar to Longest Palindrome subsequence, minor change is that
 * if the first and last characters are not matching then it's value will be
 * zero
 */

public class longestPalindromeSubstring {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String hel = s.next();
        int[][] arr = new int[hel.length()][hel.length()];
        for(int i = 0; i < arr.length; i++) arr[i][i] = 1;
        int max = Integer.MIN_VALUE;
        for(int col = 2; col <= arr.length; col++){
            for(int i = 0; i < arr.length - col + 1; i++){
                int j = i + col - 1;
                char one = hel.charAt(i);
                char two = hel.charAt(j);
                if(one == two){
                    arr[i][j] = 2 + arr[i + 1][j - 1];
                    max = Math.max(arr[i][j], max);
                }
                else arr[i][j] = 0;
            }
        }
        System.out.println(max);
    }
}
