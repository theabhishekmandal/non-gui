package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Find the size of the tree.
 * Size of tree is given by total number of nodes present in the tree.
 * Approach:
 *  -   Use post order traversal to find the total number of nodes in tree
 */

public class FindingSizeOfTree {
    public static void main(String[] args) {
        var binaryTree = new BinaryTree<Integer>();
        var random = new Random();
        random.ints(20, 0, 100).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        var size = findSizeOfTreeRecursive(binaryTree.getRoot());
        var size2 = findSizeOfTreeIteration(binaryTree.getRoot());
        System.out.println(binaryTree.levelOrder());
        System.out.println(size);
        System.out.println(size2);
    }

    private static <T> int findSizeOfTreeRecursive(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return findSizeOfTreeRecursive(node.getLeft()) + findSizeOfTreeRecursive(node.getRight()) + 1;
    }

    private static <T> int findSizeOfTreeIteration(Node<T> node) {
        if (node == null) {
            return 0;
        }

        var size = 0;
        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(node);
        stack.push(node);
        while (!stack.isEmpty()) {
            Node<T> curr = stack.pop();
            if (!stack.isEmpty() && stack.peek() == curr) {
                if (curr.getRight() != null) {
                    stack.push(curr.getRight());
                    stack.push(curr.getRight());
                }
                if (curr.getLeft() != null) {
                    stack.push(curr.getLeft());
                    stack.push(curr.getLeft());
                }
            } else {
                size++;
            }
        }
        return size;
    }
}
