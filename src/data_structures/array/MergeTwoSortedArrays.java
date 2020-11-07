package data_structures.array;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Given two sorted arrays, Merge them in non-decreasing order without using extra space.
 * Method1 is preferred because it doesn't use any extra space other than using the final array
 * which will hold both the two array values
 */

public class MergeTwoSortedArrays {
    public static void main(String[] args) {
        var random = new Random();
        int t = 1 + random.nextInt(5);
        while(t-- > 0) {
            int[] arr = new int[10];
            int[] brr = new int[10];
            for(int i = 0; i < arr.length; i++) {
               arr[i] = random.nextInt(100);
            }
            for(int i = 0; i < arr.length; i++) {
                brr[i] = random.nextInt(100);
            }
            Arrays.sort(arr);
            Arrays.sort(brr);
            System.out.println("Method1\nArrays before sorting arr = " + Arrays.toString(arr) + " brr = " + Arrays.toString(brr));
            System.out.println("Merged Array " + Arrays.toString(mergeTwoSortedArrays(arr, brr)));

            int[] crr = arr.clone();
            int[] drr = brr.clone();
            System.out.println("Method2\nArrays before sorting arr = " + Arrays.toString(crr) + " brr = " + Arrays.toString(drr));
            System.out.println("Merged Array " + Arrays.toString(mergeTwoSortedArrays2(crr, drr)) + "\n");
        }
    }

    private static int[] mergeTwoSortedArrays(int[] arr, int[] brr) {
        int[] crr = new int[arr.length + brr.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < arr.length && j < brr.length) {
            if(arr[i] < brr[j]) {
                crr[k] = arr[i];
                i++;
            }
            else {
                crr[k] = brr[j];
                j++;
            }
            k++;
        }
        while(i < arr.length) {
            crr[k] = arr[i];
            i++;
            k++;
        }
        while(j < brr.length) {
            crr[k] = brr[j];
            j++;
            k++;
        }
        return crr;
    }

    private static int[] mergeTwoSortedArrays2(int[] arr, int[] brr) {
        Map<Integer, Integer> map = new TreeMap<>();
        for(int i : arr) map.merge(i, 1, Integer::sum);
        for(int i : brr) map.merge(i, 1, Integer::sum);
        int[] crr = new int[arr.length + brr.length];
        int i = 0;
        for(var mapEntry : map.entrySet()) {
            while(mapEntry.getValue() != 0) {
               crr[i] = mapEntry.getKey();
               i++;
               mapEntry.setValue(mapEntry.getValue() - 1);
            }
        }
        return crr;
    }
}
