package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import utility.Pair;

import java.util.*;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Find all the Full nodes in the tree(nodes that have both left and right child are full nodes)
 * Approach
 *  -   To find a full node traverse in level order manner and count the nodes if the current node
 *      has both left and right child
 */
public class FindFullNodes {
    public static void main(String[] args) {
        var random = new Random();
        var binaryTree = new BinaryTree<Integer>();
        random.ints(20, 0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder());
        var output = numberOfLeafNodes(binaryTree.getRoot());
        System.out.println("total full nodes are " + output.getFirst());
        System.out.println("full nodes are " + output.getSecond());
    }

    private static <T> Pair<Integer, String> numberOfLeafNodes(Node<T> root) {
        if (root == null) {
            return Pair.of(0, "[]");
        }

        var joiner = new StringJoiner(",", "[", "]");
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(root);

        var fullNodes = 0;
        while (!queue.isEmpty()) {
            Node<T> curr = queue.poll();
            if (curr.getLeft() != null && curr.getRight() != null) {
                joiner.add(String.valueOf(curr.getData()));
                fullNodes++;
            }
            if (curr.getLeft() != null)
                queue.add(curr.getLeft());
            if (curr.getRight() != null)
                queue.add(curr.getRight());
        }
        return Pair.of(fullNodes, joiner.toString());
    }

}
