package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree and a sum k, Print every path in the tree with sum of the nodes in the path as k.
 * Here a path can start from any node to other node which is connected.
 * A path can start from any node and end at any node and must be downward only, i.e.
 * they need not be root node and leaf node; and negative numbers can also be there in the tree.
 *
 * Approach:
 *  -   Do postOrder traversal and add nodes value to a list
 *  -   When leaf is encountered we have a full path from root to the leaf node.
 *  -   Now starting from leaf node value to root node value, add it to the sum and check whether it is equal to k
 *      or not, if it is equal then add it to the joiner.
 *  -   Here permutation and combination is achieved by using, traversing from backend.
 *  -   Also after reverse traversing we must remove the last index so that we can achieve necessary permutation and combination.*
 *
 *  Approach 2:
 *      -   For every path visited, store the path from root.
 *      -   calculate the sum, while traversing from the back. Reverse traversing is a must.
 *          For example: a path has [1, 2, 3], [1, 2]. If we traverse from front we will not get the combination
 *          sum of 2 and 3.
 *
 *  Eg:
 *             [1]
 *          [3, -1]
 *       [2, 1, 4, 5]
 * [*, *, 1, *, 1, 2, *, 6]
 *
 * let's say we have a path [1, -1, 5, *] and k = 5
 * Now starting from end, we see.
 * sum = 0
 * i = 2, sum += arr[i] = 0 + 5, and k == sum, so joiner = [[5]]
 * i = 1, sum += arr[i] = 5 + 1, and k != sum, so don't add
 * i = 0, sum += arr[i] = 6 - 1, and k == sum, so joiner = [[5], [-1, 1, 5]]
 *
 */
public class _38MediumPrintKSumPaths {

    public static void main(String[] args) {
        var binaryTree = new BinaryTree<Integer>();
        var root = Node.of(1);
        root.setLeft(Node.of(3));
        root.getLeft().setLeft(Node.of(2));
        root.getLeft().setRight(Node.of(1));
        root.getLeft().getRight().setLeft(Node.of(1));

        root.setRight(Node.of(-1));
        root.getRight().setLeft(Node.of(4));
        root.getRight().getLeft().setLeft(Node.of(1));
        root.getRight().getLeft().setRight(Node.of(2));
        root.getRight().setRight(Node.of(5));
        root.getRight().getRight().setRight(Node.of(6));

        binaryTree.setRoot(root);
        System.out.println(binaryTree.levelOrderPretty());

        var k = 5;
        System.out.println(getAllKSumPaths(binaryTree, k));
        System.out.println("\n\n");
        System.out.println(getAllKSumPaths2(binaryTree, k));
        System.out.println("\n\n");
        System.out.println(getAllKSumPaths3(binaryTree, k));
    }

    private static String getAllKSumPaths(BinaryTree<Integer> binaryTree, int k) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return "";
        }
        List<Integer> list = new ArrayList<>();
        Deque<Node<Integer>> stack = new ArrayDeque<>();
        stack.push(binaryTree.getRoot());
        stack.push(binaryTree.getRoot());
        var joiner = new StringJoiner("\n");
        while (!stack.isEmpty()) {
            var curr = stack.pop();
            if (!stack.isEmpty() && curr == stack.peek()) {
                list.add(curr.getData());
                if (curr.getLeft() != null) {
                    stack.push(curr.getLeft());
                    stack.push(curr.getLeft());
                }

                if (curr.getRight() != null) {
                    stack.push(curr.getRight());
                    stack.push(curr.getRight());
                }
            } else {
                calculateKSumPath(list, k, joiner);
            }
        }
        return joiner.toString();
    }

    /*
      Using inOrder traversal, and this will not work
   */
    private static String getAllKSumPaths2(BinaryTree<Integer> binaryTree, int k) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return "";
        }
        List<Integer> list = new ArrayList<>();
        Deque<Node<Integer>> stack = new ArrayDeque<>();
        var curr = binaryTree.getRoot();
        var joiner = new StringJoiner("\n");
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                list.add(curr.getData());
                stack.push(curr);
                curr = curr.getLeft();
            } else {
                curr = stack.pop();
                calculateKSumPath(list, k, joiner);
                curr = curr.getRight();
            }
        }
        return joiner.toString();
    }



    // using preorder traversal and storing the paths
    private static String getAllKSumPaths3(BinaryTree<Integer> binaryTree, int k) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return "";
        }

        Deque<NodeWrapper<Integer>> stack = new ArrayDeque<>();
        var joiner = new StringJoiner("\n");
        stack.push(new NodeWrapper<>(binaryTree.getRoot(), new ArrayList<>()));

        while (!stack.isEmpty()) {
            var curr = stack.pop();
            curr.paths().add(curr.node().getData());
            getPaths(joiner, curr.paths(), k);

            if (curr.node().getLeft() != null) {
                stack.push(new NodeWrapper<>(curr.node().getLeft(), new ArrayList<>(curr.paths())));
            }

            if (curr.node().getRight() != null) {
                stack.push(new NodeWrapper<>(curr.node().getRight(), new ArrayList<>(curr.paths())));
            }
        }
        return joiner.toString();
    }


    private static void getPaths(StringJoiner joiner, List<Integer> paths, int k) {
        int sum = 0;
        for (int i = paths.size() - 1; i >= 0; i--) {
            sum += paths.get(i);
            if (sum == k) {
                joiner.add(getString(paths, i));
            }
        }
    }



    record NodeWrapper<T>(Node<T> node, List<T> paths) {}

    /**
     * for every node value that is added into the list,
     * from the last element start adding and checking if it is equal to K,
     * if it is present then add all the elements to the joiner
     * also remove the last element from the list as it is traversed.
     */
    private static void calculateKSumPath(List<Integer> list, int k, StringJoiner joiner) {
        var sum = 0;
        for (var i = list.size() - 1; i >= 0; i--) {
            sum += list.get(i);
            if (sum == k) {
                joiner.add(getString(list, i));
            }
        }
        list.remove(list.size() - 1);
    }

    private static String getString(List<Integer> list, int i) {
        return IntStream.range(i, list.size())
                .map(list::get)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
    }
}
