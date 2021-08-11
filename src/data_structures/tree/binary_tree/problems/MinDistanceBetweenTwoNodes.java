package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree and two nodes, find the smallest distance between the two nodes
 * Approach:
 *  -   The smallest distance between two nodes can be found by, finding the LCA of two nodes and
 *      then distance = d1 + d2 where d1(is the distance from LCA to first node) and
 *      d2(is the distance from LCA to second node)
 */
public class MinDistanceBetweenTwoNodes {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        random.ints(20, 0, 20).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        var first = random.nextInt(20);
        var second = random.nextInt(20);
        System.out.println("first = " + first + " second = " + second);
        System.out.println(binaryTree.levelOrderPretty());
        System.out.println(getDistance(binaryTree, first, second));
    }

    private static int getDistance(BinaryTree<Integer> binaryTree, int first, int second) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return -1;
        }
        List<Boolean> list = Arrays.asList(false, false);
        var ancestor = getAncestor(binaryTree.getRoot(), first, second, list);
        int distance = -1;
        if (list.get(0) && list.get(1)) {
            distance = getDistanceForNode(ancestor, first, 0) + getDistanceForNode(ancestor, second, 0);
        }
        return distance;
    }

    private static int getDistanceForNode(Node<Integer> ancestor, int nodeValueToBeFound, int distance) {
        if (ancestor == null) {
            return -1;
        }
        if (ancestor.getData() == nodeValueToBeFound) {
            return distance;
        }
        int leftDistance = getDistanceForNode(ancestor.getLeft(), nodeValueToBeFound, distance + 1);
        if (leftDistance == -1) {
            return getDistanceForNode(ancestor.getRight(), nodeValueToBeFound, distance + 1);
        }
        return leftDistance;
    }


    private static Node<Integer> getAncestor(Node<Integer> root, int first, int second, List<Boolean> list) {
        if (root == null) {
            return null;
        }

        Node<Integer> temp = null;
        if (root.getData().equals(first)) {
            list.set(0, true);
            temp = root;
        }

        if (root.getData().equals(second)) {
            list.set(1, true);
            temp = root;
        }

        var left = getAncestor(root.getLeft(), first, second, list);
        var right = getAncestor(root.getRight(), first, second, list);

        if (temp != null) {
            return temp;
        }
        if (left != null && right != null) {
            return root;
        }
        return (left != null) ? left : right;
    }
}
