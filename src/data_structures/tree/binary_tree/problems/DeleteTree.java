package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.Random;

/**
 * Delete a tree.
 * Approach:
 *  -   To delete a tree set the root as null and rest of the work will be done by
 *      garbage collector.
 */

public class DeleteTree {
    public static void main(String[] args) {
        var binaryTree = new BinaryTree<Integer>();
        var random = new Random();
        random.ints(20, 0, 10).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println("before deletion " + binaryTree.levelOrder());
        binaryTree.deleteTree();
        System.out.println("after deletion " + binaryTree.levelOrder());
    }
}
