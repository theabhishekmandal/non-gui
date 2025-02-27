package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Find the sum of all nodes in the tree
 * Approach
 *  -   Use top down/bottom up approach to find the sum of nodes
 */
public class _43EasySumInTree {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        random.ints(10, 0, 10).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder());
        int sum = getSum(binaryTree.getRoot());
        System.out.println("sum of tree is " + sum);
    }

    private static int getSum(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        Deque<Node<Integer>> stack = new ArrayDeque<>();
        var sum = 0;
        Node<Integer> curr = node;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            } else {
                curr = stack.pop();
                sum += curr.getData();
                curr = curr.getRight();
            }
        }
        return sum;
    }
}
