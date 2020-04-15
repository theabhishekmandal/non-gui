package Miscellaneous.leetcode.Challenge30DaysApril;

import java.util.Scanner;

import static java.lang.System.out;

/**
 * given an array containing non negative numbers, every element appears twice except one.
 * Find the single one.
 *
 * Approach
 *  if each number appears twice then we can xor all the elements to find the duplicate
 *  element because xoring duplicate elements result to zero.
 */
public class findingDuplicateNumber {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++) arr[i] = s.nextInt();

        int a = 0;
        for(int i : arr) a ^= i;

        out.println(a);
    }
}
