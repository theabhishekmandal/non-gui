package miscellaneous.leetcode.contests.weekly514;

/**
 * LeetCode 4015 — Weighted Sum of a Tree
 * Weekly Contest 514
 * https://leetcode.com/problems/weighted-sum-of-a-tree/description/
 *
 * You are given an integer array parent of length n representing a rooted tree
 * with nodes labeled from 0 to n - 1. The tree is rooted at node 0, so
 * parent[0] = -1. For each node i where 1 <= i <= n - 1, parent[i] denotes the
 * parent of node i.
 *
 * You are also given an integer array nums of length n, where nums[i] denotes
 * the value of node i.
 *
 * The depth of a node is the number of nodes on the path from the root to that
 * node, inclusive, with the root having depth 1.
 * The height of the tree h is the maximum depth among all nodes.
 *
 * The weight of a node i at depth d is nums[i] * (h - d + 1).
 * Return the sum of the weights of all nodes in the tree.
 *
 * Example 1:
 * Input: parent = [-1, 0, 0], nums = [1, 2, 3]
 * Tree:
 *       0 (1)
 *      / \
 *   1 (2) 2 (3)
 * depths = [1, 2, 2], h = 2
 * Output: 7
 * Explanation: 1*(2-1+1) + 2*(2-2+1) + 3*(2-2+1) = 2 + 2 + 3 = 7
 *
 * Example 2:
 * Input: parent = [-1, 0, 1], nums = [5, 4, 3]
 * Tree:
 *   0 (5)
 *   |
 *   1 (4)
 *   |
 *   2 (3)
 * depths = [1, 2, 3], h = 3
 * Output: 26
 * Explanation: 5*(3-1+1) + 4*(3-2+1) + 3*(3-3+1) = 15 + 8 + 3 = 26
 */
public class _2WeightedSumOfTree {
    public static void main(String[] args) {
        _2WeightedSumOfTree solver = new _2WeightedSumOfTree();

        int[] parent1 = {-1, 0, 0};
        int[] nums1 = {1, 2, 3};
        System.out.println(solver.weightedSum(parent1, nums1)); // expected: 7

        int[] parent2 = {-1, 0, 1};
        int[] nums2 = {5, 4, 3};
        System.out.println(solver.weightedSum(parent2, nums2)); // expected: 26
    }

    public long weightedSum(int[] parent, int[] nums) {

        int maxHeight = 0;
        int[] depth = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            int j = i;
            getHeight(j, parent, depth);
            maxHeight = Math.max(maxHeight, depth[i]);
        }
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += (long) nums[i] * (maxHeight - depth[i] + 1);
        }
        return sum;
    }

    private int getHeight(int i, int[] parent, int[] depth) {
        if (i == -1) {
            return 0;
        }

        if (depth[i] != 0) {
            return depth[i];
        }

        depth[i] = 1 + getHeight(parent[i], parent, depth);
        return depth[i];
    }
}
