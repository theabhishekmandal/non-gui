package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary Tree remove all the leaf nodes from the tree
 *
 * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 *   0
 *  1 3
 * 2 9 4 *
 * 5 * * * 6 *
 * 8 7 * *
 *
 *   0
 *  1 3
 * 2 * 4 *
 * 5 * * *
 *
 * Approach
 *  -   In this we are using level order traversal.
 *  -   For each node we will check whether it's left or right child's children are null or not
 *  -   If yes then remove left/right child as null
 *  -   Eg: In above example we see 5 has two children 8 and 7, 8 is left child and 7 is right child
 *          Since, both 8 and 7 children are null, so they qualify for leaf nodes, and they are removed.
 *
 */

public class RemoveLeafNodes {
    private static final Random random = new Random();

    public static void main(String[] args) {
        List<Integer> list = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        System.out.println(list);
        var binaryTree = new BinaryTree<Integer>();
        for (var i : list) {
            randomInsert(i, binaryTree);
        }
        System.out.println(binaryTree.levelOrderPretty());
        removeLeafNodes(binaryTree);
        System.out.println(binaryTree.levelOrderPretty());

    }

    private static <T> void removeLeafNodes(BinaryTree<T> binaryTree) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return;
        }

        var root = binaryTree.getRoot();
        if (root.getLeft() == null || root.getRight() == null) {
            binaryTree.setRoot(null);
        }

        Deque<Node<T>> queue = new ArrayDeque<>();
        queue.add(binaryTree.getRoot());

        while(!queue.isEmpty()) {
            var curr = queue.poll();
            var left = curr.getLeft();
            var right = curr.getRight();
            if (left != null) {
                removeNullElseProcess(curr, left, queue);
            }
            if (right != null) {
                removeNullElseProcess(curr, right, queue);
            }
        }
    }
    private static <T> void removeNullElseProcess(Node<T> parent, Node<T> child, Deque<Node<T>> queue) {
        if (child.getLeft() == null && child.getRight() == null) {
            if (parent.getLeft() == child) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else {
            queue.add(child);
        }
    }

    private static <T> void randomInsert(T data, BinaryTree<T> binaryTree) {
        if (binaryTree.getRoot() == null) {
            binaryTree.setRoot(Node.of(data));
            return;
        }
        Node<T> temp = binaryTree.getRoot();
        while (temp != null) {
            var addToRight = random.nextBoolean();
            if (addToRight) {
                if (temp.getRight() == null) {
                    temp.setRight(Node.of(data));
                    return;
                } else {
                    temp = temp.getRight();
                }
            } else {
                if (temp.getLeft() == null) {
                    temp.setLeft(Node.of(data));
                    return;
                } else {
                    temp = temp.getLeft();
                }
            }
        }
    }
}
