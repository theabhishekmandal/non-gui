package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree with a value associated with each node, we need to choose a subset of these nodes such that
 * the sum of chosen nodes is maximum under a constraint that no two chosen nodes in the subset should be directly connected that is,
 * if we have taken a node in our sum then we can’t take any of its children in consideration and vice versa.
 *
 * Eg:
 *              1
 *            /  \
 *           2    3
 *          /    / \
 *         1    4   5
 *
 *   maximum sum is calculated as 2 + 4 + 5 = 11
 *
 * Approach:
 *  -   This is an example of dynamic programming, as we are not sure what can be the maximum sum if we pick one
 *      node and reject the other one. So we have to take both scenario i.e calculating sum by including the node and
 *      again calculating sum by excluding the node and then calculating the max sum between both of them.
 *  -   As we are using dp, we have to compute and store the maxSum of each node in the map.
 */
public class _30MediumLargestSumWithoutAdjacentNodes {

    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        random.ints(10, 1, 10).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrderPretty());
        System.out.println("largest sum in subTree = " + findLargestSubTreeSumWithoutAdjacentNodes(binaryTree));
    }

    private static int findLargestSubTreeSumWithoutAdjacentNodes(BinaryTree<Integer> binaryTree) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return -1;
        }
        Map<Node<Integer>, Integer> maxSumStore = new HashMap<>();
        return getMaxSum(binaryTree.getRoot(), maxSumStore);
    }

    private static int getMaxSum(Node<Integer> root, Map<Node<Integer>, Integer> maxSumStore) {
        if (root == null) {
            return 0;
        }
        if (maxSumStore.get(root) != null) {
            return maxSumStore.get(root);
        }
        int inc = root.getData();
        var left = root.getLeft();
        var right = root.getRight();

        /*
          here we are taking root and left child's children, so they are not directly connected
         */
        if (left != null) {
            inc += getMaxSum(left.getLeft(), maxSumStore) + getMaxSum(left.getRight(), maxSumStore);
        }

        /*
          here we are taking root and right child's children, so they are not directly connected.
         */
        if (right != null) {
            inc += getMaxSum(right.getLeft(), maxSumStore) + getMaxSum(right.getRight(), maxSumStore);
        }

        // here we are considering only the children only and not the root. excluding the root
        var exe = getMaxSum(left, maxSumStore) + getMaxSum(right, maxSumStore);

        maxSumStore.put(root, Math.max(inc, exe));
        return maxSumStore.get(root);
    }
}
