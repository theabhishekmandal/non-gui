package data_structures.tree.binary_search_tree.problems;

import data_structures.tree.binary_search_tree.binary_search_tree_impl.BinarySearchTree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.tree.binary_search_tree.binary_search_tree_impl.BinarySearchTree.Node;


/**
 * Given a BST and an integer K find the k smallest and k largest values
 * Approach:
 *  -   to find the k smallest value traverse using the normal inorder Traversal and use a counter. return the
 *      value of node when counter == k
 *  -   to find the k largest value traverse using the reverse inorder Traversal i.e instead of going to left first
 *      from current node, go to the right node. return the value of the node when counter == k
 */
public class _8EasyFindKSmallestOrKLargest {
    public static void main(String[] args) {
        var random = new Random();
        var bst = new BinarySearchTree<Integer>();
        IntStream.range(1, 20).forEach(bst::insertInBst);
        System.out.println(bst.getSize());
        int num = 1 + random.nextInt(bst.getSize());
        Optional<Integer> kSmallestValue = getKSmallestValue(bst, num);
        Optional<Integer> kLargestValue = getKLargestValue(bst, num);
        System.out.println(num + " smallest integer = " + kSmallestValue.orElse(null));
        System.out.println(num + " largest integer = " + kLargestValue.orElse(null));
    }

    private static <T extends Comparable<? super T>> Optional<T> getKSmallestValue(BinarySearchTree<T> bst, int num) {
        if(bst == null) {
            throw new NullPointerException();
        }
        if(num > bst.getSize() || num < 1) {
            throw new IndexOutOfBoundsException();
        }
        var curr = bst.getRoot();
        var stack = new LinkedList<Node<T>>();
        int counter = 0;
        while(curr != null || !stack.isEmpty()) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            }
            else {
                curr = stack.pop();
                counter++;
                if(counter == num) {
                    return Optional.of(curr.getData());
                }
                curr = curr.getRight();
            }
        }
        return Optional.empty();
    }

    private static <T extends Comparable<? super T>> Optional<T> getKLargestValue(BinarySearchTree<T> bst, int num) {
        if(bst == null) {
            throw new NullPointerException();
        }
        if(num > bst.getSize() || num < 1) {
            throw new IndexOutOfBoundsException();
        }
        var curr = bst.getRoot();
        var stack = new LinkedList<Node<T>>();
        int counter = 0;
        while(curr != null || !stack.isEmpty()) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.getRight();
            }
            else {
                curr = stack.pop();
                counter++;
                if(counter == num) {
                    return Optional.of(curr.getData());
                }
                curr = curr.getLeft();
            }
        }
        return Optional.empty();
    }
}
