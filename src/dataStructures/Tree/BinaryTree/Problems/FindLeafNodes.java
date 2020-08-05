package dataStructures.Tree.BinaryTree.Problems;

import dataStructures.Tree.BinaryTree.BinaryTreeImpl.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.IntStream;

import static dataStructures.Tree.BinaryTree.BinaryTreeImpl.BinaryTree.node;

/**
 * Find all the leaf nodes in the tree
 * Approach
 *  -   To find a leaf node traverse in level order manner and count the nodes if the current node
 *      does not have the left and right child.
 */

public class FindLeafNodes {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder());
        Object[] output = numberOfLeafNodes(binaryTree.getRoot());
        System.out.println( "total leaf nodes are " + output[0]);
        System.out.println( "leaf nodes are " + output[1]);
    }

    private static <T> Object[] numberOfLeafNodes(node<T> root) {
        if(root == null) return new Object[]{0, new StringBuilder("[]")};
        StringBuilder br = null;
        Queue<node<T>> queue = new LinkedList<>();
        queue.add(root);

        int leafNodes = 0;
        while(!queue.isEmpty()){
            node<T> curr = queue.poll();
            if(curr.getLeft() == null && curr.getRight() == null){
                br = addLeafNodesValue(br, curr.getData());
                leafNodes++;
            }
            if(curr.getLeft() != null)
                queue.add(curr.getLeft());
            if(curr.getRight() != null)
                queue.add(curr.getRight());
        }
        if(br == null)
            br = new StringBuilder("[]");
        else
            br.append("]");
        return new Object[]{leafNodes, br};
    }

    private static <T> StringBuilder addLeafNodesValue(StringBuilder br, T data){
       if(br == null)
           br = new StringBuilder("[");
       else
           br.append(", ");
       br.append(data);
       return br;
    }
}
