package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import utility.Pair;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.StringJoiner;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Find all the leaf nodes in the tree
 * Approach
 *  -   To find a leaf node traverse in level order manner and count the nodes if the current node
 *      does not have the left and right child.
 */

public class FindLeafNodes {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        random.ints(10, 0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder());
        var output = numberOfLeafNodes(binaryTree.getRoot());
        System.out.println("total leaf nodes are " + output.getFirst());
        System.out.println("leaf nodes are " + output.getSecond());
    }

    private static <T> Pair<Integer, String> numberOfLeafNodes(Node<T> root) {
        if (root == null) {
            return Pair.of(0, "[]");
        }
        var joiner = new StringJoiner(",", "[", "]");
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(root);

        var leafNodes = 0;
        while (!queue.isEmpty()) {
            Node<T> curr = queue.poll();
            if (curr.getLeft() == null && curr.getRight() == null) {
                joiner.add(String.valueOf(curr.getData()));
                leafNodes++;
            }
            if (curr.getLeft() != null) {
                queue.add(curr.getLeft());
            }
            if (curr.getRight() != null) {
                queue.add(curr.getRight());
            }
        }
        return Pair.of(leafNodes, joiner.toString());
    }

}
