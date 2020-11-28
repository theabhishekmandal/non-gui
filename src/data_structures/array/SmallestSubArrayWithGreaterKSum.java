package data_structures.array;

import java.util.Arrays;
import java.util.List;
/**
 * Given an array of elements, find the smallest subArray whose sum is greater than or equal to K
 * Eg: arr = [1, 4, 45, 6, 0, 19] and k = 51 then sub-array = [45, 6]
 *
 * Approach:
 *  -   This problem is based on two pointer approach
 *  -   Using first pointer start adding all the elements and checking if sum does not exceeds k.
 *  -   Also for every element added increase a counter variable, this counter describes how many elements
 *      were added to form a number greater or equal to k
 *  -   Now using the second pointer keep subtracting the arr[j++] from the sum and decrease the counter variable
 *      take the min of counter
 *      eg: arr = [1, 11, 100, 1, 0, 200, 3, 2, 1, 250] k = 280
 *
 *      1st iteration
 *      here sum will exceed 280 when sum = 313 for i = [0, 1, 2, 3, 4, 5]
 *      now for j,  sum = sum - arr[j]
 *      j = 0, sum = 313 - 1 = 312
 *      j = 1, sum = 312 - 11 = 301
 *      j = 2, sum = 301 - 100 = 201
 *      minCount = 4
 *      here sum < k so we break,
 *
 *      2nd iteration
 *      And then next range we search for is from
 *      i = 6, sum = 201 + 3 = 204
 *      i = 7, sum = 204 + 2 = 206
 *      i = 8, sum = 206 + 1 = 207
 *      i = 9, sum = 207 + 250 = 457
 *      now,
 *      j = 3, sum = 457 - 1 = 456
 *      j = 4, sum = 456 - 0 = 456
 *      j = 5, sum = 456 - 200 = 256
 *      here sum < k then we break
 *      minCount is still 4 which we found in previous iteration
 *
 *
 *
 */
public class SmallestSubArrayWithGreaterKSum {
    public static void main(String[] args) {
        List<Pair<int[], int[]>> list = Arrays.asList(
                Pair.of(new int[]{1, 2, 45, 6, 0, 19}, new int[]{51}),
                Pair.of(new int[]{1, 10, 5, 2, 7}, new int[]{9}),
                Pair.of(new int[]{1, 11, 100, 1, 0, 200, 3, 2, 1, 250}, new int[]{280}),
                Pair.of(new int[]{1, 2, 4}, new int[]{8})
        );
        for(var pair : list) {
            System.out.println("Arrays is = " + Arrays.toString(pair.getFirst()) + "\nk = " + pair.getSecond()[0] + " and smallest " +
                    "subArray length is = " + getMinCount(pair.getFirst(), pair.getSecond()[0]));
        }
    }

    private static int getMinCount(int[] arr, int k) {
        int sum = 0;
        int count = 0;
        int minCount = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;

        while(i < arr.length) {
            while(i < arr.length && sum <= k) {
                sum += arr[i];
                count++;
                i++;
            }
            while(sum >= k) {
                minCount = Math.min(count, minCount);
                sum -= arr[j];
                j++;
                count--;
            }
        }
        if(count == arr.length && sum < k) {
            return -1;
        }
        return minCount;
    }
}
