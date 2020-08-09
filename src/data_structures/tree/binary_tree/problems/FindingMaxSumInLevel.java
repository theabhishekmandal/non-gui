package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Find the maximum sum in a given level in a tree
 */
public class FindingMaxSumInLevel {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder());
        System.out.println("max sum in the level is=" + maxSumInLevel(binaryTree.getRoot()));
    }

    private static int maxSumInLevel(Node<Integer> root){
        if(root == null)
            return 0;
        int maxSum = 0;
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        int sum = 0;
        while(!queue.isEmpty()){
            Node<Integer> curr = queue.poll();
            if(curr != null){
               sum += curr.getData();
               if(curr.getLeft() != null)
                   queue.add(curr.getLeft());
               if(curr.getRight() != null)
                   queue.add(curr.getRight());
            }
            else{
                maxSum = Math.max(maxSum, sum);
                sum = 0;
                if(!queue.isEmpty())
                    queue.add(null);
            }
        }
        return maxSum;
    }
}
