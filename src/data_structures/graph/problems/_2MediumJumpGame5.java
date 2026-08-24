package data_structures.graph.problems;

import java.util.Arrays;

/*
This is leetcode question.
https://leetcode.com/problems/jump-game-v/description/?envType=daily-question&envId=2026-05-24

1340. Jump Game V

Given an array of integers arr and an integer d, in one jump you can go from index i to index j if:
    - |i - j| <= d
    - arr[i] > arr[j] (you can only jump to a lower index with a smaller value)

Find the maximum number of jumps you can make starting from any index.

Explanation:
    Treat each index as a node. From index i, you may jump left or right to any index j within distance d
    whose value is strictly less than arr[i]. The answer is the longest jump sequence over all starting indices.

    This problem helps to understand how we can use DFS on an array (not on an explicit graph) plus dynamic
    programming / memoization: each index's best jump count depends on reachable neighbors.

Example 1:
    Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
    Output: 4
    Explanation:
        One of the longest jump sequences is 4 -> 6 -> 7 -> 9 -> 10 (indices 1 -> 4 -> 6 -> 7 -> 8).

Example 2:
    Input: arr = [3,3,3,3,3], d = 3
    Output: 1
    Explanation:
        No index can jump to another because all values are equal.

Example 3:
    Input: arr = [7,6,5,4,3,2,1], d = 1
    Output: 7
    Explanation:
        Starting at index 0, jump to 1, then 2, then 3, then 4, then 5, then 6.

Constraints:
    - 1 <= arr.length <= 1000
    - 1 <= arr[i] <= 10^5
    - 1 <= d <= arr.length
 */
public class _2MediumJumpGame5 {

    public int maxJumps(int[] arr, int d) {
        int[] maxJumpCount = new int[arr.length];
        Arrays.fill(maxJumpCount, -1);
        for (int i = 0; i < arr.length; i++) {
            dfs(i, arr, maxJumpCount, d);
        }
        int ans = Integer.MIN_VALUE;
        for (int i : maxJumpCount) {
            ans = Math.max(ans, i);
        }
        return ans;
    }

    private void dfs(int i, int[] arr, int[] maxJumpCount, int distance) {
        if (maxJumpCount[i] != -1) {
            return;
        }
        maxJumpCount[i] = 1;
        for (int j = i + 1; j < arr.length && j <= i + distance; j++) {
            if (arr[j] < arr[i]) {
                dfs(j, arr, maxJumpCount, distance);
                maxJumpCount[i] = Math.max(maxJumpCount[i], maxJumpCount[j] + 1);
            }
        }

        for (int j = i - 1; j >= 0  && j >= i - distance; j--) {
            if (arr[j] < arr[i]) {
                dfs(j, arr, maxJumpCount, distance);
                maxJumpCount[i] = Math.max(maxJumpCount[i], maxJumpCount[j] + 1);
            }
        }
    }
    public static void main(String[] args) {
        _2MediumJumpGame5 solution = new _2MediumJumpGame5();

        int[] arr1 = {6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12};
        int d1 = 2;
        System.out.println("arr = " + Arrays.toString(arr1) + ", d = " + d1
                + " -> max jumps = " + solution.maxJumps(arr1, d1)); // 4

        int[] arr2 = {3, 3, 3, 3, 3};
        int d2 = 3;
        System.out.println("arr = " + Arrays.toString(arr2) + ", d = " + d2
                + " -> max jumps = " + solution.maxJumps(arr2, d2)); // 1

        int[] arr3 = {7, 6, 5, 4, 3, 2, 1};
        int d3 = 1;
        System.out.println("arr = " + Arrays.toString(arr3) + ", d = " + d3
                + " -> max jumps = " + solution.maxJumps(arr3, d3)); // 7
    }
}
