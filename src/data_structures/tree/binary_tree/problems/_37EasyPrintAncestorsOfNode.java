package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import utility.Pair;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Give an algorithm for printing all the ancestors of a node in a Binary tree.
 * Approach
 * -    Do DFS traversal and for each node, pass it as a string to the stack.
 * -    Later if the node to be found matches then return the full string.
 */
public class _37EasyPrintAncestorsOfNode {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        random.ints(20, 0, 20).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        var number = random.nextInt(20);
        var ancestorString = getAncestors(binaryTree.getRoot(), number);
        System.out.println(binaryTree.levelOrder() + "\n\nvalue of node for which ancestor is to be searched = " + number);
        System.out.println(ancestorString);
    }

    private static <T> String getAncestors(Node<T> root, T valueToBeSearched) {
        var open = "[";
        var close = "]";
        var empty = "[]";
        if (root == null) {
            return empty;
        }

        var ans = "";
        Deque<Pair<Node<T>, String>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, root.getData().toString()));

        while (!stack.isEmpty()) {
            var pair = stack.pop();
            var curr = pair.getFirst();
            var string = pair.getSecond();

            if (curr.getData().equals(valueToBeSearched)) {
                ans =  string;
                break;
            }

            if (curr.getRight() != null) {
                stack.push(new Pair<>(curr.getRight(), string + ", " + curr.getRight().getData().toString()));
            }
            if (curr.getLeft() != null) {
                stack.push(new Pair<>(curr.getLeft(), string + ", " + curr.getLeft().getData().toString()));
            }
        }
        return open + ans + close;
    }
}
