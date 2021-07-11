package data_structures.tree.binary_tree.problems;


import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import utility.StopWatch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
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
 *      -   If it is set then we will add elements at the end, otherwise add elements at the front
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


        List<Function<Node<Integer>, String>> listOfOperations = List.of(
                ZigZagTraversal::printInZigZagPattern,
                ZigZagTraversal::printInZigZagPatternNew
        );

        var joiner = new StringJoiner("\n");
        for (var func : listOfOperations) {
            var stopWatch = new StopWatch();

            stopWatch.startTime();
            var zigzag = func.apply(tree.getRoot());
            stopWatch.stopTime();

            var finalAnswer = "first string " + zigzag + "\ntime taken is= " + stopWatch.getTimeInMillis() + " milli seconds\n";
            joiner.add(finalAnswer);
        }
        System.out.println(joiner);

        try (var br = new BufferedWriter(new FileWriter("hello.txt"))) {
            br.write(joiner.toString());
        }
    }

    private static <T> String printInZigZagPatternNew(Node<T> root) {
        Deque<String> nodeList = new ArrayDeque<>();
        List<Deque<String>> finalList = new ArrayList<>();
        Deque<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        var leftToRight = true;
        while (!queue.isEmpty()) {
            Node<T> curr = queue.poll();
            if (curr != null) {
                addToLeftOrRight(leftToRight, nodeList, curr);
                if (curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
            } else {
                finalList.add(nodeList);
                nodeList = new ArrayDeque<>();
                if (!queue.isEmpty()) {
                    leftToRight = !leftToRight;
                    queue.add(null);
                }
            }
        }
        return getListToString(finalList);
    }

    private static <T> void addToLeftOrRight(boolean leftToRight, Deque<String> nodeList, Node<T> curr) {
        if (leftToRight) {
            nodeList.addLast(curr.getData().toString());
        } else {
            nodeList.addFirst(curr.getData().toString());
        }
    }

    public static String getListToString(List<? extends Collection<String>> finalList) {
        Function<Collection<String>, String> func = x -> "(" + String.join(", ", x) + ")";
        return "[" + finalList.stream()
                .map(func)
                .collect(Collectors.joining(", \n"))
                + "]";
    }

    private static <T> String printInZigZagPattern(Node<T> root) {
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
                        addToQueueUsingFlag(rightQueue, node, true);
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
                        addToQueueUsingFlag(leftQueue, node, false);
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
        return getListToString(finalList);
    }

    private static <T> void addToQueueUsingFlag(Deque<Node<T>> queue, Node<T> curr, boolean addLast) {
        var left = curr.getLeft();
        var right = curr.getRight();
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
