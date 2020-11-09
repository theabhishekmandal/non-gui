package data_structures.array;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 *
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 *
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * Example 4:
 *
 * Input: nums = [1]
 * Output: [1]
 *
 * Approach:
 *  -   To find the next permutation of a given number there are two scenarios that we have to handle
 *      -   if the elements are in decreasing order, then their next permutation is not possible
 *          so, in that case just reverse the decreasing order to get increasing order
 *  -   Now, for the other scenario
 *      -   first start from the last element and check if there exists a number such that arr[i] > arr[i - 1],
 *          element at i - 1, is needed to be replaced with its immediate increased value that can be found
 *      -   the next immediate increased value can be found in range [i to arr.length - 1]
 *      -   replace i - 1 element with immediate increase value
 *      -   In the end we need to rearrange the elements from i to arr.length, as they are not in correct permutation
 *          either you can sort that or just reverse the elements from i to arr.length
 */
public class NextPermutation {
    public static void main(String[] args) {
        nextPermutation(new int[]{1, 3, 2});
        nextPermutation(new int[]{1, 2, 3});
        nextPermutation(new int[]{2, 1, 3});
        nextPermutation(new int[]{2, 3, 1});
        nextPermutation(new int[]{3, 1, 2});
        nextPermutation(new int[]{3, 2, 1});
    }

    private static void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        for(; i > 0; i--) {
           if(nums[i] > nums[i - 1]) {
               break;
           }
        }
        if(i == 0) {
           for(int k = 0, l = nums.length - 1; k < l; k++, l--) {
               swap(nums, k, l);
           }
        }
        else {
            int index = 0;
            for(int k = i; k < nums.length; k++) {
                if(nums[k] > nums[i - 1]) {
                    index = k;
                }
            }
            swap(nums, index, i - 1);
            for(int k = i, l = nums.length - 1; k < l; k++, l--) {
                swap(nums, k, l);
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
