package Miscellaneous;

import java.util.Scanner;
/**
 * This is an algorithm to find the maximum sum in a contiguous subArray
 * So it works as follows
 * if int[] arr = {1, -3, 4, -1, 2, 1, -5, 4} then:
 *      -   We have two variables currMax and max
 *      -   currMax is used to check if maxSumTillNow + currentValue is greater than
 *          currentValue or not
 *          for eg: if currMax = 1 then check if 1 + -3 > -3 if yes then currMax = -2
 *      -   max is used to store the maximum value that has been found
 *
 * Basically the idea is to take the max of given sum till now including the current index
 * and the current index.
 */
public class MaxContiguousArraySum {
    private static int getMaxSubArraySum(int[] arr){
        int currMax = arr[0];
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            currMax = Math.max(arr[i], currMax + arr[i]);
            max = Math.max(currMax, max);
        }
        return max;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++) arr[i] = s.nextInt();
        System.out.println(getMaxSubArraySum(arr));
    }
}
