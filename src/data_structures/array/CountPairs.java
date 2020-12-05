package data_structures.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of N integers, and an integer K, find the number of pairs of elements in the array whose sum is equal to K.
 *
 *
 * Example 1:
 *
 * Input:
 * N = 4, K = 6
 * arr[] = {1, 5, 7, 1}
 * Output: 2
 * Explanation:
 * arr[0] + arr[1] = 1 + 5 = 6
 * and arr[1] + arr[3] = 5 + 1 = 6.
 *
 * Example 2:
 *
 * Input:
 * N = 4, X = 2
 * arr[] = {1, 1, 1, 1}
 * Output: 6
 * Explanation:
 * Each 1 will produce sum 2 with any 1.
 *
 * Approach:
 *  -   To find the pairs that have sum equal to k, then there are two conditions
 *      -   if i is a element then k - i should also be present in the array
 *      -   i and k - i may or may not represent the same value, for eg k = 2 and i = 1, then i == k - i
 *  -   First create a count map for each element
 *  -   now for every element in array i search if k - i exists or not, if exists take the count
 *  -   now check if i == k - i, if yes then decrease the count, this is done so as to avoid counting twice
 *  -   now at last we divide by 2 because each pair is counted twice.
 *
 *  eg: if arr = [1, 1, 1, 1] countMap={1, 4}, count = 0
 *  count += 4, as i == k - i => count - 1 = 3
 *  count += 7, as i == k - i => count - 1 = 6
 *  count += 9, as i == k - i => count - 1 = 8
 *  count += 12, as i == k - i => count - 1 = 8
 *
 */

public class CountPairs {
    public static void main(String[] args) {
        System.out.println(getPairsCount(new int[]{1, 5, 7, 1}, 4, 6));
        System.out.println(getPairsCount(new int[]{1, 1, 1, 1}, 4, 2));
    }

    private static int getPairsCount(int[] arr, int n, int k) {
       Map<Integer, Integer> map = new HashMap<>();
       int count = 0;
       for(int i : arr) map.merge(i, 1, Integer::sum);
       for(int i : arr) {
           int temp = k - i;
           count += map.getOrDefault(temp, 0);
           if(temp == i) {
               count--;
           }
       }
       return count >> 1;
    }
}

