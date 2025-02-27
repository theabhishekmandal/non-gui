package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Print the level order traversal in reverse order
 * Approach:
 *  -   Following the level order traversal, and using a deque for storing in reverse order
 *  -   For each level add the node to the front of the queue, this will ensure we add the nodes
 *      in reverse order
 *  -   now add that queue in the finalDeque Deque
 */
public class _41EasyReverseLevelOrder {
    public static void main(String[] args) {
        var binaryTree = new BinaryTree<Integer>();
        var random = new Random();
        random.ints(10, 0, 20).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        String answer = printReverseLevelOrder(binaryTree);
        System.out.println(binaryTree.levelOrder());
        System.out.println(answer);
    }

    private static <T> String printReverseLevelOrder(BinaryTree<T> binaryTree) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return "";
        }
        var nodeLevelDeque = new ArrayDeque<String>();
        var finalDeque = new ArrayDeque<ArrayDeque<String>>();
        var nullNode = Node.<T>of(null);
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.offer(binaryTree.getRoot());
        queue.offer(nullNode);
        while (!queue.isEmpty()) {
            Node<T> curr = queue.poll();
            if (curr != nullNode) {
                nodeLevelDeque.addFirst(curr.getData().toString());
                if (curr.getLeft() != null) {
                    queue.offer(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.offer(curr.getRight());
                }
            } else {
                var newList = new ArrayDeque<>(nodeLevelDeque);
                finalDeque.addFirst(newList);

                // clearing the list
                nodeLevelDeque = new ArrayDeque<>();

                if (!queue.isEmpty()) {
                    queue.offer(nullNode);
                }
            }
        }
        return "[" + finalDeque.stream()
                .map(list -> "(" + String.join(", ", list) + ")")
                .collect(Collectors.joining(", ")) + "]";
    }
}
