package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree print all the views of it.
 */

public class _1MediumAllViewOfTree {
    public static void main(String[] args) {
        final var tree = new BinaryTree<Integer>();
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
        if (root == null) {
            return Collections.emptyList();
        }
        final Node<T> nullNode = Node.of(null);
        final Deque<Node<T>> queue = new ArrayDeque<>();
        final List<T> ans = new ArrayList<>();

        queue.add(nullNode);
        queue.add(root);

        while (!queue.isEmpty()) {
            final var curr = queue.poll();

            // for every value node add the left and right childs
            if (curr != nullNode) {
                if (curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
            }
            // for every null node
            else {

                // for every null node, first element will give the leftmost child
                if (!queue.isEmpty()) {

                    Node<T> tNode = queue.peekFirst();
                    if (tNode != nullNode) {
                        ans.add(tNode.getData());
                        queue.add(nullNode);
                    }
                }
            }
        }
        return ans;
    }

    private static <T> void addToQueue(Deque<Node<T>> deque, Node<T> curr, boolean leftToRight) {
        final var left = (leftToRight) ? curr.getLeft() : curr.getRight();
        final var right = (leftToRight) ? curr.getRight() : curr.getLeft();
        if (left != null) {
            deque.add(left);
        }
        if (right != null) {
            deque.add(right);
        }
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
     *  queue
     *  [n, 1]
     *  [1] -> print 1
     *  [1, n] <- add nullNode to queue if queue contains non-null element
     *  [n, 2, 3]
     *  [2, 3] <- print 3
     *  [2, 3, n] <- add nullNode
     *  [3, n, 4, 5]
     *  [n, 4, 5, 6, 7]
     *  [4, 5, 6, 7] <- print 7
     *  [4, 5, 6, 7, n] <- add nullNode
     *  [5, 6, 7, n, 8]
     *  [6, 7, n, 8]
     *  [7, n, 8]
     *  [n, 8]
     *  [8] -> print 8
     *  [n]
     */
    private static <T> List<T> getRightView(Node<T> root) {
        if (root == null) {
            return Collections.emptyList();
        }

        final Node<T> nullNode = Node.of(null);
        final Deque<Node<T>> queue = new ArrayDeque<>();
        final List<T> ans = new ArrayList<>();
        queue.add(nullNode);
        queue.add(root);

        while (!queue.isEmpty()) {
            final var curr = queue.poll();

            // for every value node add the left and right childs
            if (curr != nullNode) {
                if (curr.getLeft() != null) {
                    queue.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    queue.add(curr.getRight());
                }
            }
            // for every null node
            else {

                // for every null node, last element will give the rightMost child
                if (!queue.isEmpty()) {

                    Node<T> tNode = queue.peekLast();
                    if (tNode != nullNode) {
                        ans.add(tNode.getData());
                        queue.add(nullNode);
                    }
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
        if (root == null) {
            return Collections.emptyList();
        }

        final var queue = new ArrayDeque<Container<T>>();
        queue.add(Container.of(root, 0, 0));

        final var map = new HashMap<Integer, Container<T>>();
        while (!queue.isEmpty()) {
            final var curr = queue.poll();

            // put only those values which you can see from the top view of tree
            map.putIfAbsent(curr.distanceFromRoot, Container.of(curr.node, curr.level, curr.distanceFromRoot));

            if (curr.node.getLeft() != null) {
                queue.add(Container.of(curr.node.getLeft(), curr.level + 1, curr.distanceFromRoot + 1));
            }
            if (curr.node.getRight() != null) {
                queue.add(Container.of(curr.node.getRight(), curr.level + 1, curr.distanceFromRoot - 1));
            }
        }
        final Comparator<Container<T>> comp = Comparator.comparing(Container<T>::getDistanceFromRoot, Comparator.reverseOrder())
                .thenComparing(Container::getLevel);

        return map.values()
                .stream()
                .sorted(comp)
                .map(x -> x.getNode().getData())
                .collect(Collectors.toList());
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
        if (root == null) {
            return Collections.emptyList();
        }

        final var queue = new ArrayDeque<Container<T>>();
        queue.add(Container.of(root, 0, 0));

        final var map = new HashMap<Integer, Container<T>>();
        while (!queue.isEmpty()) {
            final var curr = queue.poll();

            map.put(curr.distanceFromRoot, Container.of(curr.node, curr.level, curr.distanceFromRoot));

            if (curr.node.getLeft() != null) {
                queue.add(Container.of(curr.node.getLeft(), curr.level + 1, curr.distanceFromRoot - 1));
            }
            if (curr.node.getRight() != null) {
                queue.add(Container.of(curr.node.getRight(), curr.level + 1, curr.distanceFromRoot + 1));
            }
        }
        final Comparator<Container<T>> comp = Comparator.comparing(Container<T>::getDistanceFromRoot)
                .thenComparing(Container::getLevel);

        return map.values()
                .stream()
                .sorted(comp)
                .map(x -> x.getNode().getData())
                .collect(Collectors.toList());
    }

    static class Container<T> {
        private final Node<T> node;
        private final int level;
        private final int distanceFromRoot;

        private Container(Node<T> node, int level, int distanceFromRoot) {
            this.node = node;
            this.level = level;
            this.distanceFromRoot = distanceFromRoot;
        }

        public static <T> Container<T> of(Node<T> node, int level, int distanceFromRoot) {
            return new Container<>(node, level, distanceFromRoot);
        }

        public Node<T> getNode() {
            return node;
        }

        public int getLevel() {
            return level;
        }

        public int getDistanceFromRoot() {
            return distanceFromRoot;
        }
    }
}
