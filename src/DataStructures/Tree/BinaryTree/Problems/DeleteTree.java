package DataStructures.Tree.BinaryTree.Problems;

import DataStructures.Tree.BinaryTree.TreeImpl.BinaryTree;

import java.util.Random;

/**
 * Delete a tree.
 * Approach:
 *  -   To delete a tree set the root as null and rest of the work will be done by
 *      garbage collector.
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
