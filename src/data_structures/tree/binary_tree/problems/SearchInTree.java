package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Search a node in the Binary Tree
 * Approach:
 *  -   We can use level order traversal to find the node in the tree
 */
public class SearchInTree {
    public static void main(String[] args) {
        var binaryTree = new BinaryTree<Integer>();
        var random = new Random();
        for (var i = 0; i < 10; i++) {
            binaryTree.insertInBinaryTreeLevelOrder(random.nextInt(10));
        }
        var searchValue = random.nextInt(10);

        System.out.println(binaryTree.levelOrder());
        System.out.println(searchValue);
        var found = findByRecursive(searchValue, binaryTree.getRoot());
        var found2 = findByIteration(searchValue, binaryTree.getRoot());
        System.out.println(found + " " + found2);
    }

    /*
        Approach
        search in left subtree or right subtree, which ever yields result.
     */
    private static boolean findByRecursive(Integer searchValue, Node<Integer> node) {
        if (node == null) {
            return false;
        }
        if (node.getData().equals(searchValue)) {
            return true;
        }
        return findByRecursive(searchValue, node.getLeft()) || findByRecursive(searchValue, node.getRight());
    }

    /*
        searching using level order traversal
     */
    private static boolean findByIteration(Integer searchValue, Node<Integer> node) {
        if (node == null) {
            return false;
        }
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node<Integer> curr = queue.poll();
            if (curr.getData().equals(searchValue)) {
                return true;
            }
            if (curr.getLeft() != null) {
                queue.add(curr.getLeft());
            }
            if (curr.getRight() != null) {
                queue.add(curr.getRight());
            }
        }
        return false;
    }
}
