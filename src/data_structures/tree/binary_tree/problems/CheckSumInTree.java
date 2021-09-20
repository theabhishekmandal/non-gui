package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given an algorithm for checking the existence of path with given sum. That means, given a sum,
 * check whether there exists a path from root to any of the nodes.
 *
 * Approach,
 *  -   Iterative
 *      -   Use any traversal and while pushing the node in the stack or queue, just add (sum - current node value) along with the
 *          node value.
 *      -   if leaf node is reach check the added (sum - current node value) is 0, if it is zero then return true otherwise false
 *  -   Recursion
 *      -   In recursion call the root without subtracting the value and then while calling left or right subtree
 *          , then subtract the value
 *      -   when the left and right child are null then add the current node and then add it to the static list.
 *
 */

public class CheckSumInTree {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();

        // creating binaryTree and getting a number that's need to be found for sum Tree
        int number = getNumberToBeFound(random, binaryTree);
        System.out.println("Number to be found " + number + "\n" + binaryTree.levelOrderPretty());

        // Using Iteration
        checkSumUsingIteration(binaryTree, number);

        // Using Recursion
        checkSumUsingRecursion(binaryTree, number);
    }

    private static int getNumberToBeFound(Random random, BinaryTree<Integer> binaryTree) {
        List<Integer> list = new ArrayList<>();
        IntUnaryOperator op = x -> {
            list.add(x);
            return x;
        };
        IntStream.range(0, random.nextInt(20))
                .map(op)
                .forEach(binaryTree::insertInBinaryTreeLevelOrder);
        var number = random.nextInt(list.size());
        return list.get(number);
    }

    private static void checkSumUsingRecursion(BinaryTree<Integer> binaryTree, int number) {
        var joiner = new StringJoiner(", ", "[", "]");
        checkSumInTreeRecursion(binaryTree.getRoot(), number, "", joiner);
        System.out.println(joiner);
    }

    private static void checkSumUsingIteration(BinaryTree<Integer> binaryTree, int number) {
        var joiner = new StringJoiner(", ", "[", "]");
        checkSumInTree(binaryTree.getRoot(), number, joiner);
        System.out.println(joiner);
    }

    private static void checkSumInTree(Node<Integer> root, int sum, StringJoiner joiner) {
        if (root == null) {
            return;
        }

        Deque<Container<Integer>> stack = new ArrayDeque<>();
        // adding the node, sum - current node value and current node as string
        stack.push(Container.of(root, sum - root.getData(), "[ " + root.getData().toString()));

        while (!stack.isEmpty()) {
            var container = stack.pop();
            var curr = container.node;
            var left = curr.getLeft();
            var right = curr.getRight();

            if (left == null && right == null && container.sum == 0) {
                joiner.add(container.pathFromRoot + " ]");
            }

            pushToStack(stack, container, right);
            pushToStack(stack, container, left);
        }
    }

    private static void pushToStack(Deque<Container<Integer>> stack, Container<Integer> container, Node<Integer> node) {
        if (node != null) {
            var data = node.getData();
            stack.push(Container.of(node, container.sum - data, container.pathFromRoot + ", " + data));
        }
    }

    private static void checkSumInTreeRecursion(Node<Integer> node, int sum, String ans, StringJoiner paths) {
        if (node == null) {
            return;
        }

        var data = node.getData();
        if (node.getLeft() == null && node.getRight() == null && sum - data == 0) {
            paths.add(ans + ", " + node.getData() + " ]");
            return;
        }

        // checking for first time
        String temp = ans;
        temp += (ans.isEmpty()) ? "[" : ",";
        temp += " " + node.getData();

        checkSumInTreeRecursion(node.getLeft(), sum - node.getData(), temp, paths);
        checkSumInTreeRecursion(node.getRight(), sum - node.getData(), temp, paths);
    }

    static class Container<T> {
        private final Node<T> node;
        private final int sum;
        private final String pathFromRoot;

        public Container(Node<T> node, int sum, String pathFromRoot) {
            this.node = node;
            this.sum = sum;
            this.pathFromRoot = pathFromRoot;
        }

        public static <T> Container<T> of(Node<T> node, int sum, String distanceFromRoot) {
            return new Container<>(node, sum, distanceFromRoot);
        }
    }
}
