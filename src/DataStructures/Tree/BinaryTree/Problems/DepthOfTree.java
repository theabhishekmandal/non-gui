package DataStructures.Tree.BinaryTree.Problems;

import DataStructures.Tree.BinaryTree.TreeImpl.BinaryTree;
import static DataStructures.Tree.BinaryTree.TreeImpl.BinaryTree.node;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Find the depth of the binary tree.
 * Depth of binary tree is given by, the deepest node from root to that node.
 *
 * Approach:
 *  -   Using post order and level order traversal we can find the depth of tree.
 *  -   Recursion solution is simple
 *      -   Use recursion in post order manner and find the max between left child and right child
 *          and increase the value by 1
 *
 *  -   In iterative post order traversal, increase the counter when left child or right child is added,
 *      otherwise take the max between current value and max_value and decrease the counter by 1
 *
 *  -   In iterative level order traversal, increase counter at every level
 */
public class DepthOfTree {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Random random = new Random();
        for(int i = 0; i < random.nextInt(20); i++){
            binaryTree.insertInBinaryTreeLevelOrder(random.nextInt(100));
        }
        System.out.println(binaryTree.levelOrder());
        int depthOfTreeRecursion = depthOfTreeRecursion(binaryTree.getRoot());
        int depthOfTreeIteration = depthOfTreeIteration(binaryTree.getRoot());
        int depthOfTreeLevelOrder = depthOfTreeLevelOrder(binaryTree.getRoot());
        System.out.println("depth using recursion " + depthOfTreeRecursion +
                "\ndepth using iteration " + depthOfTreeIteration +
                "\ndepth using levelOrder " + depthOfTreeLevelOrder);
    }

    private static <T> int depthOfTreeRecursion(node<T> node){
       if(node == null) return 0;
       int leftDepth = depthOfTreeRecursion(node.getLeft());
       int rightDepth = depthOfTreeRecursion(node.getRight());
       return Math.max(leftDepth, rightDepth) + 1;
    }

    private static <T> int depthOfTreeIteration(node<T> node){
        if(node == null) return 0;

        int depthCounter = 0;

        Deque<node<T>> stack = new LinkedList<>();
        stack.push(node);
        stack.push(node);

        int depth = 0;
        while(!stack.isEmpty()){
            node<T> curr = stack.pop();
            if(!stack.isEmpty() && stack.peek() == curr){
                if(curr.getRight() != null){
                    stack.push(curr.getRight());
                    stack.push(curr.getRight());
                }
                if(curr.getLeft() != null){
                    stack.push(curr.getLeft());
                    stack.push(curr.getLeft());
                }
                depthCounter++;
            }
            else{
                depth = Math.max(depth, depthCounter);
                depthCounter--;
            }
        }
        return depth;
    }

    private static <T> int depthOfTreeLevelOrder(node<T> node){
       if(node == null) return 0;

       int counter = 0;
       Queue<node<T>> queue = new LinkedList<>();
       queue.add(node);
       queue.add(null);

       while(!queue.isEmpty()){
          node<T> curr = queue.poll();
          if(curr != null){
              if(curr.getLeft() != null)
                  queue.add(curr.getLeft());
              if(curr.getRight() != null)
                  queue.add(curr.getRight());
          }
          else{
              counter++;
              if(!queue.isEmpty())
                  queue.add(null);
          }
       }
       return counter;
    }
}
