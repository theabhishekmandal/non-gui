package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Find the depth of the binary tree.
 * Depth of binary tree is given by, the deepest node from root to that node.
 *
 * Approach:
 *  -   Using post order and level order traversal we can find the depth of tree.
 *  -   Recursion solution is simple
 *      -   Use recursion in post order manner and find the max between left child and right child
 *          and increase the value by 1
 *
 *  -   In iterative post order traversal, increase the counter when left child or right child is added,
 *      otherwise take the max between current value and max_value and decrease the counter by 1
 *
 *  -   In iterative level order traversal, increase counter at every level
 */
public class DepthOfTree {
    public static void main(String[] args) {
        var binaryTree = new BinaryTree<Integer>();
        var random = new Random();
        for (var i = 0; i < random.nextInt(20); i++) {
            binaryTree.insertInBinaryTreeLevelOrder(random.nextInt(100));
        }
        System.out.println(binaryTree.levelOrder());
        var depthOfTreeRecursion = depthOfTreeRecursion(binaryTree.getRoot());
        var depthOfTreeIteration = depthOfTreeIteration(binaryTree.getRoot());
        var depthOfTreeLevelOrder = depthOfTreeLevelOrder(binaryTree.getRoot());
        System.out.println("depth using recursion " + depthOfTreeRecursion +
                "\ndepth using iteration " + depthOfTreeIteration +
                "\ndepth using levelOrder " + depthOfTreeLevelOrder);
    }

    private static <T> int depthOfTreeRecursion(Node<T> node) {
        if (node == null) {
            return 0;
        }
        var leftDepth = depthOfTreeRecursion(node.getLeft());
        var rightDepth = depthOfTreeRecursion(node.getRight());
        return Math.max(leftDepth, rightDepth) + 1;
    }

    private static <T> int depthOfTreeIteration(Node<T> node) {
        if (node == null) {
            return 0;
        }


        var depthCounter = 0;

        Deque<Node<T>> stack = new LinkedList<>();
        stack.push(node);
        stack.push(node);

        var depth = 0;
        while (!stack.isEmpty()) {
            Node<T> curr = stack.pop();
            if (!stack.isEmpty() && stack.peek() == curr) {
                if (curr.getRight() != null) {
                    stack.push(curr.getRight());
                    stack.push(curr.getRight());
                }
                if (curr.getLeft() != null) {
                    stack.push(curr.getLeft());
                    stack.push(curr.getLeft());
                }
                depthCounter++;
            } else {
                depth = Math.max(depth, depthCounter);
                depthCounter--;
            }
        }
        return depth;
    }

    private static <T> int depthOfTreeLevelOrder(Node<T> node) {
        if (node == null) {
            return 0;
        }


        var counter = 0;
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(node);
        queue.add(null);

        while (!queue.isEmpty()) {
            Node<T> curr = queue.poll();
            if (curr != null) {
                if (curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
            } else {
                counter++;
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }
        }
        return counter;
    }
}
