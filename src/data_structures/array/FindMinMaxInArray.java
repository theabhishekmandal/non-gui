package data_structures.array;

import java.util.Arrays;
import java.util.Random;

/**
 * Given an array of numbers, find the maximum and minimum element in the array with less number of comparisons
 * use findMinMax2 method
 * Approach:
 *  -   if the length of array is odd then use then take the first element as the min and max element
 *  -   if the length of array is even then take the max of first and second element as max and min of first and
 *      second element as min
 *  -   if the length is odd then next comparison location starts from 1 index and for even 2
 *  -   first compare i with i + 1, if i is greater then take max between current max and arr[i]. Otherwise,
 *      take min between current min and arr[i + 1].
 *  -   Do similar for other cases also
 */
public class FindMinMaxInArray {
    public static void main(String[] args) {
        var random = new Random();
        int n = 1 + random.nextInt(10);
        var arr = new int[n];
        for(int i = 0; i < arr.length; i++) arr[i] = -5 + random.nextInt(10);
        System.out.println("given array " + Arrays.toString(arr));
        System.out.println("min and max " + Arrays.toString(findMinMax(arr)));
        System.out.println("min and max " + Arrays.toString(findMinMax2(arr)));
    }

    private static int[] findMinMax(int[] arr) {
        if(arr == null || arr.length == 0) {
            return new int[]{};
        }
        int min = arr[0];
        int max = arr[0];
        int counter = 0;
        for(int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        System.out.println(counter);
        return new int[]{min, max};
    }

    private static int[] findMinMax2(int[] arr) {
        if(arr == null || arr.length == 0) {
            return new int[]{};
        }
        int min;
        int max;
        int start;
        if(arr.length % 2 == 0) {
            min = Math.min(arr[0], arr[1]);
            max = Math.max(arr[0], arr[1]);
            start = 2;
        }
        else {
            min = arr[0];
            max = arr[0];
            start = 1;
        }

        for(; start < arr.length; start += 2) {
            if(arr[start] > arr[start + 1]) {
                max = Math.max(arr[start], max);
                min = Math.min(arr[start + 1], min);
            }
            else {
               max = Math.max(arr[start + 1], max);
               min = Math.min(arr[start], min);
            }
        }
        return new int[]{min, max};
    }
}
