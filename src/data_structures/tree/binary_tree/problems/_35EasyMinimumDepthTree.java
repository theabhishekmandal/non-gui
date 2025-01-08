package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import utility.StopWatch;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Random;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree, find its minimum depth
 * The minimum depth is the number of nodes along the shortest path from root node down to the nearest leaf node
 *
 * Approach:
 *  Using PostOrder Approach:
 *      Using postOrder traversal, increase the counter for every node found.
 *      Now when leaf node is reached, start decreasing the counter. Also, before decreasing the counter
 *      check if the current node is the leaf node or not
 *          - if it is leaf node then take the minimum otherwise do nothing
 *
 * Using LevelOrder Approach:
 *      Traverse each level, increase the counter and check whether the current node is leaf node, if it is leaf node
 *      then return the counter
 */

public class _35EasyMinimumDepthTree {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        random.ints(20, 0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder());

        var stopWatch = new StopWatch();

        // this is little faster
        stopWatch.startTime();
        var minimumDepthLevelOrder = minimumDepthLevelOrder((binaryTree.getRoot()));
        stopWatch.stopTime();
        System.out.println("Minimum time taken by levelOrder method " + stopWatch.getTimeInMillis() + " milliseconds min depth = " + minimumDepthLevelOrder);

        stopWatch.reset();
        stopWatch.startTime();
        // this is slower than previous one
        var minimumDepthPostOrder = minimumDepth(binaryTree.getRoot());
        stopWatch.stopTime();
        System.out.println("Minimum time taken by postOrder method " + stopWatch.getTimeInMillis() + " milliseconds min depth = " + minimumDepthPostOrder);
    }

    private static <T> int minimumDepth(Node<T> node) {
        if (node == null) {
            return 0;
        }

        var minDepth = Integer.MAX_VALUE;
        var depthCounter = 0;

        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(node);
        stack.push(node);

        while (!stack.isEmpty()) {
            Node<T> curr = stack.pop();
            if (!stack.isEmpty() && curr == stack.peek()) {
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
                if (curr.getLeft() == null && curr.getRight() == null) {
                    minDepth = Math.min(minDepth, depthCounter);
                }
                depthCounter--;
            }
        }
        return minDepth;
    }

    private static <T> int minimumDepthLevelOrder(Node<T> node) {
        if (node == null) {
            return 0;
        }

        var minDepth = 1;

        var nullNode = Node.<T>of(null);
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(node);
        queue.add(nullNode);

        while (!queue.isEmpty()) {
            Node<T> curr = queue.poll();
            if (curr != nullNode) {
                Node<T> left = curr.getLeft();
                Node<T> right = curr.getRight();
                if (right == null && left == null) {
                    return minDepth;
                }
                if (left != null) {
                    queue.add(left);
                }
                if (right != null) {
                    queue.add(right);
                }
            } else {
                if (!queue.isEmpty()) {
                    /*
                        Increase counter only when there are more nodes in the queue. because, suppose
                        if there is only one node in the tree, then it will return 1
                     */
                    minDepth++;
                    queue.add(nullNode);
                }
            }
        }
        return minDepth;
    }
}
