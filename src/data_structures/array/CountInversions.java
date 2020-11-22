package data_structures.array;

import java.util.Arrays;

/**
 * Given an array of integer elements. Determine how far is it from being sorted. If array is already sorted then
 * inversion count is 0, if array is in decreasing order then inversion count is maximum.
 * Inversion count is determined when arr[i] > arr[j] where i < j.
 * For example arr = [1, 20, 6, 4, 5], Inversion count is 5 because.
 * 20 > 6, 20 > 4, 20 > 5, 6 > 4, 6 > 5
 *
 * Approach
 * -   We are going with O(nlogn) approach, which is using mergesort
 * -   Now in merge sort when we try to merge left and right arrays, there can be two conditions i.e
 *      -   left array is sorted and right array is also sorted
 *      -   if for a given index i in left array and j in right array, if left[i] > right[j], this means
 *          we have total mid - i elements which are greater than right[j] (where mid is middle index). So this
 *          count will get added
 *
 *  -   Example: arr = [1, 20, 6, 4, 5]  count = (m + 1) - (l + i)
 *
 *      when merging left = [1, 20] right = [6],
 *              i = 1, j = 0, count = (1 + 1) - (0 + 1)then count = 1
 *
 *      when merging left = [1, 6, 20] right = [4, 5],
 *              i = 1, j = 0, count = (2 + 1) - (0 + 1) then count = 2
 *
 *      when merging left = [1, 6, 20] right = [4, 5] then count = 2 for index 2
 *             i = 1, j = 0, count = (2 + 1) - (0 + 1) then count = 2
 *
 *      IMPORTANT: here you are seeing for index i = 1, count inversion is calculated two times, because if there are mid - i elements
 *      then they are multiplied by the number of element present in right array which are smaller than left array
 *      In this case [4, 5] are 2 elements and number of mid - i elements are also two [6, 20], so total current inversion is
 *      2 * 2 = 4
 *      so total count = 5
 */
public class CountInversions {
    public static void main(String[] args) {
        int[] arr = { 1, 20, 6, 4, 5};
        Arrays.sort(arr);
        System.out.println(mergeSortAndCount(arr, 0, arr.length - 1));
        System.out.println(Arrays.toString(arr));
    }
    private static int mergeSortAndCount(int[] arr, int l, int h) {
        int count = 0;
        if(l < h) {
            int m = l + ((h - l) / 2);
            count += mergeSortAndCount(arr, l, m);
            count += mergeSortAndCount(arr, m + 1, h);
            count += merge(arr, l, m, h);
        }
        return count;
    }

    private static int merge(int[] arr, int l, int m, int h) {
        int[] left = Arrays.copyOfRange(arr, l, m + 1);
        int[] right = Arrays.copyOfRange(arr, m + 1, h + 1);
        int swaps = 0;
        int i = 0;
        int j = 0;

        int k = l;
        while(i < left.length && j < right.length) {
            if(left[i] < right[j]) {
                arr[k++] = left[i++];
            }
            else {
                swaps += (m + 1) - (l + i); // m + 1 is the length of left subArray and l + i gives the indices
//                swaps += left.length - i; you can do this too
                arr[k++] = right[j++];
            }
        }
        while(i < left.length) {
            arr[k++] = left[i++];
        }
        while(j < right.length) {
            arr[k++] = right[j++];
        }
        return swaps;
    }
}
