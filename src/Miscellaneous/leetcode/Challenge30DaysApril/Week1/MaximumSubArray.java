package Miscellaneous.leetcode.Challenge30DaysApril.Week1;

import java.util.Arrays;
import java.util.Random;

/**
 * Week 1 day 3
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * Example:
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 */
public class MaximumSubArray {
    public static void main(String[] args) {
        int n = 10;
        Random random = new Random();
        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++) arr[i] = -n / 2 + random.nextInt(n);
        System.out.println(Arrays.toString(arr));
        System.out.println(solve(arr));

    }

    private static int solve(int[] nums) {
        int val = 0;
        int currMax = Integer.MIN_VALUE;
        for(int i : nums){
            val = Math.max(val + i, i);
            currMax = Math.max(currMax, val);

        }
        return currMax;
    }
}
