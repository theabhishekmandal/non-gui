package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Find the deepest node in the tree.
 * Approach:
 *      Traverse in level order and whichever is the last node will be the deepest node in the tree
 */
public class DeepestNodeInTree {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder());
        System.out.println(getDeepestNodeValue(binaryTree.getRoot()));
    }

    private static <T> T getDeepestNodeValue(Node<T> root) {
        if (root == null) {
            return null;
        }
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        Node<T> curr = null;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.getLeft() != null) {
                queue.add(curr.getLeft());
            }
            if (curr.getRight() != null) {
                queue.add(curr.getRight());
            }
        }
        return (curr != null) ? curr.getData() : null;
    }
}
