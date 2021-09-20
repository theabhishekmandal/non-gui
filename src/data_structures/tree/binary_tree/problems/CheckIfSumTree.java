package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import utility.Pair;

import java.util.List;
import java.util.StringJoiner;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree check if it is sum tree or not.
 * Sum tree are those tree in which sum of left and right child are equal to parent node
 * A leaf node is also a sum tree
 *
 * Approach
 *  -   For base condition i.e if there is a leaf node, then it's left and right child are null, as nulls are equals
 *      so return true for equal sum
 *  -   If one of the node is null, then for that node it is equivalent of equal sum.
 *  -   Otherwise for every left and right node's sum, check if it's equal to it's parent's value or not,
 *          -   If they are equivalent then return true with sum of it's parent and left and right nodes.
 *          -   Else return false with the above sum
 *  -   returning sum is necessary so as to check for above node whether they have equal sum with their parent or not
 *
 */
public class CheckIfSumTree {
    public static void main(String[] args) {
        final List<int[]> list = List.of(
                new int[]{3, 1, 2},
                new int[]{10, 20, 30, 10, 10}
        );

        final var answer = new StringJoiner("\n");
        for (final var arr : list) {
            var binaryTree = new BinaryTree<Integer>();
            for (final var i : arr) {
                binaryTree.insertInBinaryTreeLevelOrder(i);
            }
            System.out.println(binaryTree.levelOrderPretty());
            answer.add(String.valueOf(checkIfSumTree(binaryTree)));
        }
        System.out.print(answer);
    }

    private static boolean checkIfSumTree(BinaryTree<Integer> binaryTree) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return false;
        }
        return isSumTree(binaryTree.getRoot()).getSecond();
    }

    private static Pair<Integer, Boolean> isSumTree(Node<Integer> curr) {
        if (curr == null) {
            return Pair.of(0, true);
        }
        if (isLeaf(curr)) {
            return Pair.of(curr.getData(), true);
        }
        final var leftPair = isSumTree(curr.getLeft());
        final var rightPair = isSumTree(curr.getRight());
        final var leftRightSum = leftPair.getFirst() + rightPair.getFirst();
        final var isRootSumTree = curr.getData().equals(leftRightSum);
        return Pair.of(curr.getData() + leftRightSum, isRootSumTree);
    }

    private static boolean isLeaf(Node<Integer> root) {
        return root.getLeft() == null && root.getRight() == null;
    }
}