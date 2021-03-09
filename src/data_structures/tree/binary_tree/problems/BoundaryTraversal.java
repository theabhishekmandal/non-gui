package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
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
 *     4    12    25
 *         /  \
 *        10   14
 *
 * Output: 20 8 4 10 14 25 22
 *
 * Approach:
 *  -   Get the leaves as list
 *  -   Get the left and right view of the list
 *  -   combine and merge them
 */
public class BoundaryTraversal {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        IntStream.range(0, 9).forEach(tree::insertInBinaryTreeLevelOrder);
        System.out.println(tree.levelOrderPretty());
        System.out.println(getBoundaryTraversalNew(tree));
    }

    private static <T> List<T> getBoundaryTraversalNew(BinaryTree<T> tree) {
        if (tree == null || tree.getRoot() == null) {
            return Collections.emptyList();
        }
        List<T> leavesList = getLeaves(tree.getRoot());
        List<List<T>> leftRightList = getLeftAndRightView(tree.getRoot());
        return Stream.of(leftRightList.get(0), leavesList, leftRightList.get(1)).flatMap(Collection::stream).collect(Collectors.toList());
    }

    private static <T> List<T> getLeaves(Node<T> root) {
        List<T> list = new ArrayList<>();

        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(root);
        stack.push(root);

        while (!stack.isEmpty()) {
            var curr = stack.pop();
            if (!stack.isEmpty() && curr == stack.peek()) {
                if (Objects.nonNull(curr.getRight())) {
                    stack.push(curr.getRight());
                    stack.push(curr.getRight());
                }
                if (Objects.nonNull(curr.getLeft())) {
                    stack.push(curr.getLeft());
                    stack.push(curr.getLeft());
                }
            } else {
               if (Objects.isNull(curr.getLeft()) && Objects.isNull(curr.getRight())) {
                   list.add(curr.getData());
               }
            }
        }
        return list;
    }

    private static <T> List<List<T>> getLeftAndRightView(Node<T> root) {
        Node<T> left = root.getLeft();
        Node<T> right = root.getRight();

        List<T> leftList = new ArrayList<>();
        leftList.add(root.getData());

        List<T> rightList = new ArrayList<>();

        while (Objects.nonNull(left)) {
            if (Objects.nonNull(left.getLeft()) && Objects.nonNull(left.getRight())) {
                leftList.add(left.getData());
            }
            left = left.getLeft();
        }

        while (Objects.nonNull(right)) {
            if (Objects.nonNull(right.getLeft()) && Objects.nonNull(right.getRight())) {
                rightList.add(right.getData());
            }
            right = right.getRight();
        }
        Collections.reverse(rightList);
        return List.of(leftList, rightList);
    }
}
