package data_structures.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of elements, search whether a subArray exists with 0 sum.
 * example:
 *  arr = [1,3,2,-1,5,4,-8,4,3,-7]
 *  here [4, 3, -7] is one of the subArray where sum is 0
 *
 *  Approach:
 *      -   To find a sub-array that has 0 zero sum, we will need the concept of prefix sum
 *      -   if a prefix sum already exists then there exists a subArray with 0 sum
 *          -   For example arr = [4, 1, -2, 1] prefix sum is [4, 5, 3, 4]
 *      -   if an element in the given array is zero, then that element is sub-array itself with zero sum
 *          -   For example arr = [1, 0, 1], index 1 is sub-array within itself
 *      -   if an element in the given array having prefix sum 0 then also sub-array exists
 *          -   For example arr = [1, -2, 1] prefix sum is [1, -1, 0], then prefix sum coming as 0, so sub-array exists
 */
public class FindSubArrayWithSum0 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, -1, 5, 4, -8, 4, 3, -7};
        System.out.println("Array = " + Arrays.toString(arr));
        System.out.println("does 0 sum subArray exists ? " + subArrayExists(arr));
    }

    private static boolean subArrayExists(int[] arr) {
        if(arr == null || arr.length == 0) return false;
        int sum = 0;
        Set<Integer> lookBack = new HashSet<>();
        for(int i : arr) {
            sum += i;
            if(lookBack.contains(sum) || i == 0 || sum == 0) {
                return true;
            }
            lookBack.add(sum);
        }
        return false;
    }
}
