package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 *
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * Note: The length of path between two nodes is represented by the number of edges between them.
 *
 * Approach:
 *  -   Using post order traversal, at any given time, we will have left child, right child and parent.
 *  -   Now, there can be n number of left, right and parent, so take the maximum value out of these in a global
 *      variable and then return the result - 1(because we want to calculate edges not nodes)
 *  -   Note that, initial value of the global variable should start with 1 because, if tree has only 1 node
 *      then number of edges will be 0. Also, this condition is also satisfied by empty root value.
 * */

public class DiameterOfTree {
    private static int value;

    public static void main(String[] args) {
        var binaryTree = new BinaryTree<Integer>();
        var random = new Random();
        random.ints(20, 0, 100).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        try {
            System.out.println(binaryTree.levelOrder());
            var diameterOfTreeRecursion = findDiameterOfTreeRecursion(binaryTree.getRoot());
            var diameterOfTreeIteration = findDiameterOfTreeIteration(binaryTree.getRoot());

            System.out.println(diameterOfTreeRecursion);
            System.out.println(diameterOfTreeIteration);
        } catch (Exception e) {
            System.err.println("In exception");
            System.out.println(binaryTree.levelOrder());
            e.printStackTrace();
        }
    }

    private static int findDiameterOfTreeRecursion(Node<Integer> root) {
        value = 1;
        find(root);
        return value - 1;
    }

    private static <T> int find(Node<T> node) {
        if (node == null) {
            return 0;
        }
        var left = find(node.getLeft());
        var right = find(node.getRight());

        // adding the deepest left and right node from each current node
        value = Math.max(value, left + right + 1);

        // to find the deepest value take the max between left and right
        return Math.max(left, right) + 1;
    }

    private static <T> int findDiameterOfTreeIteration(Node<T> root) {
        if (root == null || (root.getLeft() == null && root.getRight() == null)) {
            return 0;
        }
        var maxValue = 0;
        Map<Node<T>, Integer> map = new HashMap<>();
        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(root);
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> curr = stack.pop();
            if (!stack.isEmpty() && curr == stack.peek()) {
                if (curr.getRight() != null) {
                    stack.push(curr.getRight());
                    stack.push(curr.getRight());
                }
                if (curr.getLeft() != null) {
                    stack.push(curr.getLeft());
                    stack.push(curr.getLeft());
                }
            } else {
                var left = map.getOrDefault(curr.getLeft(), 0);
                var right = map.getOrDefault(curr.getRight(), 0);

                // adding the deepest left and right node from each current node
                maxValue = Math.max(maxValue, left + right + 1);

                // to find the deepest value take the max between left and right
                map.put(curr, Math.max(left, right) + 1);
            }
        }
        return maxValue - 1;
    }
}
