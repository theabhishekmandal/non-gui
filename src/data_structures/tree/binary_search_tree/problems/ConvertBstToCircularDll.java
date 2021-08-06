package data_structures.tree.binary_search_tree.problems;

import utility.Pair;
import data_structures.tree.binary_search_tree.binary_search_tree_impl.BinarySearchTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.tree.binary_search_tree.binary_search_tree_impl.BinarySearchTree.Node;

/**
 * Given a BST, convert it into circular sorted dll.
 * Approach:
 *  -   To convert a BST to sorted order, we will use inorder traversal as it gives sorted order in bst
 *  -   a given node's left pointer will become the previous pointer for a dll and the right pointer will become
 *      the next pointer
 */

public class ConvertBstToCircularDll {

    public static void main(String[] args) {
        var bst = new BinarySearchTree<Integer>();
        var random = new Random();
        IntStream.range(0, 10).forEach(x -> bst.insertInBst(random.nextInt(20)));
        System.out.println(bst.inOrder());
        var pairString = getBstToDllAsString(bst);
        System.out.println(pairString.getFirst());
        System.out.println(pairString.getSecond());
    }

    private static <T extends Comparable<? super T>> Pair<String, String> getBstToDllAsString(BinarySearchTree<T> bst) {
        var pair = bstToDll(bst);
        if (pair == null) {
            return Pair.of("[]", "[]");
        }
        var head = pair.getFirst();
        var tail = pair.getSecond();
        var tempTail = tail;
        var builder = new StringBuilder("[");
        var builder2 = new StringBuilder("[");
        while (head != tail) {
            builder.append(head.getData()).append(", ");
            builder2.append(tempTail.getData()).append(", ");
            head = head.getRight();
            tempTail = tempTail.getLeft();
        }
        builder.append(tail.getData()).append("]");
        builder2.append(tempTail.getData()).append("]");
        return Pair.of(builder.toString(), builder2.toString());
    }

    private static <T extends Comparable<? super T>> Pair<Node<T>, Node<T>> bstToDll(BinarySearchTree<T> bst) {
        Node<T> node;
        if (bst == null || (node = bst.getRoot()) == null) {
            return null;
        }
        Deque<Node<T>> stack = new LinkedList<>();
        Node<T> curr = node;

        Node<T> head = null;
        Node<T> tail = null;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            } else {
                curr = stack.pop();
                Node<T> right = curr.getRight();
                if (head == null) {
                    // if head is null then point the left and right to itself
                    curr.setRight(curr);
                    curr.setLeft(curr);
                    head = tail = curr;
                } else {
                    tail.setRight(curr);
                    curr.setLeft(tail);
                    tail = tail.getRight();
                }
                curr = right;
            }
        }
        // at last assign left of head to tail and assign right of tail to head to create a circle
        if (head != null) {
            head.setLeft(tail);
            tail.setRight(head);
        }
        return Pair.of(head, tail);
    }
}
