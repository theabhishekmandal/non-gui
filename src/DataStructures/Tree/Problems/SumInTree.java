package DataStructures.Tree.Problems;

import DataStructures.Tree.TreeImpl.BinaryTree;
import static DataStructures.Tree.TreeImpl.BinaryTree.node;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

/**
 * Find the sum of all nodes in the tree
 * Approach
 *  -   Use any type of traversal and add each node value.
 */
public class SumInTree {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        for(int i  = 0; i < random.nextInt(20); i++){
            binaryTree.insertInBinaryTreeLevelOrder(random.nextInt(20));
        }
        System.out.println(binaryTree.levelOrder());
        int sum = getSum(binaryTree.getRoot());
        System.out.println("sum of tree is " + sum);
    }
    private static int getSum(node<Integer> node){
        if(node == null) return 0;
        Deque<node<Integer>> stack = new LinkedList<>();
        int sum = 0;
        node<Integer> curr = node;
        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.getLeft();
            }
            else{
                curr = stack.pop();
                sum += curr.getData();
                curr = curr.getRight();
            }
        }
        return sum;
    }
}
