package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

/**
 * Find the size of the tree.
 * Size of tree is given by total number of nodes present in the tree.
 * Approach:
 *  -   Use post order traversal to find the total number of nodes in tree
 */

public class FindingSizeOfTree {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Random random = new Random();
        for(int i = 0; i < random.nextInt(20); i++)
            binaryTree.insertInBinaryTreeLevelOrder(random.nextInt(100));
        int size = findSizeOfTreeRecursive(binaryTree.getRoot());
        int size2 = findSizeOfTreeIteration(binaryTree.getRoot());
        System.out.println(binaryTree.levelOrder());
        System.out.println(size);
        System.out.println(size2);
    }

    private static <T> int findSizeOfTreeRecursive(BinaryTree.Node<T> node) {
       if(node == null) return 0;
       return findSizeOfTreeRecursive(node.getLeft()) + findSizeOfTreeRecursive(node.getRight()) + 1;
    }

    private static <T> int findSizeOfTreeIteration(BinaryTree.Node<T> node){
       if(node == null) return 0;
       int size = 0;
       Deque<BinaryTree.Node<T>> stack = new LinkedList<>();
       stack.push(node);
       stack.push(node);
       while(!stack.isEmpty()){
            BinaryTree.Node<T> curr = stack.pop();
            if(!stack.isEmpty() && stack.peek() == curr){
               if(curr.getRight() != null){
                   stack.push(curr.getRight());
                   stack.push(curr.getRight());
               }
               if(curr.getLeft() != null){
                   stack.push(curr.getLeft());
                   stack.push(curr.getLeft());
               }
            }
            else{
               size++;
            }
        }
       return size;
    }
}
