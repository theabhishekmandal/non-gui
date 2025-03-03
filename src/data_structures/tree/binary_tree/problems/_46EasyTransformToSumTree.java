package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 *  Given a binary Tree convert it to sum Tree
 *
 *  Input:
 *              10
 *           /      \
 *         -2        6
 *        /   \     /  \
 *      8     -4   7    5
 *
 * Output:
 *             20
 *           /    \
 *         4        12
 *        /  \     /  \
 *      0     0   0    0
 *
 * Explanation:
 *
 *            (4-2+12+6)
 *           /           \
 *       (8-4)          (7+5)
 *        /   \         /  \
 *       0     0       0    0
 *
 *  Approach:
 *      -   See below with easy approach
 */

public class _46EasyTransformToSumTree {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        random.ints(10, 0, 20).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrderPretty());

        transformToSumTree(binaryTree);
        System.out.println("\nAfter transforming sumTree\n" + binaryTree.levelOrderPretty());
    }

    private static void transformToSumTree(BinaryTree<Integer> tree) {
        if (tree == null || tree.getRoot() == null) {
            return;
        }
        transformNew(tree.getRoot());
    }

    private static int transformNew(Node<Integer> root) {
        if (root == null) {
            return 0;
        }
        int left = transformNew(root.getLeft());
        int right = transformNew(root.getRight());
        int data = root.getData();
        root.setData(left + right);
        return data + left + right;
    }
}
