package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

import java.util.Optional;
import java.util.Random;

/**
 * Given a binary tree find the largest sum in the subTree, given that negative numbers are also present.
 */
public class LargestSubTreeSum {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        random.ints(10, -10, 10).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrderPretty());
        System.out.println("largest sum in subTree = " + findLargestSubTreeSum(binaryTree));
    }

    static class IntClass {
        int value;
        IntClass(int value) {
            this.value = value;
        }
    }
    private static int findLargestSubTreeSum(BinaryTree<Integer> binaryTree) {
        if (Optional.ofNullable(binaryTree).map(BinaryTree::getRoot).isEmpty()) {
            return -1;
        }
        IntClass temp = new IntClass(Integer.MIN_VALUE);
        return find(binaryTree.getRoot(), temp);
    }

    private static int find(Node<Integer> root, IntClass temp) {
        if (root == null) {
            return 0;
        }
        int currentSum = root.getData() + find(root.getLeft(), temp) + find(root.getRight(), temp);
        temp.value = Math.max(temp.value, currentSum);
        return currentSum;
    }
}
