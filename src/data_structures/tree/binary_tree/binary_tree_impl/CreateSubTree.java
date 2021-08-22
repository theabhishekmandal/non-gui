package data_structures.tree.binary_tree.binary_tree_impl;

import java.lang.ref.Reference;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

public class CreateSubTree {
    private static final Random random = new Random();
    public static boolean addNewValueInTree = false;
    private CreateSubTree() {
    }

    public static <T> BinaryTree<T> getRandomSubTreeFromParentTree(BinaryTree<T> binaryTree) {
        int start;
        int size = binaryTree.getSize();
        do {
            start = random.nextInt(size);
        } while (start >= (size + size >> 1));
        return getSubTreeFromNode(getStartNode(binaryTree, start));
    }

    private static <T> BinaryTree<T> getSubTreeFromNode(Node<T> startNode) {
        BinaryTree<T> subTree = new BinaryTree<>();
        Deque<Node<T>> queue = new ArrayDeque<>();
        queue.add(startNode);
        System.out.println(startNode);
        while (!queue.isEmpty()) {
            Node<T> curr = queue.poll();
            subTree.insertInBinaryTreeLevelOrder(curr.getData());
            if (addNewValueInTree) {
                addNewValueInTree = false;
                if (curr.getData() instanceof Number) {
                    subTree.insertInBinaryTreeLevelOrder((T) (Number) Integer.MAX_VALUE);
                }
                if (curr.getData() instanceof CharSequence) {
                    subTree.insertInBinaryTreeLevelOrder((T) "A");
                }
            }
            if (curr.getLeft() != null) {
                queue.add(curr.getLeft());
            }
            if (curr.getRight() != null) {
                queue.add(curr.getRight());
            }
        }
        return subTree;
    }

    private static <T> Node<T> getStartNode(BinaryTree<T> binaryTree, int start) {
        var nullNode = Node.<T>of(null);
        Deque<Node<T>> queue = new ArrayDeque<>();

        queue.add(binaryTree.getRoot());
        queue.add(nullNode);

        int counter = 0;
        Node<T> curr = null;
        System.out.println("start = " + start);

        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (counter == start && curr != nullNode) {
                break;
            }
            if (curr != null) {
                counter++;
            }
            if (curr != null) {
                if (curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
            } else {
                if (!queue.isEmpty()) {
                    queue.add(nullNode);
                }
            }
        }
        return curr;
    }
}
