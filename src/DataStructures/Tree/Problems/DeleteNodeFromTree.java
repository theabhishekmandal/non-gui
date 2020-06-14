package DataStructures.Tree.Problems;

import DataStructures.Tree.TreeImpl.BinaryTree;
import static DataStructures.Tree.TreeImpl.BinaryTree.node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Delete a given node from the tree.
 *
 * Approach:
 *      -   To delete a node from the tree, first find that node and the last node using level order traversal
 *      -   After, that using level order traversal find the parent of the last node and then set the parent child as
 *          null
 */
public class DeleteNodeFromTree {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        int randomValue = random.nextInt(20);
        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println("before deletion " + binaryTree.levelOrder());
        System.out.println("value=" + randomValue);
        boolean isDeleted = deleteNodeFromTree(binaryTree.getRoot(), randomValue);
        if(isDeleted){
            System.out.println("after deletion " + binaryTree.levelOrder());
        }
        else{
            System.out.println("value=" + randomValue + " not found");
        }
    }

    private static <T> boolean deleteNodeFromTree(node<T> node, T data){
        if(node == null) return false;
        Queue<node<T>> queue = new LinkedList<>();
        queue.add(node);

        node<T> nodeToBeDeleted = null;
        node<T> lastNode = null;
        while(!queue.isEmpty()){
            lastNode = queue.poll();
            if(lastNode.getData().equals(data)){
                nodeToBeDeleted = lastNode;
            }
            if(lastNode.getLeft() != null)
                queue.add(lastNode.getLeft());
            if(lastNode.getRight() != null)
                queue.add(lastNode.getRight());
        }
        if(nodeToBeDeleted != null){
           nodeToBeDeleted.setData(lastNode.getData());
           deleteLastNode(node, lastNode);
           return true;
       }
       return false;
    }

    private static <T> void deleteLastNode(node<T> node, node<T> lastNode) {
        Queue<node<T>> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            node<T> curr = queue.poll();
            // set to null if parent's left or right child
            if(curr.getLeft() == lastNode){
                curr.setLeft(null);
            }
            else if(curr.getRight() == lastNode){
                curr.setRight(null);
            }
            else{
                if(curr.getLeft() != null)
                    queue.add(curr.getLeft());
                if(curr.getRight() != null)
                    queue.add(curr.getRight());
            }
        }
    }
}
