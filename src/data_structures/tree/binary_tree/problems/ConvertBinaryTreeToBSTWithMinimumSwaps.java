package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import utility.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;


/**
 * Given the array representation of Complete Binary Tree i.e, if index i is the parent, index 2*i + 1 is the
 * left child and index 2*i + 2 is the right child. The task is to find the minimum number of swap required to convert
 * it into Binary Search Tree.
 *
 * Approach
 *  -   Given a binary tree first generate the inorder array as inorder traversal in BST gives values in increasing order
 *  -   Now this array will have value and an index, now sort the array on the basis of value
 *  -   After sorting we have to count the swaps required such that it becomes bst array which has the property of
 *      left child = 2 * i + 1 and right child = 2 * i + 2;
 */

public class ConvertBinaryTreeToBSTWithMinimumSwaps {
    public static void main(String[] args) {
        var binaryTree = new BinaryTree<Integer>();
        Stream.of(5, 6, 7, 8, 9, 10, 11).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrderPretty());
        System.out.println(solve(binaryTree));
    }

    private static int solve(BinaryTree<Integer> binaryTree) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return 0;
        }
        List<Pair<Integer, Integer>> inOrderList = new ArrayList<>();
        getInOrderList(binaryTree.getRoot(), inOrderList);
        System.out.println("list before sorting\n" + inOrderList);
        inOrderList.sort(Comparator.comparingInt(Pair::getFirst));
        System.out.println("list after sorting\n" + inOrderList);
        var swaps = 0;
        for (var i = 0; i < inOrderList.size();) {
            var pair = inOrderList.get(i);
            var index = pair.getSecond();
            if (i == index) {
                i++;
            } else {
                swapIndex(inOrderList, i, index);
                swaps++;
            }
        }
        System.out.println(inOrderList);
        return swaps;
    }

    private static void swapIndex(List<Pair<Integer, Integer>> inOrderList, int i, int index) {
        var temp = inOrderList.get(i);
        inOrderList.set(i, inOrderList.get(index));
        inOrderList.set(index, temp);
    }

    private static void getInOrderList(Node<Integer> root, List<Pair<Integer, Integer>> inOrderList) {
        if (root == null) {
            return;
        }
        getInOrderList(root.getLeft(), inOrderList);
        inOrderList.add(Pair.of(root.getData(), inOrderList.size()));
        getInOrderList(root.getRight(), inOrderList);
    }
}
