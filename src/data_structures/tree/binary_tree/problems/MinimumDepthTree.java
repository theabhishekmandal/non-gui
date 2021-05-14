package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree, find it's minimum depth
 * The minimum depth is the number of nodes along the shortest path from root node down to the nearest leaf node
 *
 * Approach:
 *  Using PostOrder Approach:
 *      Using postOrder traversal, increase the counter for every node found.
 *      Now when leaf node is reached, start decreasing the counter. Also, before decreasing the counter
 *      check if the current node is the leaf node or not
 *          - if it is leaf node then take the minimum otherwise do nothing
 *
 * Using LevelOrder Approach:
 *      Traverse each level, increase the counter and check whether the current node is leaf node, if it is leaf node
 *      then return the counter
 */

public class MinimumDepthTree {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);

        // this is little bit faster
        int minimumDepthLevelOrder = minimumDepthLevelOrder((binaryTree.getRoot()));

        // this is slower than previous one
        int minimumDepthPostOrder = minimumDepth(binaryTree.getRoot());

        System.out.println(binaryTree.levelOrder());
        System.out.println(minimumDepthPostOrder);
        System.out.println(minimumDepthLevelOrder);
    }

    private static <T> int minimumDepth(Node<T> node){
        if(node == null) return 0;

        int minDepth = Integer.MAX_VALUE;
        int depthCounter = 0;

        Deque<Node<T>> stack = new LinkedList<>();
        stack.push(node);
        stack.push(node);

        while(!stack.isEmpty()){
            Node<T> curr = stack.pop();
            if(!stack.isEmpty() && curr == stack.peek()){
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
                if(curr.getLeft() == null && curr.getRight() == null){
                    minDepth = Math.min(minDepth, depthCounter);
                }
                depthCounter--;
            }
        }
        return minDepth;
    }

    private static <T> int minimumDepthLevelOrder(Node<T> node){
        if(node == null) return 0;

        int minDepth = 1;

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(node);
        queue.add(null);

        while(!queue.isEmpty()){
            Node<T> curr = queue.poll();
            if(curr != null){
                Node<T> left = curr.getLeft();
                Node<T> right = curr.getRight();
                if(right == null && left == null){
                    return minDepth;
                }
                if(left != null){
                    queue.add(left);
                }
                if(right != null){
                    queue.add(right);
                }
            }
            else{
                if(!queue.isEmpty()){
                    /*
                        Increase counter only when there are more nodes in the queue. because, suppose
                        if there is only one node in the tree, then it will return 1
                     */
                    minDepth++;
                    queue.add(null);
                }
            }
        }
        return minDepth;
    }
}
