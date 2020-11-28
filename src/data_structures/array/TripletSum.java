package data_structures.array;

import java.util.Arrays;

/**
 * Given an array of integer elements, check whether if there are three elements whose sum is equal to k
 * For eg = [1, 4, 45, 6, 10, 8] and k = 22 then [4, 8, 10]
 *
 * Approach
 *  -   To find this first sort the array to avoid using O(n^3) complexity
 *  -   First fix the first element of the triplet
 *  -   Then fix two pointers, one at i + 1 and the other at n – 1. And look at the sum,
 *      -   If the sum is smaller than the required sum, increment the first pointer.
 *      -   Else, If the sum is bigger, Decrease the end pointer to reduce the sum.
 *      -   Else, if the sum of elements at two-pointer is equal to given sum then print the triplet and break.
 */

public class TripletSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 45, 6, 10, 8};
        int k = 22;
        System.out.println("Array = " + Arrays.toString(arr) + " k = " + k);
        System.out.println("Triplet sum" + (findTripletSum(arr, k)? "" : " not") + " found");
    }

    private static boolean findTripletSum(int[] arr, int k) {
        Arrays.sort(arr);
        for(int i = 0; i < arr.length; i++) {
            int l = i + 1;
            int r = arr.length - 1;
            while(l < r) {
                int sum = arr[i] + arr[l] + arr[r];
                if(sum == k) {
                    return true;
                }
                else if(sum < k) {
                    l++;
                }
                else {
                    r--;
                }
            }
        }
        return false;
    }
}
