package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree print all the views of it.
 */

public class AllViewOfTree {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        IntStream.range(0, 9).forEach(tree::insertInBinaryTreeLevelOrder);
        System.out.println(tree.levelOrderPretty());
        System.out.println("leftView List is " + getLeftView(tree.getRoot()));
        System.out.println("rightView List is " + getRightView(tree.getRoot()));
        System.out.println("topView List is " + getTopView(tree.getRoot()));
        System.out.println("bottomView List is " + getBottomView(tree.getRoot()));
    }

    /**
     * Left view of tree, is the nodes that are visible on the tree when viewed from left side.
     * For example
     * Left view of following tree is 1 2 4 8.
     *
     *          1
     *        /   \
     *      2      3
     *    /   \   /  \
     *   4     5 6    7
     *    \
     *     8
     *
     *  Approach: Using level order traversal, the first node that is added to queue, is one of the left node and
     *  will be added to final list
     */
    private static <T> List<T> getLeftView(Node<T> root) {
        if (root == null) return Collections.emptyList();
        Deque<Node<T>> queue = new LinkedList<>();
        List<T> ans = new ArrayList<>();

        boolean leftView = true;
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            var curr = queue.poll();
            if (curr != null) {
                if (leftView) {
                    leftView = false;
                    ans.add(curr.getData());
                }
                if (curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
            } else {
                if (!queue.isEmpty()) {
                    leftView = true;
                    queue.add(null);
                }
            }
        }
        return ans;
    }

    /**
     * Right view of tree, is the nodes that are visible on the tree when viewed from right side.
     * For example
     * Right view of following tree is, 1 3 7 8
     *
     *          1
     *        /   \
     *      2      3
     *    /   \   /  \
     *   4     5 6    7
     *    \
     *     8
     *
     *  Approach: Using reverse level order traversal, the first node that is added to queue, is one of the right node and
     *  will be added to final list
     */
    private static <T> List<T> getRightView(Node<T> root) {
        if (root == null) return Collections.emptyList();
        Deque<Node<T>> queue = new LinkedList<>();
        List<T> ans = new ArrayList<>();

        boolean rightView = true;
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            var curr = queue.poll();
            if (curr != null) {
                if (rightView) {
                    rightView = false;
                    ans.add(curr.getData());
                }
                if (curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
                if (curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
            } else {
                if (!queue.isEmpty()) {
                    rightView = true;
                    queue.add(null);
                }
            }
        }
        return ans;
    }

    /**
     * Top view of tree, is the nodes that are visible on the tree when viewed from top side.
     * For example
     * Top view of following tree is, 4, 2, 1, 3, 7
     *
     *          1
     *        /   \
     *      2      3
     *    /   \   /  \
     *   4     5 6    7
     *    \
     *     8
     *
     * Approach:
     *  -   use level order traversal and two variables level and distanceFromRoot
     *  -   level is the level of each node and distanceFromRoot is given as for
     *      leftNode = distanceFromRoot - 1 and
     *      rightNode = distanceFromRoot + 1
     *  -   Idea is add the node to the map if not already present on the basis of distanceFromRoot variable
     *  -   so, in the above example 1 has distanceFromRoot=0 and 5 and 6 have also distanceFromRoot=0, but 5 and
     *      6 will not be added as distanceFromRoot=0 as key is present which is having value=1
     */

    private static <T> List<T> getTopView(Node<T> root) {
        if (root == null) return Collections.emptyList();

        var queue = new LinkedList<Pair<Node<T>, Pair<Integer, Integer>>>();
        queue.add(Pair.of(root, Pair.of(0, 0)));

        var map = new HashMap<Integer, Pair<T, Pair<Integer, Integer>>>();

        while (!queue.isEmpty()) {
            var curr = queue.poll();
            var currNode = curr.fst;
            var distanceFromRoot = curr.snd.fst;
            var level = curr.snd.snd;

            // put only those values which you can see from the top view of tree
            map.putIfAbsent(distanceFromRoot, Pair.of(currNode.getData(), Pair.of(distanceFromRoot, level)));

            if (currNode.getLeft() != null) {
                queue.add(Pair.of(currNode.getLeft(), Pair.of(distanceFromRoot - 1, level + 1)));
            }
            if (currNode.getRight() != null) {
                queue.add(Pair.of(currNode.getRight(), Pair.of(distanceFromRoot + 1, level + 1)));
            }
        }
        Comparator<Pair<T, Pair<Integer, Integer>>> pair = (x, y) -> {
            int first = y.snd.fst.compareTo(x.snd.fst);
            if (first != 0) {
                return -first;
            }
            return x.snd.snd.compareTo(y.snd.snd);
        };
        return map.values().stream().sorted(pair).map(x -> x.fst).collect(Collectors.toList());
    }

    /**
     * Bottom view of tree, is the nodes that are visible on the tree when viewed from bottom side.
     * For example
     * Bottom view of following tree is, 4, 2, 1, 3, 7
     *
     *          1
     *        /   \
     *      2      3
     *    /   \   /  \
     *   4     5 6    7
     *    \
     *     8
     *
     * Approach:
     *  -   Same as Top view, only difference is here we put the value in the map to overwrite the old value
     *      there by getting the bottom nodes.
     */
    private static <T> List<T> getBottomView(Node<T> root) {
        if (root == null) return Collections.emptyList();

        var queue = new LinkedList<Pair<Node<T>, Pair<Integer, Integer>>>();
        queue.add(Pair.of(root, Pair.of(0, 0)));

        var map = new HashMap<Integer, Pair<T, Pair<Integer, Integer>>>();
        while (!queue.isEmpty()) {
            var curr = queue.poll();
            var currNode = curr.fst;
            var distanceFromRoot = curr.snd.fst;
            var level = curr.snd.snd;

            map.put(distanceFromRoot, Pair.of(currNode.getData(), Pair.of(distanceFromRoot, level)));

            if (currNode.getLeft() != null) {
                queue.add(Pair.of(currNode.getLeft(), Pair.of(distanceFromRoot - 1, level + 1)));
            }
            if (currNode.getRight() != null) {
                queue.add(Pair.of(currNode.getRight(), Pair.of(distanceFromRoot + 1, level + 1)));
            }
        }
        Comparator<Pair<T, Pair<Integer, Integer>>> pair = (x, y) -> {
            int first = y.snd.fst.compareTo(x.snd.fst);
            if (first != 0) {
                return -first;
            }
            return x.snd.snd.compareTo(y.snd.snd);
        };
        return map.values().stream().sorted(pair).map(x -> x.fst).collect(Collectors.toList());
    }
}
