package DataStructures.Tree.BinaryTree.Problems;

import DataStructures.Tree.BinaryTree.TreeImpl.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

/**
 * Create the mirror image of binary tree
 * Approach
 *  -   You can top down approach or bottom up approach
 *  -   In the iterative version top down approach is used i.e first right and left child's are resolved of the root
 *      and then their subtrees
 *  -   In the recursive version bottom up approach is used i.e first interchange the left and right of the child
 *      and then go for the parent
 */
public class CreateMirrorTree {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        for(int i = 0; i < random.nextInt(20); i++){
           binaryTree.insertInBinaryTreeLevelOrder(random.nextInt(20));
        }
        System.out.println("binary Tree before mirroring " + binaryTree.levelOrder());
        createMirrorTree(binaryTree.getRoot());
        System.out.println("binary Tree after mirroring " + binaryTree.levelOrder());
        createMirrorTreeRecursion(binaryTree.getRoot());
        System.out.println("binary Tree after mirroring again " + binaryTree.levelOrder());
    }

    // using top down approach
    private static void createMirrorTree(BinaryTree.node<Integer> node){
        if(node == null) return;
        Deque<BinaryTree.node<Integer>> stack = new LinkedList<>();
        stack.push(node);
        while(!stack.isEmpty()){
            BinaryTree.node<Integer> curr = stack.pop();
            BinaryTree.node<Integer> temp = curr.getRight();
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

    // using bottom up approach
    private static void createMirrorTreeRecursion(BinaryTree.node<Integer> node){
        if(node == null) return;
        createMirrorTreeRecursion(node.getLeft());
        createMirrorTreeRecursion(node.getRight());
        BinaryTree.node<Integer> temp = node.getRight();
        node.setRight(node.getLeft());
        node.setLeft(temp);
    }
}
