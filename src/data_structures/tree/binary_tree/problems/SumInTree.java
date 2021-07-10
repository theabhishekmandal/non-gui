package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

/**
 * Find the sum of all nodes in the tree
 * Approach
 *  -   Use any type of traversal and add each node value.
 */
public class SumInTree {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        for (var i = 0; i < random.nextInt(20); i++) {
            binaryTree.insertInBinaryTreeLevelOrder(random.nextInt(20));
        }
        System.out.println(binaryTree.levelOrder());
        int sum = getSum(binaryTree.getRoot());
        System.out.println("sum of tree is " + sum);
    }

    private static int getSum(Node<Integer> node) {
        if (node == null) return 0;
        Deque<Node<Integer>> stack = new LinkedList<>();
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
