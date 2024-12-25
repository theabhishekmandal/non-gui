package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Create the mirror image of binary tree
 * Approach
 *  -   You can use top down approach or bottom up approach
 *  -   So, in this top down approach, preorder traversal is used.
 *  -   The idea is there will be two stacks one will be used to traverse the current tree and
 *      the second one is used to iterate over the new binary tree.
 *  -   So, after pulling both nodes from the two stack, create left and right nodes which will be in reverse order
 *      for the mirror image node.
 *  -   Now, push nodes in reverse order, that is for one insert the left one and for other insert the right one.
 */
public class _12EasyCreateMirrorTree {
    public static void main(String[] args) {
        var random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        random.ints(20, 0, 20).forEach(binaryTree::insertInBinaryTreeLevelOrder);

        System.out.println("binary Tree\n" + binaryTree.levelOrderPretty());
        var tree = createMirrorTree(binaryTree);
        System.out.println("mirrored binary Tree\n" + tree.levelOrderPretty());
    }

    private static <T> BinaryTree<T> createMirrorTree(BinaryTree<T> binaryTree) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return new BinaryTree<>();
        }

        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(binaryTree.getRoot());

        //creating head node of new BinaryTree
        Node<T> newRoot = Node.of(binaryTree.getRoot().getData());

        Deque<Node<T>> stack2 = new ArrayDeque<>();
        stack2.push(newRoot);

        while (!stack.isEmpty()) {
            var curr = stack.pop();
            var newCurr = stack2.pop();
            createMirrorNode(newCurr, curr);

            if (curr.getLeft() != null) {
                stack2.push(newCurr.getRight());
                stack.push(curr.getLeft());
            }
            if (curr.getRight() != null) {
                stack2.push(newCurr.getLeft());
                stack.push(curr.getRight());
            }
        }

        return new BinaryTree<>(newRoot);
    }

    private static <T> void createMirrorNode(Node<T> newCurr, Node<T> curr) {
        Node<T> newLeft = (curr.getRight() == null)? null : Node.of(curr.getRight().getData());
        Node<T> newRight = (curr.getLeft() == null)? null : Node.of(curr.getLeft().getData());
        newCurr.setLeft(newLeft);
        newCurr.setRight(newRight);
    }
}
