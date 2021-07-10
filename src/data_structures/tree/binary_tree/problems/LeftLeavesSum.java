package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree, find the sum of the left leave nodes
 */

public class LeftLeavesSum {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        var size = 1 + random.nextInt(10);
        for (int i = 0; i < size; i++) binaryTree.insertInBinaryTreeLevelOrder(i + 1);
        System.out.println("binary Tree\n" + binaryTree.levelOrderPretty() + "\nsum of left leaves are =" +
                getLeftLeavesSum(binaryTree.getRoot()));
    }

    private static int getLeftLeavesSum(Node<Integer> root) {
        if (root == null) {
            return 0;
        }
        Deque<Node<Integer>> stack = new LinkedList<>();
        stack.push(root);
        var ans = 0;
        while (!stack.isEmpty()) {
            Node<Integer> curr = stack.pop();
            if (curr.getLeft() != null) {
                Node<Integer> left = curr.getLeft();
                if (left.getLeft() == null && left.getRight() == null) {
                    ans += left.getData();
                }
                stack.push(left);
            }
            if (curr.getRight() != null) {
                stack.push(curr.getRight());
            }
        }
        return ans;
    }
}
