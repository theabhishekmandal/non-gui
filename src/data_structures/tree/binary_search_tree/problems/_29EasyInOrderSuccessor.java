package data_structures.tree.binary_search_tree.problems;

import data_structures.tree.binary_search_tree.binary_search_tree_impl.BinarySearchTree;
import static data_structures.tree.binary_search_tree.binary_search_tree_impl.BinarySearchTree.Node;

import java.util.List;
import java.util.Random;

/**
 * Given a binary search tree find the inorder successor of a node.
 * Approach
 *  -   if a inorder successor exists then it will be the leftmost child in the right subtree
 *  -   if a value is smaller than current node, curr node is greater than value and it might be a possible successor. go to left subTree.
 *  -   if a value is greater than or equal to current node go to right subtree.
 *
 */
public class _29EasyInOrderSuccessor {
    public static void main(String[] args) {
        var binaryTree = new BinarySearchTree<Integer>();

        var random = new Random();
//        random.ints(0, 50).limit(20).forEach(binaryTree::insertInBst);

        binaryTree.insertInBst(20);
        binaryTree.insertInBst(25);
        binaryTree.insertInBst(9);
        binaryTree.insertInBst(12);
        binaryTree.insertInBst(5);
        binaryTree.insertInBst(11);
        binaryTree.insertInBst(14);

        System.out.println(binaryTree.inOrder());

        List.of(5).forEach(
                num -> System.out.println("successor of number " + num + " is = " + findInOrderSuccessor(num, binaryTree.getRoot()))
        );
    }

    private static Node<Integer> findInOrderSuccessor(int i, Node<Integer> root) {
        if (root == null) {
            return null;
        }
        Node<Integer> succ = null;

        var curr = root;
        while (curr != null) {
            if (curr.getData() > i) {
                succ = curr;
                curr = curr.getLeft();
            }
            else if (curr.getData() <= i) {
                curr = curr.getRight();
            }
        }
        return succ;
    }
}
