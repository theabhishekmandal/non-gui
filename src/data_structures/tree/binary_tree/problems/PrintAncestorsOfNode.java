package data_structures.tree.binary_tree.problems;

import utility.Pair;
import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Give an algorithm for printing all the ancestors of a node in a Binary tree.
 */
public class PrintAncestorsOfNode {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        var number = random.nextInt(20);
        var ancestorString = getAncestors(binaryTree.getRoot(), number);
        System.out.println(binaryTree.levelOrder() + "\n\nvalue of node for which ancestor is to be found= " + number);
        System.out.println(ancestorString);
    }

    private static <T> String getAncestors(Node<T> root, T valueToBeSearched) {
        if (root == null) {
            return "null";
        }
        Deque<Pair<Node<T>, String>> stack = new LinkedList<>();
        stack.push(new Pair<>(root, "[" + root.getData().toString()));

        while (!stack.isEmpty()) {
            var pair = stack.pop();
            var curr = pair.getFirst();
            var string = pair.getSecond();

            if (curr.getData().equals(valueToBeSearched)) {
                return string + "]";
            }

            if (curr.getRight() != null) {
                stack.push(new Pair<>(curr.getRight(), string + ", " + curr.getRight().getData().toString()));
            }
            if (curr.getLeft() != null) {
                stack.push(new Pair<>(curr.getLeft(), string + ", " + curr.getLeft().getData().toString()));
            }
        }
        return "null";
    }
}
