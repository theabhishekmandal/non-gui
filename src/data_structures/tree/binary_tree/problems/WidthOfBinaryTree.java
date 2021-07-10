package data_structures.tree.binary_tree.problems;


import java.util.ArrayDeque;
import java.util.Deque;

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
 *  -   In this width of the binary tree, depends on distance between the leftmost child and the rightmost child
 *  -   In this given binary tree, we can have null values also, between two non null nodes, see last example
 *  -   Also, those null values are considered in calculating width which are between two non null nodes. If
 *      there is null node after a nonnull node, and there is no non null node after the null node then that null
 *      node is not considered. Example see second last example
 *  -   To calculate width we can use the property that every node's
 *      -   left child is calculated using 2 * n + 1
 *      -   right child is calculate using 2 * n + 2
 *      -   This is possible as we are considering the tree as full binary tree
 *  -   In this mainly two queues are used.
 *      -   One queue for traversing the nodes in the level order manner
 *      -   Second queue to insert the index value generated from it's parent index. This index will
 *          be later used to calculate the width
 *  -   Now while traversing the given level of the tree using the queue, the first element of queue
 *      will give the leftmost child and the last element of the queue will give the rightmost child. Eg:
 *      nodeQueue = [5, null, null, 9], here 5 is the leftmost child and 9 is the rightmost child.
 *      indexQueue = [3, 6], index queue is calculated using last example.
 *  -   We got the leftmost index and rightmost index i.e 3 and 6 respectively, so width at this level is 6 - 3 + 1 = 4
 *
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class WidthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
//        root.left.right = new TreeNode(3);
//        root.right.right = new TreeNode(2);
//        System.out.println(widthOfBinaryTree2(root));
        System.out.println(widthOfBinaryTree3(root));
    }

    private static int widthOfBinaryTree3(TreeNode root) {
        if (root == null) {
            return 0;
        }

        var width = 1;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        Deque<Integer> intDeque = new ArrayDeque<>();
        intDeque.add(0);

        while (!queue.isEmpty()) {
            var length = queue.size();
            var left = 0;
            var right = 0;
            for (var i = 0; i < length; i++) {
                int index = (intDeque.isEmpty()) ? 0 : intDeque.poll();

                // calculate left and right index
                if (i == 0) {
                    left = index;
                } else if (i == length - 1) {
                    right = index;
                }

                TreeNode curr = queue.poll();
                if (curr != null) {
                    if (curr.left != null) {
                        queue.add(curr.left);
                        intDeque.add(2 * index + 1);
                    }
                    if (curr.right != null) {
                        queue.add(curr.right);
                        intDeque.add(2 * index + 2);
                    }
                }
            }
            if (length != 1) {
                width = Math.max(width, right - left + 1);
            }
        }
        return width;
    }
}
