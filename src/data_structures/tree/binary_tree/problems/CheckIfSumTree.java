package data_structures.tree.binary_tree.problems;

import data_structures.Pair;
import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree check if it is sum tree or not.
 * Sum tree are those tree in which sum of left and right child are equal to parent node
 * A leaf node is also a sum tree
 *
 * Approach
 *  -  For every node check if left and right child node sum is equal to current parent node
 *  -  if yes then send the sum of parent, left and right child node back to recursion tree with the flag value.
 */
public class CheckIfSumTree {
    public static void main(String[] args) {
        List<int[]> list = Arrays.asList(
                new int[]{3, 1, 2},
                new int[]{10, 20, 30, 10, 10}
        );

        StringBuilder answer = new StringBuilder();
        for (var arr : list) {
            BinaryTree<Integer> binaryTree = new BinaryTree<>();
            for (int i : arr) {
                binaryTree.insertInBinaryTreeLevelOrder(i);
            }
            answer.append(checkIfSumTree(binaryTree)).append("\n");
        }
        System.out.print(answer);
    }

    private static boolean checkIfSumTree(BinaryTree<Integer> binaryTree) {
        if (Optional.ofNullable(binaryTree).map(BinaryTree::getRoot).isEmpty()) {
            return false;
        }
        return isSumTree(binaryTree.getRoot()).getSecond();
    }

    private static Pair<Integer, Boolean> isSumTree(Node<Integer> root) {
        if (root == null) {
            return Pair.of(0, true);
        }
        if (isLeaf(root)) {
            return Pair.of(root.getData(), true);
        }
        var leftPair = isSumTree(root.getLeft());
        var rightPair = isSumTree(root.getRight());
        int leftValue = leftPair.getFirst();
        int rightValue = rightPair.getFirst();
        boolean isRootSumTree = root.getData().equals(leftValue + rightValue);
        return Pair.of(root.getData() + leftPair.getFirst() + rightPair.getFirst(), isRootSumTree);
    }

    private static boolean isLeaf(Node<Integer> root) {
        return root.getLeft() == null && root.getRight() == null;
    }
}