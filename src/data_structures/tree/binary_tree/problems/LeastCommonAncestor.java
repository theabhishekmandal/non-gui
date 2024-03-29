package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import utility.StopWatch;

import java.util.Random;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Give an algorithm for find LCA(Least Common Ancestor) of two nodes in a Binary Tree
 * Using recursion in O(n) is the faster way
 * Approach
 *      -   Traverse the tree using post order recursion, we will set v1 or v2 if first or second matches the
 *          root
 *          -   In bottom up approach, if a node is matching one of the first and second values then return it,
 *              as we got our value we don't need to search for other value in the subtree
 *          -   If both left and right node that we got from recursion method is not null then we return the parent node
 *              for both left and right node.
 *          -   If one of the left and right are absent then return the present node
 *  TestCases
 *     -   7   - if both null and value does not match then return null
 *        /  \
 *     null  null
 *     -   7   - if value matches then return value node
 *        /  \
 *     null  null
 *     -   7   - if value does not match and both the nodes are not null then return parent node i.e 7
 *        /  \
 *       8   9
 *
 */
public class LeastCommonAncestor {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        int randomMaxElement = 20;
        random.ints(20, 0, randomMaxElement).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        var first = random.nextInt(randomMaxElement);
        var second = random.nextInt(randomMaxElement);
        System.out.println(binaryTree.levelOrder() + "\nfirst value = " + first + "\nsecond value = " + second);

        var stopWatch = new StopWatch();
        var outputFormat = "time taken = %d output = %s";

        stopWatch.startTime();
        var res1 = getLeastCommonAncestor(binaryTree, first, second);
        stopWatch.stopTime();
        System.out.printf((outputFormat) + "%n", stopWatch.getTimeInMillis(), res1);
    }


    private static boolean v1;
    private static boolean v2;

    private static Integer getLeastCommonAncestor(BinaryTree<Integer> binaryTree, int first, int second) {
        if (binaryTree == null || binaryTree.getRoot() == null) {
            return null;
        }
        v1 = false;
        v2 = false;
        Node<Integer> node = getAncestor(binaryTree.getRoot(), first, second);
        if (v1 && v2) {
            return node.getData();
        }
        return null;
    }

    // use this to find the least common ancestor
    private static Node<Integer> getAncestor(Node<Integer> root, int first, int second) {
        if (root == null) {
            return null;
        }

        Node<Integer> temp = null;
        // if root matches with first and second then we assign it to temp
        // use of v1 and v2 is that, if both of the values are set then we will return the answer
        // it is used because it assumes that first and second is already present in the tree
        // suppose if first is not present then it will return root, but we should return null instead

        // Also, value of temp will change according to first and second.
        // we don't return after assigning temp = root because, suppose if first=5 and second=6
        // and 5 is parent of 6, then we have to traverse 6 also, we can't return if we found first as we have
        // to set the flag for 5 also.

        if (root.getData() == first) {
            v1 = true;
            temp = root;
        }
        if (root.getData() == second) {
            v2 = true;
            temp = root;
        }
        var left = getAncestor(root.getLeft(), first, second);
        var right = getAncestor(root.getRight(), first, second);

        // if the current Node is first or second then return after setting the flag above
        if (temp != null) {
            return temp;
        }
        // if both are not null, this means one key is present in one subTree and one is present in the other subTree
        if (left != null && right != null) {
            return root;
        } else {
            return (left != null) ? left : right;
        }
    }
}
