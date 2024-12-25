package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree do diagonal traversal.
 *              0
 *           1      2
 *        3     4 5    6
 *      7   8  9
 * Output [0, 2, 6, 1, 4, 5, 3, 8, 9, 7]
 * Approach:
 *  -   first initialise a counter = 0, and a list of list
 *  -   the counter is used for to which list the node will be added, and list of list denotes number of lists required
 *      for each level
 *  -   Basically, if the current node is left node then add it to next level and if current node is the right node
 *      then add it to the same level as that of the parent.
 */

public class _19EasyDiagonalTraversal {
    public static void main(String[] args) {
        var tree = new BinaryTree<Integer>();
        IntStream.range(0, 10).forEach(tree::insertInBinaryTreeLevelOrder);
        System.out.println(tree.levelOrder());
        List<Integer> arr = getDiagonal(tree.getRoot());
        System.out.println(arr);
    }

    private static <T> List<T> getDiagonal(Node<T> root) {
        List<List<T>> arr = new ArrayList<>();
        Deque<Node<T>> stack = new ArrayDeque<>();
        Node<T> curr = root;
        var counter = 0;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                if (arr.size() <= counter) {
                    arr.add(new ArrayList<>());
                }
                arr.get(counter).add(curr.getData());
                counter++;
                curr = curr.getLeft();
            } else {
                Node<T> temp = stack.pop();
                counter--;
                curr = temp.getRight();
            }
        }
        return arr.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }
}
