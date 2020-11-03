package data_structures.array;

import java.util.Arrays;
import java.util.Random;

/**
 * Given an array of ints and a number k. Modify the array such that k can be added or subtracted to a value only once.
 * After that find the minimum distance between the smallest and the largest value
 *
 * For example
 *  arr = [1, 5, 8, 10] and k = 3, then arr = [3, 3, 6, 8] diff = 8 - 3 = 5, we added 3 to 1 and subtracted
 *  3 from 10, so as to minimize the distance between min and max value
 *
 *  Approach:
 *      -   First sort the array, initialise small = arr[0] + k and big = arr[arr.length - 1] - k
 *      -   small and big are initialised because we want to minimize the distance between the largest and smallest value
 *      -   After that we need to iterate from 1 to arr.length - 2 index, to search for a value such that
 *              -   it is smaller(arr[i] - k) than the small
 *              -   it is larger(arr[i] + k) than the big
 *      -   and we find any of the above we will take the minimum of min(max - min), which will give the minimum
 *          distance between the largest and smallest value
 */
public class FindMinDiffInHeights {
    public static void main(String[] args) {
        var random = new Random();
        int[] arr = new int[10 + random.nextInt(10)];
        for(int i = 0; i < arr.length; i++) arr[i] = random.nextInt(arr.length);
        int k = random.nextInt(arr.length);
        int[] brr = arr.clone();

        System.out.println("array values = " + Arrays.toString(arr) + " k = " + k);
        int diff = findMinDiffInHeights(arr, k);
        System.out.println("Array values in sorted order = " + Arrays.toString(arr) + "\nmin Diff in Heights = " + diff);

        System.out.println("Detailed Explanation " + findMinDiffInHeightsDetailed(brr, k));
    }

    private static int findMinDiffInHeights(int[] arr, int k) {
        if(arr == null || arr.length == 0) throw new NullPointerException("array cannot be null or empty");
        Arrays.sort(arr);
        int big = arr[arr.length - 1] - k;
        int small = arr[0] + k;
        if(small > big) {
            int temp = big;
            big = small;
            small = temp;
        }
        int ans = big - small;
        for(int i = 1; i < arr.length - 1; i++) {
            int max = Math.max(arr[i] + k, big);
            int min = Math.min(small, arr[i] - k);
            ans = Math.min(ans, max - min);
        }
        return ans;
    }

    private static int findMinDiffInHeightsDetailed(int[] arr, int k) {
        if(arr == null || arr.length == 0) throw new NullPointerException("array cannot be null or empty");
        Arrays.sort(arr);
        int big = arr[arr.length - 1] - k;
        int small = arr[0] + k;
        if(small > big) {
            int temp = big;
            big = small;
            small = temp;
        }
        int ans = big - small;
        int[] brr = arr.clone();
        brr[0] = small;
        brr[brr.length - 1] = big;
        for(int i = 1; i < arr.length - 1; i++) {
            int max = Math.max(arr[i] + k, big);
            if(arr[i] + k > big) {
                brr[i] = arr[i] + k;
            }
            int min = Math.min(small, arr[i] - k);
            if(arr[i] - k < small) {
               brr[i] = arr[i] - k;
            }
            ans = Math.min(ans, max - min);
            System.out.println(Arrays.toString(brr) + " diff = " + (max - min));
        }
        return ans;
    }
}
