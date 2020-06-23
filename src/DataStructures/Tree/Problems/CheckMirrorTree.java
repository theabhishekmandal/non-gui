package DataStructures.Tree.Problems;

import DataStructures.Tree.TreeImpl.BinaryTree;
import static DataStructures.Tree.TreeImpl.BinaryTree.node;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

/**
 * Given two binary Trees check whether that both of them are mirror images of each other.
 * Approach
 *     -    check whether the two trees are null, then return true
 *     -    check whether one of them is null, then return false
 *     -    now using C,L,R(preOrder) in first tree and C,R,L(preOrder) in the second tree, check whether
 *          each node is equal to another
 *     -    at last check whether their size are same or not because, while traversing there can be a possibility
 *          that one of the node is null, then it will exit out of the loop
 */
public class CheckMirrorTree {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        BinaryTree<Integer> binaryTree1 = new BinaryTree<>();
        for(int i = 0; i < random.nextInt(20); i++){
            int num = random.nextInt(20);
            binaryTree.insertInBinaryTreeLevelOrder(num);
            binaryTree1.insertInBinaryTreeLevelOrder(num);
        }
        if(random.nextBoolean()){
           createMirrorTree(binaryTree1.getRoot());
        }
        boolean isMirrorImage = areMirrors(binaryTree.getRoot(), binaryTree1.getRoot());
        System.out.println(isMirrorImage);
        System.out.println("binaryTree " + binaryTree.levelOrder());
        System.out.println("binaryTree1 " + binaryTree1.levelOrder());
    }

    private static boolean areMirrors(node<Integer> root, node<Integer> root1) {
        if(root == null && root1 == null) return true;
        if(root == null || root1 == null) return false;

        Deque<node<Integer>> stack = new LinkedList<>();
        stack.push(root);

        Deque<node<Integer>> stack1 = new LinkedList<>();
        stack1.push(root1);

        boolean flag = true;
        while(!stack.isEmpty() && !stack1.isEmpty()){
            node<Integer> first = stack.pop();
            node<Integer> second = stack1.pop();
            if(!first.getData().equals(second.getData())){
                flag = false;
                break;
            }

            // adding right child and then left to check for mirror image
            if(first.getRight() != null) stack.push(first.getRight());
            if(first.getLeft() != null) stack.push(first.getLeft());

            // adding left child and then right to check for mirror image
            if(second.getLeft() != null) stack1.push(second.getLeft());
            if(second.getRight() != null) stack1.push(second.getRight());
        }

        return flag && stack.size() == stack1.size();
    }

    // using top down approach
    private static void createMirrorTree(node<Integer> node){
        if(node == null) return;
        Deque<node<Integer>> stack = new LinkedList<>();
        stack.push(node);
        while(!stack.isEmpty()){
            node<Integer> curr = stack.pop();
            node<Integer> temp = curr.getRight();
            curr.setRight(curr.getLeft());
            curr.setLeft(temp);
            if(curr.getRight() != null){
                stack.push(curr.getRight());
            }

            if(curr.getLeft() != null){
                stack.push(curr.getLeft());
            }
        }
    }
}
