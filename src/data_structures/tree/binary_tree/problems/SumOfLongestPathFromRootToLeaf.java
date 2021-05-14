package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree, find the sum of all nodes on the longest path from root to leaf node.
 * If two or more paths compare for the longest path, then the path having maximum sum of nodes is being considered
 *
 * Note: find the sum of longest path even if it smaller i.e
 *              1
 *            /  \
 *           2    5
 *          /
 *         1
 *  here maxSum will be 4 [1, 2, 1] because it is the longest path from root
 *
 *  Approach
 *      -   add the node, sum and height in the queue and calculate the value
 */

public class SumOfLongestPathFromRootToLeaf {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        IntStream.rangeClosed(0, random.nextInt(10)).forEach(x -> binaryTree.insertInBinaryTreeLevelOrder(random.nextInt(10)));
        System.out.println(binaryTree.levelOrderPretty());
        var heightAndSum = sumOfLongestPathFromRootToLeaf(binaryTree);
        System.out.println("maxHeight is " + heightAndSum.fst + " maxSum is " + heightAndSum.snd);
    }

    static class StackValue {
       Node<Integer> node;
       int height;
       int value;

       StackValue(Node<Integer> node, int height, int value) {
           this.node = node;
           this.height = height;
           this.value = value;
       }
       static StackValue of(Node<Integer> node, int height, int value) {
           return new StackValue(node, height, value);
       }
    }

    private static Pair<Integer, Integer> sumOfLongestPathFromRootToLeaf(BinaryTree<Integer> binaryTree) {
        if (Optional.ofNullable(binaryTree).map(BinaryTree::getRoot).isEmpty()) {
            return Pair.of(null, null);
        }
        var queue = new ArrayDeque<StackValue>();
        var curr = StackValue.of(binaryTree.getRoot(), 0, binaryTree.getRoot().getData());
        int maxHeight = Integer.MIN_VALUE;
        int maxSum = Integer.MIN_VALUE;
        queue.add(curr);
        while (!queue.isEmpty()) {
            curr = queue.poll();
            var left = curr.node.getLeft();
            var right = curr.node.getRight();
            if (left != null) {
                queue.add(StackValue.of(left, curr.height + 1, curr.value + left.getData()));
            }
            if (right != null) {
                queue.add(StackValue.of(right, curr.height + 1, curr.value + right.getData()));
            }

            // add the sum of max height nodes
            if (curr.height > maxHeight) {
                maxHeight = curr.height;
                maxSum = curr.value;
            }
            // if height is same check for sum
            else if (curr.height == maxHeight && curr.value > maxSum) {
                maxSum = curr.value;
            }
        }
        return Pair.of(maxHeight, maxSum);
    }

}
