package DataStructures.Tree.BinaryTree.Problems;

import DataStructures.Tree.BinaryTree.TreeImpl.BinaryTree;
import static DataStructures.Tree.BinaryTree.TreeImpl.BinaryTree.node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Find the deepest node in the tree.
 * Approach:
 *      Traverse in level order and whichever is the last node will be the deepest node in the tree
 */
public class DeepestNodeInTree {
    public static void main(String[] args) {
       Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder());
        System.out.println(getDeepestNodeValue(binaryTree.getRoot()));
    }

    private static <T> T getDeepestNodeValue(node<T> root) {
        if(root == null)
            return null;
        Queue<node<T>> queue = new LinkedList<>();
        queue.add(root);
        node<T> curr = null;
        while(!queue.isEmpty()){
            curr = queue.poll();
            if(curr.getLeft()  != null)
                queue.add(curr.getLeft());
            if(curr.getRight() != null)
                queue.add(curr.getRight());
        }
        return (curr != null) ? curr.getData() : null;
    }
}
