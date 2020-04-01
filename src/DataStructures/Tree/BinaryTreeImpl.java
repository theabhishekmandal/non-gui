package DataStructures.Tree;

import DataStructures.Tree.TreeImpl.BinaryTree;

public class BinaryTreeImpl {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        for(int i = 0; i < 10; i++) binaryTree.insertInBinaryTreeLevelOrder(i);
        System.out.println(binaryTree.preOrder());
        System.out.println(binaryTree.inOrder());
    }
}
