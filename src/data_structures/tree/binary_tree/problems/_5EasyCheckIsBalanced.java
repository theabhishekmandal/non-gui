package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a tree check if it is balanced or not
 * Approach:
 *  -   use recursion to get the left and right height
 *  -   compare those heights, if they differ more than 1 then tree is not balanced
 *  -   Height is calculated as the max height from root to deepest node. To find, max height calculate left
 *      and right height and take the max out of left and right. Add 1 to this max which represents the root and return
 *      back to recursion tree.
 */
public class _5EasyCheckIsBalanced {
    // for input to generate random tree
    private static final Random random = new Random();
    private static boolean isBalanced = true;

    public static void main(String[] args) {
        final var tree = new BinaryTree<Integer>();
        tree.setRoot(createRandomTree());
        System.out.println(tree.levelOrderPretty());
        final boolean bal = isBalanced(tree.getRoot());
        System.out.println("tree is" + (bal ? " balanced" : " not balanced"));
    }

    private static boolean isBalanced(Node<Integer> root) {
        isBalanced = true;
        isBal(root);
        return isBalanced;
    }

    private static int isBal(Node<Integer> root) {
        if (root == null) {
            return 0;
        }

        final var left = isBal(root.getLeft());
        final var right = isBal(root.getRight());
        if (Math.abs(left - right) > 1) {
            isBalanced = false;
        }
        return 1 + Math.max(left, right);
    }

    private static Node<Integer> createRandomTree() {
        final var size = random.nextInt(5);
        final var root = Node.of(0);
        final Deque<Node<Integer>> queue = new ArrayDeque<>();
        for (var i = 0; i < size; i++) {
            final Node<Integer> nodeToBeAdded = Node.of(i + 1);
            queue.add(root);
            var doNotAdd = false;
            while (!queue.isEmpty() && !doNotAdd) {
                final var temp = queue.poll();
                if (random.nextBoolean()) {
                    doNotAdd = addToLeft(queue, nodeToBeAdded, temp);
                } else {
                    doNotAdd = addToRight(queue, nodeToBeAdded, temp);
                }
            }
        }
        return root;
    }

    private static boolean addToRight(Deque<Node<Integer>> queue, Node<Integer> nodeToBeAdded, Node<Integer> temp) {
        if (temp.getRight() == null) {
            temp.setRight(nodeToBeAdded);
            return true;
        } else {
            queue.add(temp.getRight());
        }
        return false;
    }

    private static boolean addToLeft(Deque<Node<Integer>> queue, Node<Integer> nodeToBeAdded, Node<Integer> temp) {
        if (temp.getLeft() == null) {
            temp.setLeft(nodeToBeAdded);
            return true;
        } else {
            queue.add(temp.getLeft());
        }
        return false;
    }
}
