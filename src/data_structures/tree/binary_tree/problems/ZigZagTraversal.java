package data_structures.tree.binary_tree.problems;


import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree, print the elements in zig zag order.
 * Ex: if tree is 1
 *              2   3
 *            4  5  6  7
 *
 *  then zigzag traversal will be 1 3 2 4 5 6 7
 *  Approach:
 *      There are two approaches
 *          -   Using single queue but multiple traversal (better for space complexity)
 *          -   Using multiple queue but less traversal (better for time complexity)
 *  Using Single Queue
 *      -   In Single queue add the node to nodeList just like level order traversal
 *      -   But, while adding to the finalList we will check through the flag whether is set or not
 *      -   If it is set then we will reverse the elements of the list using a stack and then add to the finalList
 *
 *  Using Double Queue
 *      -   There will be two queues leftQueue and rightQueue
 *      -   Properties of insertion and removal in both queues are as follows:
 *              -   adding  -   In leftQueue addition will be done using addFirst and right child
 *                              is inserted before the left child. Also, the null value is added using addLast
 *                              method
 *              -   adding  -   In rightQueue addition will be done using addLast and left child
 *                              is inserted before the right child. Also, the null value is added using addFirst method
 *              -   removal -   In leftQueue removal operation will be in same direction as that of the add operation
 *                              i.e removeFirst
 *              -   removal -   In rightQueue removal operation will be in same direction as that of the add operation
 *                              i.e removeLast
 *      -   Using these properties it will be easier to remember.
 */
public class ZigZagTraversal {
    public static void main(String[] args) throws IOException {
        var tree = new BinaryTree<Integer>();
        IntStream.range(1, 10).forEach(tree::insertInBinaryTreeLevelOrder);
        System.out.println(tree.levelOrder());
        var starttime = System.currentTimeMillis();
        var zigZag = printInZigZagPattern(tree.getRoot());
        var stoptime = System.currentTimeMillis();
        var finalAnswer = "first string " + zigZag + " time taken is= " + (stoptime - starttime) + " milli seconds";

        starttime = System.currentTimeMillis();
        var zigZag2 = printInZigZagPatternNew(tree.getRoot());
        stoptime = System.currentTimeMillis();
        var finalAnswer2 = "first string " + zigZag2 + " time taken is= " + (stoptime - starttime) + " milli seconds";

        System.out.println(zigZag.equals(zigZag2));
        try (var br = new BufferedWriter(new FileWriter("hello.txt"))) {
            br.write(finalAnswer);
            br.write(finalAnswer2);
            br.flush();
        }
    }

    private static <T> String printInZigZagPatternNew(Node<T> root) {
        List<String> nodeList = new ArrayList<>();
        List<List<String>> finalList = new ArrayList<>();
        Deque<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        var leftToRight = true;
        while (!queue.isEmpty()) {
            Node<T> curr = queue.poll();
            if (curr != null) {
                nodeList.add(curr.getData().toString());
                if (curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
            } else {
                if (!leftToRight) {
                    Collections.reverse(nodeList);
                }
                finalList.add(nodeList);
                nodeList = new ArrayList<>();
                if (!queue.isEmpty()) {
                    leftToRight = !leftToRight;
                    queue.add(null);
                }
            }
        }
        return "[" + finalList.stream()
                .map(list -> "(" + String.join(", ", list) + ")")
                .collect(Collectors.joining(", \n")) + "]";
    }

    private static <T> String printInZigZagPattern(BinaryTree.Node<T> root) {
        List<String> nodeList = new ArrayList<>();
        List<List<String>> finalList = new ArrayList<>();

        Deque<Node<T>> leftQueue = new LinkedList<>();
        Deque<Node<T>> rightQueue = new LinkedList<>();
        leftQueue.addFirst(null);
        leftQueue.addFirst(root);

        while (true) {
            if (!leftQueue.isEmpty()) {
                while (!leftQueue.isEmpty()) {
                    Node<T> node = leftQueue.pollFirst();
                    if (node != null) {
                        nodeList.add(node.getData().toString());
                        addToQueueUsingFlag(rightQueue, node.getLeft(), node.getRight(), true);
                    } else {
                        finalList.add(nodeList);
                        nodeList = new ArrayList<>();
                        if (!rightQueue.isEmpty()) {
                            rightQueue.addFirst(null);
                        }
                    }
                }
            } else if (!rightQueue.isEmpty()) {
                while (!rightQueue.isEmpty()) {
                    Node<T> node = rightQueue.pollLast();
                    if (node != null) {
                        nodeList.add(node.getData().toString());
                        addToQueueUsingFlag(leftQueue, node.getLeft(), node.getRight(), false);
                    } else {
                        finalList.add(nodeList);
                        nodeList = new ArrayList<>();
                        if (!leftQueue.isEmpty()) {
                            leftQueue.addLast(null);
                        }
                    }
                }
            } else {
                break;
            }
        }
        return "[" + finalList.stream()
                .map(list -> "(" + String.join(", ", list) + ")")
                .collect(Collectors.joining(", \n")) + "]";
    }

    private static <T> void addToQueueUsingFlag(Deque<Node<T>> queue, Node<T> left, Node<T> right, boolean addLast) {
        if (addLast) {
            if (left != null) {
                queue.addLast(left);
            }
            if (right != null) {
                queue.addLast(right);
            }
        } else {
            if (right != null) {
                queue.addFirst(right);
            }
            if (left != null) {
                queue.addFirst(left);
            }
        }
    }
}
