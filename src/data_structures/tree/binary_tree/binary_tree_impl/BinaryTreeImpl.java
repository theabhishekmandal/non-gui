package data_structures.tree.binary_tree.binary_tree_impl;

import java.util.stream.IntStream;

public class BinaryTreeImpl {
    public static void main(String[] args) {
        var binaryTree = new BinaryTree<Integer>();
        IntStream.range(0, 7).forEach(binaryTree::insertInBinaryTreeLevelOrder);

        String preOrderIter = binaryTree.preOrder();
        String preOrderRec = binaryTree.preOrderRecursive();
        System.out.println(preOrderIter.equals(preOrderRec));
        System.out.println(preOrderIter);
        System.out.println(preOrderRec);

        String inOrderIter = binaryTree.inOrder();
        String inOrderRec = binaryTree.inOrderRecursive();
        System.out.println(inOrderIter.equals(inOrderRec));
        System.out.println(inOrderIter);
        System.out.println(inOrderRec);

        String postOrderIter = binaryTree.postOrder();
        String postOrderRec = binaryTree.postOrderRecursive();
        System.out.println(postOrderIter.equals(postOrderRec));
        System.out.println(postOrderIter);
        System.out.println(postOrderRec);

        String levelOrderIter = binaryTree.levelOrder();
        System.out.println(levelOrderIter);
    }
}
