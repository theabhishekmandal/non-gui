package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import utility.Counter;

import java.util.Random;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree, return the tilt of the whole tree.
 *
 * The tilt of a tree node is defined as the absolute difference between the sum of all left
 * subtree node values and the sum of all right subtree node values. Null node has tilt 0.
 *
 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
 *
 * Example:
 *
 * Input:
 *          1
 *        /   \
 *       2     3
 *     /  \   /
 *    4    5 6
 * Output: 1
 * Explanation:
 * Tilt of node 4 : 0
 * Tilt of node 5 : 0
 * Tilt of node 2 : |5 - 4| = 1
 * Tilt of node 6 : 0
 * Tilt of node 3 : |6 - 0| = 6
 * Tilt of node 1 : |(2 + 4 + 5) - (3 + 6)| = |11 - 9| = 2
 * Sum of every tilt is = 0 + 0 + 1 + 0 + 6 + 2 = 9
 *
 *
 * Approach:
 * -    To find the Tilt of every subTree, we have to follow the bottom up approach, i.e traversing and calculating value from
 *      bottom to top.
 * -    For every node, find the value of leftChild and rightChild, calculate the tilt of that node by |rightChildValue - leftChildValue|.
 *      And send back the value of node value, leftChildValue and rightChildValue to the recursion call.
 * -    We are sending back the value because, we want to calculate the tilt of each node's parent also, so we need the value
 *      of all the child nodes.
 */

public class _45EasyTiltTree {

    public static void main(String[] args) {
        var random = new Random();
        var testCase = 3;
        while (testCase-- > 0) {
            var tree = new BinaryTree<Integer>();
            random.ints(10, 0, 10).forEach(tree::insertInBinaryTreeLevelOrder);
            System.out.println(tree.levelOrder() + "\n the tilt of the tree is = " + getTilt(tree) + "\n");
        }
    }

    private static int getTilt(BinaryTree<Integer> binaryTree) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return 0;
        }
        var totalTiltCounter = new Counter();
        getTiltCount(binaryTree.getRoot(), totalTiltCounter);

        return totalTiltCounter.getCount();
    }

    private static int getTiltCount(Node<Integer> root, Counter totalTiltCounter) {
        if (root == null) {
            return 0;
        }
        var left = getTiltCount(root.getLeft(), totalTiltCounter);
        var right = getTiltCount(root.getRight(), totalTiltCounter);

        var value = Math.abs(right - left);
        totalTiltCounter.add(value);

        return root.getData() + left + right;
    }
}
