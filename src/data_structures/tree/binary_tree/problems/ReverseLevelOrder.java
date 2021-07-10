package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

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
        for (var i = 0; i < random.nextInt(20); i++) {
            binaryTree.insertInBinaryTreeLevelOrder(random.nextInt(100));
        }
        String answer = printReverseLevelOrder(binaryTree.getRoot());
        System.out.println(binaryTree.levelOrder());
        System.out.println(answer);
    }

    private static <T> String printReverseLevelOrder(Node<T> node) {
        if (node == null) {
            return "";
        }
        var nodeLevelList = new LinkedList<String>();
        var finalList = new LinkedList<LinkedList<String>>();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(node);
        queue.offer(null);
        while (!queue.isEmpty()) {
            Node<T> curr = queue.poll();
            if (curr != null) {
                nodeLevelList.addFirst(curr.getData().toString());
                if (curr.getLeft() != null) {
                    queue.offer(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.offer(curr.getRight());
                }
            } else {
                var newList = new LinkedList<String>(nodeLevelList);
                finalList.addFirst(newList);

                // clearing the list
                nodeLevelList.clear();

                if (!queue.isEmpty()) {
                    queue.offer(null);
                }
            }
        }
        return "[" + finalList.stream()
                .map(list -> "(" + String.join(", ", list) + ")")
                .collect(Collectors.joining(", ")) + "]";
    }
}
