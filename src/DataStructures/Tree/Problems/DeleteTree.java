package DataStructures.Tree.Problems;

import DataStructures.Tree.TreeImpl.BinaryTree;

import java.util.Random;

/**
 * Delete a tree.
 * Approach:
 *  -   To delete a tree set the
 */

public class DeleteTree {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Random random = new Random();
        for(int i = 0; i < random.nextInt(20); i++) {
            int value = random.nextInt(100);
            binaryTree.insertInBinaryTreeLevelOrder(value);
        }
        System.out.println("before deletion " + binaryTree.levelOrder());
        binaryTree.deleteTree();
        System.out.println("after deletion " + binaryTree.levelOrder());
    }
}
