package data_structures.array;

import java.util.Scanner;
/**
 * This is an algorithm to find the maximum sum in a contiguous subArray
 * So it works as follows
 * if int[] arr = {1, -3, 4, -1, 2, 1, -5, 4} then:
 *      -   We have two variables currMax and max
 *      -   currMax is used to check if maxSumTillNow + currentValue is greater than
 *          currentValue or not
 *          for eg: if currMax = 1 then check if 1 + -3 > -3 if yes then currMax = -2
 *      -   max is used to store the maximum value that has been found because there can be n
 *          number of currMax values. We have to find the maximum of those values
 *
 *      -   eg arr = [1, -3, 4, -1, 2, 1, -5, 4], currMax = 1 and max = 1
 *          -   for i = 1, currMax = max(-3, 1 + -3) currMax = -2, maximum = max(-2, 1) = 1
 *          -   for i = 2, currMax = max(4, -2 + 4) currMax = 4, maximum = max(4, 1) = 4
 *          -   for i = 3, currMax = max(-1, 4 + -1) currMax = 3, maximum = max(3, 4) = 4
 *          -   for i = 4, currMax = max(2, 3 + 2) currMax = 5, maximum = max(5, 4) = 5
 *          -   for i = 5, currMax = max(1, 5 + 1) currMax = 6, maximum = max(6, 5) = 6
 *          -   for i = 6, currMax = max(-5, 6 + -5) currMax = 1, maximum = max(1, 6) = 6
 *          -   for i = 7, currMax = max(4, 1 + 4) currMax = 5, maximum = max(5, 6) = 6
 *
 *          - so max sub array sum is maximum = 6
 *
 * Basically the idea is to take the max of given sum till now including the current index.
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
