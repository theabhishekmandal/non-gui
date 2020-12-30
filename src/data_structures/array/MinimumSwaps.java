package data_structures.array;

/**
 * Given an array of integers and a value k. Find the minimum number of swaps so that elements less than k are together
 * ex arr = [4, 3, 6, 2, 1] k = 4, replace 6 with 1 so that arr = [4, 3, 1, 2, 6]
 *
 * Approach:
 *  -   To group elements which are smaller than or equal to k
 *  -   First calculate how many elements are there which are <=k
 *  -   Suppose the value is x, now to group x good elements together , we have to consider
 *      each sub-array of length x and calculate bad elements(> k).
 *  -   By calculating minimum number of bad elements, we can swap these elements to group good elements.
 *  -   Calculation of bad elements is done by sliding window and two pointer
 *      -   i resides at 0 and j will start from count(count of good elements)
 *      -   when arr[i] > k then we subtract bad element, this denotes this element is leaving the window
 *      -   when arr[j] > k then we add bad element, this denotes this is entering the window
 *      -   then we take the minimum of answer and bad elements to calculate final answer.
 *
 */

public class MinimumSwaps {
    public static void main(String[] args) {
        int[] arr = {263, 349, 318, 277, 282, 301, 250, 104, 164, 278, 442, 400, 130, 271, 305, 52, 472, 346, 24, 185};
        int k = 212;
        System.out.println(getMinimumSwaps2(arr, k));
    }

    private static int getMinimumSwaps2(int[] arr, int k) {
        int count = 0;
        for (int value : arr) {
            if (value <= k) {
                ++count;
            }
        }

        int bad = 0;
        for (int i = 0; i < count; ++i) {
            if (arr[i] > k) {
                ++bad;
            }
        }

        int ans = bad;
        for (int i = 0, j = count; j < arr.length; ++i, ++j) {

            if (arr[i] > k) {
                --bad;
            }

            if (arr[j] > k) {
                ++bad;
            }

            ans = Math.min(ans, bad);
        }
        return ans;
    }
}
