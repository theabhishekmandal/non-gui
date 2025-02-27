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
 *  Approach Using Single Queue
 *      -   In Single queue addition of node will depend on leftToRight
 *          -   if leftToRight is true then add nodes at the end in nodeList
 *          -   otherwise add nodes in front
 *      -   After every level add nodeList to finalList and change the revert the flag of leftToRight
 */
public class _48EasyZigZagTraversal {
    public static void main(String[] args) throws IOException {
        var tree = new BinaryTree<Integer>();
        IntStream.range(1, 10).forEach(tree::insertInBinaryTreeLevelOrder);
        System.out.println(tree.levelOrder());


        List<Function<Node<Integer>, String>> listOfOperations = List.of(
                _48EasyZigZagTraversal::printInZigZagPatternNew
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
        Node<T> nullNode = Node.of(null);
        // to add nodes level wise
        Deque<String> levelNodeList = new ArrayDeque<>();

        // finalList for display
        List<Deque<String>> finalList = new ArrayList<>();

        // traversing tree
        Deque<Node<T>> queue = new ArrayDeque<>();
        queue.add(root);
        queue.add(nullNode);

        var leftToRight = true;
        while (!queue.isEmpty()) {
            Node<T> curr = queue.poll();
            if (curr != nullNode) {
                addToLeftOrRight(leftToRight, levelNodeList, curr);
                if (curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
            } else {
                finalList.add(levelNodeList);
                levelNodeList = new ArrayDeque<>();
                if (!queue.isEmpty()) {
                    leftToRight = !leftToRight;
                    queue.add(nullNode);
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

}
