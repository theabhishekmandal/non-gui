package dataStructures.Tree.BinarySearchTree.Problems;

import dataStructures.Tree.BinarySearchTree.BinarySearchTreeImpl.BinarySearchTree;
import static dataStructures.Tree.BinarySearchTree.BinarySearchTreeImpl.BinarySearchTree.node;


import java.util.Random;
import java.util.stream.IntStream;

/**
 * Given a BST find the minimum and maximum value in it.
 * Approach:
 *  In a given bst, left most non-null leaf node has the minimum value,
 *  Similarly, right most non-null leaf node has the maximum value,
 *  So, just traverse left and right for minimum and maximum value.
 */
public class FindingMinMax {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Random random = new Random();
        IntStream.rangeClosed(0, 100).forEach(x -> bst.insertInBst(random.nextInt(1000000)));
        int minValue = getMinimumInBst(bst.getRoot());
        int maxValue = getMaxValueInBst(bst.getRoot());
        System.out.println(
                "maximum value is = " + maxValue + "\nminimum value is = " + minValue + "\nbst is = " + bst.inOrder()
        );
    }

    private static <T extends Comparable<? super T>> T getMaxValueInBst(node<T> root) {
        if(root == null){
            return null;
        }
        node<T> temp = root;
        while(temp.getRight() != null){
            temp = temp.getRight();
        }
        return temp.getData();
    }

    private static <T extends Comparable<? super T>> T getMinimumInBst(node<T> root) {
        if(root == null){
            return null;
        }
        node<T> temp = root;
        while(temp.getLeft() != null){
            temp = temp.getRight();
        }
        return temp.getData();
    }
}
