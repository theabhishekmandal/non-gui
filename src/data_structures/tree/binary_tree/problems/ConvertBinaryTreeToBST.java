package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;


/**
 * Given the array representation of Complete Binary Tree i.e, if index i is the parent, index 2*i + 1 is the
 * left child and index 2*i + 2 is the right child. The task is to find the minimum number of swap required to convert it into Binary Search Tree.
 *
 * Approach
 *  -   Given a binary tree first generate the inorder array as inorder traversal in BST gives values in increasing order
 *  -   Now this array will have value and an index, now sort the array on the basis of value
 *  -   After sorting we have to count the swaps required such that it becomes bst array which has the property of
 *      left child = 2 * i + 1 and right child = 2 * i + 2;
 */

public class ConvertBinaryTreeToBST {
    public static void main(String[] args) {
        var binaryTree = new BinaryTree<Integer>();
        int[] arr = { 5, 6, 7, 8, 9, 10, 11 };
        for (int i : arr) {
            binaryTree.insertInBinaryTreeLevelOrder(i);
        }
        System.out.println(binaryTree.levelOrderPretty());
        System.out.println(solve(binaryTree));
    }

    private static int solve(BinaryTree<Integer> binaryTree) {
        if (Optional.ofNullable(binaryTree).map(BinaryTree::getRoot).isEmpty()) {
            return 0;
        }
        List<Pair<Integer, Integer>> inOrderList = new ArrayList<>();
        getInOrderList(binaryTree.getRoot(), inOrderList);
        System.out.println(inOrderList);
        inOrderList.sort(Comparator.comparingInt(Pair::getFirst));
        System.out.println(inOrderList);
        int swaps = 0;
        for (int i = 0; i < inOrderList.size();) {
            var pair = inOrderList.get(i);
            int index = pair.getSecond();
            if (i == index) {
                i++;
            } else {
                var tempPair = inOrderList.get(index);

                int temp = pair.getSecond();
                pair.setSecond(tempPair.getSecond());
                tempPair.setSecond(temp);

                temp = pair.getFirst();
                pair.setFirst(tempPair.getFirst());
                tempPair.setFirst(temp);
                swaps++;
            }
        }
        System.out.println(inOrderList);
        return swaps;
    }

    private static void getInOrderList(Node<Integer> root, List<Pair<Integer, Integer>> inOrderList) {
        if (root == null) {
            return;
        }
        getInOrderList(root.getLeft(), inOrderList);
        inOrderList.add(Pair.of(root.getData(), inOrderList.size()));
        getInOrderList(root.getRight(), inOrderList);
    }

    static class Pair<T, V> {
        private T first;
        private V second;

        public Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        public static <T, V> Pair<T, V> of(T first, V second) {
            return new Pair<>(first, second);
        }

        public T getFirst() {
            return this.first;
        }

        public V getSecond() {
            return this.second;
        }

        public void setFirst(T first) {
            this.first = first;
        }

        public void setSecond(V second) {
            this.second = second;
        }

        @Override
        public String toString() {
            return "{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }
    }
}
