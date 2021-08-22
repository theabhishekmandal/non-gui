package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Find the maximum sum in a given level in a tree
 */
public class FindingMaxSumInLevel {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder());
        System.out.println("max sum in the level is=" + maxSumInLevel(binaryTree.getRoot()));
    }

    private static int maxSumInLevel(Node<Integer> root) {
        if (root == null) {
            return 0;
        }
        var maxSum = 0;
        Node<Integer> nullNode = Node.of(null);
        Queue<Node<Integer>> queue = new ArrayDeque<>();
        queue.add(root);
        queue.add(nullNode);

        var sum = 0;
        while (!queue.isEmpty()) {
            var curr = queue.poll();
            if (curr != nullNode) {
                sum += curr.getData();
                if (curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
            } else {
                maxSum = Math.max(maxSum, sum);
                sum = 0;
                if (!queue.isEmpty()) {
                    queue.add(nullNode);
                }
            }
        }
        return maxSum;
    }
}
