package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a String of numbers with brackets convert them to binary tree
 * Approach:
 *  -   when the ) is encountered then pop the node from stack and then check if it's left is null or right is null
 *  -   then place the node at the appropriate left or right
 *  -   pop the last node and make it as root node
 */

public class ConstructBinaryTreeFromString {
    public static void main(String[] args) {
        Stream.of("4(2(3)(1))(6(5))", "1(2)(3)")
                .map(ConstructBinaryTreeFromString::getBinaryTreeFromString)
                .map(BinaryTree::levelOrderPretty)
                .forEach(System.out::println);
    }

    private static BinaryTree<Integer> getBinaryTreeFromString(String input) {
        var binaryTree = new BinaryTree<Integer>();
        if (input == null || input.trim().isEmpty()) {
            return binaryTree;
        }
        Deque<Node<Integer>> nodeStack = new ArrayDeque<>();

        var ind = 0;
        int length = input.length();
        while (ind < length) {
            var c = input.charAt(ind);
            if (Character.isDigit(c)) {
                ind = convertAndPush(input, ind, nodeStack);
                continue;
            } else if (c == ')') {
                var leftOrRightNode = nodeStack.pop();
                var peekNode = nodeStack.peek();
                if (peekNode != null) {
                    if (peekNode.getLeft() == null) {
                        peekNode.setLeft(leftOrRightNode);
                    } else {
                        peekNode.setRight(leftOrRightNode);
                    }
                }
            } else {
                // do nothing in case of (
            }
            ind++;
        }
        if (!nodeStack.isEmpty()) {
            binaryTree.setRoot(nodeStack.pop());
        }
        return binaryTree;
    }

    private static int convertAndPush(String input, int ind, Deque<Node<Integer>> nodeStack) {
        var length = input.length();
        var temp = 0;
        while (ind < length && Character.isDigit(input.charAt(ind))) {
            temp = (temp * 10) + input.charAt(ind) - '0';
            ind++;
        }
        nodeStack.push(Node.of(temp));
        return ind;
    }
}
