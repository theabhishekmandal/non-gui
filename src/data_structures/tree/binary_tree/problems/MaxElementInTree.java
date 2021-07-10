package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Find the maximum value node in the tree.
 * Approach:
 *      -   Find the max between left child, right child and the parent node
 *      -   Using post order traversal is the best option
 */
public class MaxElementInTree {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        for (var i = 0; i < 10; i++) {
            binaryTree.insertInBinaryTreeLevelOrder(random.nextInt(100));
        }

        System.out.println(binaryTree.levelOrder());
        int value = findMaxRecursive(binaryTree.getRoot());
        int value2 = findMaxIter(binaryTree.getRoot());
        System.out.println(value == value2);
        System.out.println(value);
        System.out.println(value2);
    }

    /*
        Approach
        Use post order traversal, and then check which is greater among the three,
        left child, right child or the parent, then propagate the value.
     */
    private static Integer findMaxRecursive(Node<Integer> node) {
        var maxValue = Integer.MIN_VALUE;
        if (node != null) {
            int leftValue = findMaxRecursive(node.getLeft());
            int rightValue = findMaxRecursive(node.getRight());
            maxValue = Math.max(node.getData(), Math.max(leftValue, rightValue));
        }
        return maxValue;
    }

    private static Integer findMaxIter(Node<Integer> node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        Deque<Node<Integer>> stack = new LinkedList<>();
        stack.push(node);
        stack.push(node);
        int maxValue = Integer.MIN_VALUE;
        while (!stack.isEmpty()) {
            Node<Integer> currValue = stack.pop();
            if (!stack.isEmpty() && currValue == stack.peek()) {
                if (currValue.getRight() != null) {
                    stack.push(currValue.getRight());
                    stack.push(currValue.getRight());
                }
                if (currValue.getLeft() != null) {
                    stack.push(currValue.getLeft());
                    stack.push(currValue.getLeft());
                }
            } else {
                maxValue = Math.max(maxValue, currValue.getData());
            }
        }
        return maxValue;
    }
}
