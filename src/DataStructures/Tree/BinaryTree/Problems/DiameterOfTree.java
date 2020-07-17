package DataStructures.Tree.BinaryTree.Problems;

import DataStructures.Tree.BinaryTree.BinaryTreeImpl.BinaryTree;

import java.util.*;
import java.util.stream.IntStream;

/**
 *
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * Note: The length of path between two nodes is represented by the number of edges between them.
 *
 * Approach:
 *  -   Using post order traversal, at any given time, we will have left child, right child and parent.
 *  -   Now, there can be n number of left, right and parent, so take the maximum value out of these in a global
 *      variable and then return the result - 1(because we want to calculate edges not nodes)
 *  -   Note that, initial value of the global variable should start with 1 because, if tree has only 1 node
 *      then number of edges will be 0. Also, this condition is also satisfied by empty root value.
 * */

public class DiameterOfTree {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Random random = new Random();
        IntStream.range(0, random.nextInt(20))
                .forEach(x -> binaryTree.insertInBinaryTreeLevelOrder(random.nextInt(100)));
        try{
            int diameterOfTreeRecursion = findDiameterOfTreeRecursion(binaryTree.getRoot());
            int diameterOfTreeIteration = findDiameterOfTreeIteration(binaryTree.getRoot());

            System.out.println(binaryTree.levelOrder());
            System.out.println(diameterOfTreeRecursion);
            System.out.println(diameterOfTreeIteration);
        }
        catch (Exception e){
            System.err.println("In exception");
            System.out.println(binaryTree.levelOrder());
            e.printStackTrace();
        }
    }

    private static int value;
    private static int findDiameterOfTreeRecursion(BinaryTree.node<Integer> root) {
        value = 1;
        find(root);
        return value - 1;
    }

    private static <T> int  find(BinaryTree.node<T> node) {
        if(node == null)
            return 0;
        int left = find(node.getLeft());
        int right = find(node.getRight());

        // adding the deepest left and right node from each current node;
        value = Math.max(value, left + right + 1);

        // to find the deepest value take the max between left and right
        return Math.max(left, right) + 1;
    }

    private static <T> int findDiameterOfTreeIteration(BinaryTree.node<T> root){
        if(root == null || (root.getLeft() == null && root.getRight() == null))
            return 0;
        int maxValue = 0;
        Map<BinaryTree.node<T>, Integer> map = new HashMap<>();
        Deque<BinaryTree.node<T>> stack = new LinkedList<>();
        stack.push(root);
        stack.push(root);
        while(!stack.isEmpty()){
            BinaryTree.node<T> curr = stack.pop();
            if(!stack.isEmpty() && curr == stack.peek()){
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
                Integer left, right;
                left = ((left = map.get(curr.getLeft())) == null)? 0 : left;
                right = ((right = map.get(curr.getRight())) == null)? 0 : right;

                // adding the deepest left and right node from each current node;
                maxValue = Math.max(maxValue, left + right + 1);

                // to find the deepest value take the max between left and right
                map.put(curr, Math.max(left, right) + 1);
            }
        }
        return maxValue - 1;
    }
}
