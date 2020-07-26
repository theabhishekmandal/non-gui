package DataStructures.Tree.BinarySearchTree.Problems;

import DataStructures.Tree.BinarySearchTree.BinarySearchTreeImpl.BinarySearchTree;
import static DataStructures.Tree.BinarySearchTree.BinarySearchTreeImpl.BinarySearchTree.node;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Given a BST, find a node in it if exists
 * Approach:
 *      Compare the value to be found with the tree's root, if it is smaller than it then search in the left
 *      subtree, if it is greater than search in the right subtree.
 */
public class FindNodeInBst {
    public static void main(String[] args) {
        var bst = new BinarySearchTree<Integer>();

        Random random = new Random();
        int valueToBeFound = random.nextInt(20);
        IntStream.rangeClosed(0, 10).forEach(bst::insertInBst);
        node<Integer> node = findInBst(bst.getRoot(), valueToBeFound);
        node<Integer> nodeNew = findBstUsingRecursive(bst.getRoot(), valueToBeFound);
        System.out.println(
                "value to be found " + valueToBeFound + " value found = " + node + "\n" +
                        "value to be found " + valueToBeFound + " value found = " + nodeNew + "\n" +
                bst.inOrder());
    }
    private static <T extends Comparable<? super T>> node<T> findInBst(node<T> root, T value) {
        if(root == null){
            return null;
        }
        node<T> temp = root;
        node<T> prev = null;
        while(temp != null) {
            prev = temp;
            int compareTo = temp.getData().compareTo(value);
            if(compareTo == 0){
                return temp;
            }
            if(compareTo > 0) {
                temp = temp.getLeft();
            }
            else{
                temp = temp.getRight();
            }
        }
        int compareTo = prev.getData().compareTo(value);
        if(compareTo == 0){
            return prev;
        }
        return null;
    }

    private static <T extends Comparable<? super T>> node<T> findBstUsingRecursive(node<T> root, T value){
        if(root == null){
            return null;
        }
        int compare = root.getData().compareTo(value);
        if(compare == 0){
            return root;
        }
        if(compare <= 0){
            return findBstUsingRecursive(root.getRight(), value);
        }
        else{
            return findBstUsingRecursive(root.getLeft(), value);
        }
    }
}
