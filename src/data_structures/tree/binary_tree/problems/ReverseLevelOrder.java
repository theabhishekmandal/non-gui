package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Print the level order traversal in reverse order
 * Approach:
 *  -   Traverse level order and store the current node in the stack
 *  -   After traversal now print the list.
 */
public class ReverseLevelOrder {
    public static void main(String[] args) {
        var binaryTree = new BinaryTree<Integer>();
        var random = new Random();
        random.ints(10, 0, 20).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        String answer = printReverseLevelOrder(binaryTree.getRoot());
        System.out.println(binaryTree.levelOrder());
        System.out.println(answer);
    }

    private static <T> String printReverseLevelOrder(Node<T> node) {
        if (node == null) {
            return "";
        }
        var nodeLevelList = new ArrayDeque<String>();
        var finalList = new ArrayDeque<ArrayDeque<String>>();
        var nullNode = Node.<T>of(null);
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.offer(node);
        queue.offer(nullNode);
        while (!queue.isEmpty()) {
            Node<T> curr = queue.poll();
            if (curr != nullNode) {
                nodeLevelList.addFirst(curr.getData().toString());
                if (curr.getLeft() != null) {
                    queue.offer(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.offer(curr.getRight());
                }
            } else {
                var newList = new ArrayDeque<>(nodeLevelList);
                finalList.addFirst(newList);

                // clearing the list
                nodeLevelList = new ArrayDeque<>();

                if (!queue.isEmpty()) {
                    queue.offer(nullNode);
                }
            }
        }
        return "[" + finalList.stream()
                .map(list -> "(" + String.join(", ", list) + ")")
                .collect(Collectors.joining(", ")) + "]";
    }
}
