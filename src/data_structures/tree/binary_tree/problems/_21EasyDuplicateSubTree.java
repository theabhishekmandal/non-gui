package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.HashMap;
import java.util.Map;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree find out if it contains a duplicate subtree of size two or more.
 * Input :
 *                1
 *              /   \
 *            2       3
 *          /   \       \
 *         4     5       2
 *                      /  \
 *                     4    5
 *
 * Output : 1
 *
 * here [2, 4, 5] is repeated two times
 *
 * Approach:
 *  -   First traverse the tree using recursion
 *  -   While traversing we will denote null node as $ which will be added to the string because.
 *
 *                1
 *              /   \
 *            2       3
 *          /   \       \
 *         4     5       2
 *                        \
 *                         4
 *                          \
 *                           5
 *      we will have 245 from left side and 245 from right side which is incorrect, they are not equal subtree
 *      And if we replace null node with $ then we will get 24$$5$$ from left side and 2$4$5$$ from right side and
 *      both are not equal
 *
 *  -   Also we are not storing strings which are leaf nodes because we want size greater than 1.
 *  -   Add the strings in the map with there count and later find if there is any entry<key, value> which
 *      has count greater than 1
 */

public class _21EasyDuplicateSubTree {
    public static void main(String[] args) {
        Node<Integer> root = Node.of(1);

        root.setLeft(Node.of(2));
        root.getLeft().setLeft(Node.of(4));
        root.getLeft().setRight(Node.of(5));

        root.setRight(Node.of(3));
        root.getRight().setRight(Node.of(2));
        root.getRight().getRight().setLeft(Node.of(4));
        root.getRight().getRight().setRight(Node.of(5));


        var binaryTree = new BinaryTree<Integer>();
        binaryTree.setRoot(root);
        System.out.println(binaryTree.levelOrderPretty());
        System.out.println(findDuplicateSubTree(binaryTree));

    }

    private static boolean findDuplicateSubTree(BinaryTree<Integer> binaryTree) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return false;
        }
        Map<String, Integer> map = new HashMap<>();
        findDup(binaryTree.getRoot(), map);
        return map.entrySet().stream().anyMatch(x -> x.getValue() > 1);
    }

    private static String findDup(Node<Integer> root, Map<String, Integer> map) {
        var end = "$";
        if (root == null) {
            return end;
        }

        // returning because won't store leaves in map, as subTree size should be greater than 1
        if (root.getLeft() == null && root.getRight() == null) {
            return root.getData() + end + end;
        }
        var left = findDup(root.getLeft(), map);
        var right = findDup(root.getRight(), map);
        var s = root.getData() + left + right;
        map.merge(s, 1, Integer::sum);
        return s;
    }

}
