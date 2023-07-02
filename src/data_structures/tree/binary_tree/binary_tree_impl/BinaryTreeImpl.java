package data_structures.tree.binary_tree.binary_tree_impl;

import java.util.stream.IntStream;

public class BinaryTreeImpl {
    public static void main(String[] args) {
        final var binaryTree = new BinaryTree<Integer>();
        IntStream.range(0, 7).forEach(binaryTree::insertInBinaryTreeLevelOrder);

        final String preOrderIter = binaryTree.preOrder();
        final String preOrderRec = binaryTree.preOrderRecursive();
        System.out.println(preOrderIter.equals(preOrderRec));
        System.out.println(preOrderIter);
        System.out.println(preOrderRec);

        final String inOrderIter = binaryTree.inOrder();
        final String inOrderRec = binaryTree.inOrderRecursive();
        System.out.println(inOrderIter.equals(inOrderRec));
        System.out.println(inOrderIter);
        System.out.println(inOrderRec);

        final String postOrderIter = binaryTree.postOrder();
        final String postOrderNew = binaryTree.postOrderNew();
        System.out.println("is postOrderNew == postOrderIter? " + postOrderIter.equals(postOrderNew));
        final String postOrderRec = binaryTree.postOrderRecursive();
        System.out.println(postOrderIter.equals(postOrderRec));
        System.out.println(postOrderIter);
        System.out.println(postOrderRec);

        final String levelOrderIter = binaryTree.levelOrder();
        System.out.println(levelOrderIter);
    }
}
