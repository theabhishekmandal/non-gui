package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary Tree, do boundary traversal
 * Input:
 *           20
 *         /   \
 *        8     22
 *      /   \    \
 *   null    12    25
 *         /  \
 *        10   14
 *
 * Output: 20 8 12 10 14 25 22
 *
 * Approach:
 *  -   Get the leaves as list
 *  -   Get the left and right view of the list
 *  -   combine and merge them
 */
public class BoundaryTraversal {
    public static void main(String[] args) {
        var tree = new BinaryTree<Integer>();
        IntStream.range(0, 9).forEach(tree::insertInBinaryTreeLevelOrder);
        System.out.println(tree.levelOrderPretty());
        System.out.println(getBoundaryTraversalNew(tree));
    }

    private static <T> List<T> getBoundaryTraversalNew(BinaryTree<T> tree) {
        if (tree == null || tree.getRoot() == null) {
            return Collections.emptyList();
        }
        var leavesList = getLeaves(tree.getRoot());
        var leftRightList = getLeftAndRightView(tree.getRoot());
        return Stream.of(leftRightList.get(0), leavesList, leftRightList.get(1))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static <T> Deque<T> getLeaves(Node<T> root) {
        final Deque<T> deque = new ArrayDeque<>();

        final Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(root);
        stack.push(root);

        while (!stack.isEmpty()) {
            final var curr = stack.pop();
            if (!stack.isEmpty() && curr == stack.peek()) {
                if (!isRightNull(curr)) {
                    stack.push(curr.getRight());
                    stack.push(curr.getRight());
                }
                if (!isLeftNull(curr)) {
                    stack.push(curr.getLeft());
                    stack.push(curr.getLeft());
                }
            } else {
               if (isLeftNull(curr) && isRightNull(curr)) {
                   deque.add(curr.getData());
               }
            }
        }
        return deque;
    }

    private static <T> boolean isLeftNull(Node<T> curr) {
        return curr.getLeft() == null;
    }

    private static <T> boolean isRightNull(Node<T> curr) {
        return curr.getRight() == null;
    }

    private static <T> List<Deque<T>> getLeftAndRightView(Node<T> root) {

        final Deque<T> leftDeque = new ArrayDeque<>();
        leftDeque.add(root.getData());

        Node<T> left = root.getLeft();
        // add all the left nodes, if left node is not present then add right node, but
        // leave the leaf nodes
        while (left != null) {
            if (isLeftNull(left) && isRightNull(left))  {
                break;
            }
            leftDeque.addLast(left.getData());
            if (!isLeftNull(left)) {
                left = left.getLeft();
            } else {
                left = left.getRight();
            }
        }

        final Deque<T> rightDeque = new ArrayDeque<>();
        Node<T> right = root.getRight();
        // add all the right nodes, if right node is not present then add left node, but
        // leave the leaf nodes
        while (right != null) {
            if (isLeftNull(right) && isRightNull(right))  {
                break;
            }
            rightDeque.addLast(right.getData());
            if (!isRightNull(right)) {
                right = right.getRight();
            } else {
                right = right.getLeft();
            }
        }
        return List.of(leftDeque, rightDeque);
    }
}
