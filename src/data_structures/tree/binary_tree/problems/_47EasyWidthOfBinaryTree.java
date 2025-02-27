package data_structures.tree.binary_tree.problems;


import utility.Pair;

import java.util.ArrayDeque;

/**
 * Given a binary tree, write a function to get the maximum width of the given tree.
 * The maximum width of a tree is the maximum width among all levels.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level,
 * where the null nodes between the end-nodes are also counted into the length calculation.
 *
 * Input:
 *
 *            1
 *          /   \
 *         3     2
 *        / \     \
 *       5   3     9
 *
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 *
 * Input:
 *
 *           1
 *          /
 *         3
 *        / \
 *       5   3
 *
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 *
 *
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /
 *       5
 *
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 *
 * Input:
 *
 *           1
 *          / \
 *         3   2
 *        /     \
 *       5       9
 *      /         \
 *     6           7
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 *
 *
 * Approach:
 *  -   To find the width of the binary tree, null nodes are also considered here.
 *  -   It is calculated as the max of difference between leftMost and rightMost non null node value.
 *      For eg - if leftNode position is 3 and right nodePosition is 4 then width = Math.max(width, right - left + 1)
 *  -   Here for each node it's position is calculated based on it's parent.
 *      - leftChild = 2 * i + 1
 *      - rightChild = 2 * i + 2
 *  -   Do level order traversal and for every level of nodes present in the deque, find the width by pulling the firstNode in
 *      the queue(which represent the leftMost node) and the lastNode in the queue(which represents the rightMost node in the queue)
 *  -   Now, take the difference between the position of leftMost and rightMost node.
 *  -   Do these above two steps, for each level and find the maxWidth of the tree.
 *
 */

public class _47EasyWidthOfBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {
        var root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(2);

        System.out.println(widthOfBinaryTreeNew(root));
    }

    private static int widthOfBinaryTreeNew(TreeNode root) {
        if (root == null) {
            return 0;
        }

        var width = 1;
        var deque = new ArrayDeque<Pair<TreeNode, Integer>>();
        var nullNode = Pair.<TreeNode, Integer>of(null, 0);
        deque.add(Pair.of(root, 0));
        deque.add(nullNode);

        while (!deque.isEmpty()) {
            var treeNodeIntegerPair = deque.poll();
            if (treeNodeIntegerPair != nullNode) {
                var currNode = treeNodeIntegerPair.getFirst();
                var value = treeNodeIntegerPair.getSecond();
                if (currNode.left != null) {
                    deque.add(Pair.of(currNode.left, 2 * value + 1));
                }
                if (currNode.right != null) {
                    deque.add(Pair.of(currNode.right, 2 * value + 2));
                }
            } else {
                if (!deque.isEmpty()) {
                    var right = deque.getLast().getSecond();
                    var left = deque.getFirst().getSecond();
                    width = Math.max(width, right - left + 1);
                    deque.add(nullNode);
                }
            }
        }
        return width;
    }
}
