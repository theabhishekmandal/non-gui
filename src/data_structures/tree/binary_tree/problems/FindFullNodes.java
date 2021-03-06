package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.IntStream;

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
        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder());
        var output = numberOfLeafNodes(binaryTree.getRoot());
        System.out.println("total full nodes are " + output[0]);
        System.out.println("full nodes are " + output[1]);
    }

    private static <T> Object[] numberOfLeafNodes(Node<T> root) {
        if (root == null) {
            return new Object[]{0, new StringBuilder("[]")};
        }

        StringBuilder br = null;
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        var fullNodes = 0;
        while (!queue.isEmpty()) {
            Node<T> curr = queue.poll();
            if (curr.getLeft() != null && curr.getRight() != null) {
                br = addFullNodesValue(br, curr.getData());
                fullNodes++;
            }
            if (curr.getLeft() != null)
                queue.add(curr.getLeft());
            if (curr.getRight() != null)
                queue.add(curr.getRight());
        }
        if (br == null) {
            br = new StringBuilder("[]");
        } else {
            br.append("]");
        }
        return new Object[]{fullNodes, br};
    }

    private static <T> StringBuilder addFullNodesValue(StringBuilder br, T data) {
        if (br == null) {
            br = new StringBuilder("[");
        } else {
            br.append(", ");
        }
        br.append(data);
        return br;
    }
}
