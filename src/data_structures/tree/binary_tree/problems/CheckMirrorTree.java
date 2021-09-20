package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 * Given two binary Trees check whether that both of them are mirror images of each other.
 * Approach
 *     -    check whether the two trees are null, then return true
 *     -    check whether one of them is null, then return false
 *     -    now using C,L,R(preOrder) in first tree and C,R,L(preOrder) in the second tree, check whether
 *          each node is equal to another
 *     -    at last check whether their size are same or not because, while traversing there can be a possibility
 *          that one of the node is null, then it will exit out of the loop
 */
public class CheckMirrorTree {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        var binaryTree1 = new BinaryTree<Integer>();

        random.ints(20, 0, 20).forEach(x -> {
            binaryTree.insertInBinaryTreeLevelOrder(x);
            binaryTree1.insertInBinaryTreeLevelOrder(x);
        });

        if (random.nextBoolean()) {
            createMirrorTree(binaryTree1.getRoot());
        }

        var isMirrorImage = areMirrors(binaryTree.getRoot(), binaryTree1.getRoot());
        System.out.println(isMirrorImage);
        System.out.println("binaryTree\n" + binaryTree.levelOrderPretty());
        System.out.println("binaryTree1\n" + binaryTree1.levelOrderPretty());
    }

    private static boolean areMirrors(Node<Integer> root, Node<Integer> root1) {
        if (root == null && root1 == null) {
            return true;
        }
        if (root == null || root1 == null) {
            return false;
        }

        Deque<Node<Integer>> stack = new ArrayDeque<>();
        stack.push(root);

        Deque<Node<Integer>> stack1 = new ArrayDeque<>();
        stack1.push(root1);

        var flag = true;
        while (!stack.isEmpty() && !stack1.isEmpty()) {
            var first = stack.pop();
            var second = stack1.pop();
            if (!first.getData().equals(second.getData())) {
                flag = false;
                break;
            }
            // adding right child and then left to check for mirror image
            addToQueue(stack, first, false);

            // adding left child and then right to check for mirror image
            addToQueue(stack1, second, true);
        }

        return flag && stack.size() == stack1.size();
    }

    private static void addToQueue(Deque<Node<Integer>> stack, Node<Integer> curr, boolean leftToRight) {
        var left = (leftToRight) ? curr.getLeft() : curr.getRight();
        var right = (leftToRight) ? curr.getRight() : curr.getLeft();
        if (left != null) {
            stack.push(left);
        }
        if (right != null) {
            stack.push(right);
        }
    }

    // using top down approach
    private static void createMirrorTree(Node<Integer> node) {
        if (node == null) {
            return;
        }
        Deque<Node<Integer>> stack = new ArrayDeque<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            var curr = stack.pop();
            swap(curr);
            if (curr.getRight() != null) {
                stack.push(curr.getRight());
            }

            if (curr.getLeft() != null) {
                stack.push(curr.getLeft());
            }
        }
    }

    private static void swap(Node<Integer> curr) {
        var temp = curr.getRight();
        curr.setRight(curr.getLeft());
        curr.setLeft(temp);
    }
}
