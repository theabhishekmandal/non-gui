package data_structures.tree.binary_search_tree.problems;

import data_structures.tree.binary_search_tree.binary_search_tree_impl.BinarySearchTree;

import java.util.*;
import java.util.stream.IntStream;

import static data_structures.tree.binary_search_tree.binary_search_tree_impl.BinarySearchTree.Node;

/**
 * Given a BST and two integers k1 and k2, which may or may not be present in bst. Find all the numbers which satisfy
 * k1 <= x <= k2
 *
 * Approach
 *  -   Traverse the bst in inorder manner and check if the node satisfies the above condition or not
 *  -   add the found node value in the list
 */

public class _7EasyFindInRange {
    public static void main(String[] args) {
        var random = new Random();
        var bst = new BinarySearchTree<Integer>();
        IntStream.range(0, 50).forEach(x -> bst.insertInBst(random.nextInt(100)));
        int one = 1 + random.nextInt(bst.getSize());
        int second = one + random.nextInt(bst.getSize());
        System.out.println(bst.inOrder());
        System.out.println("numbers in bst in range first = " + one  + " second = " + second + " \n" + getAllInRange(bst, one, second));
    }

    private static <T extends Comparable<? super T>> List<String> getAllInRange(BinarySearchTree<T> bst, T one, T second) {
        if(bst == null || bst.getRoot() == null || one == null || second == null) {
            return Collections.emptyList();
        }
        var curr = bst.getRoot();
        var stack = new LinkedList<Node<T>>();
        var list = new ArrayList<String>();
        while(curr != null || !stack.isEmpty()) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            }
            else {
                curr = stack.pop();
                int compare = curr.getData().compareTo(one);
                int compare2 = curr.getData().compareTo(second);
                if(compare >= 0 && compare2 <= 0) {
                    list.add(curr.getData().toString());
                }
                curr = curr.getRight();
            }
        }
        return list;
    }
}
