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
 */
public class CheckIsBalanced {
    public static void main(String[] args) {
        var tree = new BinaryTree<Integer>();
        tree.setRoot(createRandomTree());
        System.out.println(tree.levelOrderPretty());
        boolean bal = isBalanced(tree.getRoot());
        System.out.println("tree is" + (bal ?  " balanced" : " not balanced"));
    }

    private static boolean isBalanced = true;
    private static boolean isBalanced(Node<Integer> root) {
        isBalanced = true;
        isBal(root);
        return isBalanced;
    }

    private static int isBal(Node<Integer> root) {
        if (root == null) {
            return 0;
        }

        int left = isBal(root.getLeft());
        int right = isBal(root.getRight());
        if (Math.abs(left - right) > 1) {
            isBalanced = false;
        }
        return 1 + Math.max(left, right);
    }

    // for input to generate random tree
    private static final Random random = new Random();
    private static Node<Integer> createRandomTree() {
        int size = random.nextInt(5);
        Node<Integer> root = Node.of(0);
        Deque<Node<Integer>> queue = new ArrayDeque<>();
        for (int i = 0; i < size; i++) {
            Node<Integer> nodeToBeAdded = Node.of(i + 1);
            queue.add(root);
            while (!queue.isEmpty()) {
                var temp = queue.poll();
                if (random.nextBoolean()) {
                   if (temp.getLeft() == null) {
                       temp.setLeft(nodeToBeAdded);
                       break;
                   } else {
                       queue.add(temp.getLeft());
                   }
                } else {
                    if (temp.getRight() == null) {
                        temp.setRight(nodeToBeAdded);
                        break;
                    } else {
                        queue.add(temp.getRight());
                    }
                }
            }
        }
        return root;
    }
}
