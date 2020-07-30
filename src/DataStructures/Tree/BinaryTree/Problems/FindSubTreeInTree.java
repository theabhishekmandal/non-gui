package DataStructures.Tree.BinaryTree.Problems;

import DataStructures.Tree.BinaryTree.BinaryTreeImpl.BinaryTree;
import DataStructures.Tree.BinaryTree.BinaryTreeImpl.CreateSubTree;

import static DataStructures.Tree.BinaryTree.BinaryTreeImpl.BinaryTree.node;

import java.util.*;

/**
 * Given a subTree and a Tree, find whether the subTree exists in the given tree.
 * Approach
 *  -   First convert the subTree in inorder list
 *  -   Then traverse the main Tree in inorder traversal, compare the node with list containing subTree nodes
 *      if all the nodes are present in the list, then it is found otherwise not
 */

public class FindSubTreeInTree {
    public static void main(String[] args) {
        Random random = new Random();

        Integer[] sourceArray = new Integer[20];
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        for(int i = 0; i < sourceArray.length; i++){
            sourceArray[i] = random.nextInt(100);
            binaryTree.insertInBinaryTreeLevelOrder(sourceArray[i]);
        }
        System.out.println(binaryTree.levelOrder() + "\n");

        int t = 4;
        while(t-- > 0){
            if((t & 1) == 0) CreateSubTree.addNewValueInTree = true;
            BinaryTree<Integer> subTree = CreateSubTree.getRandomSubTreeFromParentTree(binaryTree);
            System.out.println(subTree.levelOrder());
            boolean isSubTreeInTree = isSubTree(binaryTree, subTree);
            System.out.println(isSubTreeInTree + "\n");
        }
    }

    private static <T> boolean isSubTree(BinaryTree<T> binaryTree, BinaryTree<T> subTree) {
        if(binaryTree == null) {
            return true;
        }
        if(subTree == null) {
            return false;
        }
        List<T> inOrderOfSubTree = getInOrderOfSubTree(subTree);
        return compareMainTreeWithInOrderSubTree(binaryTree, inOrderOfSubTree);
    }

    private static <T> boolean compareMainTreeWithInOrderSubTree(BinaryTree<T> binaryTree, List<T> inOrderOfSubTree) {
        node<T> curr = binaryTree.getRoot();
        Deque<node<T>> stack = new LinkedList<>();
        int counter = 0;
        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.getLeft();
            }
            else {
                curr = stack.pop();
                if(inOrderOfSubTree.get(counter).equals(curr.getData())){
                    counter++;
                }
                else{
                    counter = 0;
                }
                if(counter == inOrderOfSubTree.size()){
                    return true;
                }
                curr = curr.getRight();
            }
        }
        return false;
    }

    private static <T> List<T> getInOrderOfSubTree(BinaryTree<T> subTree) {
        List<T> list = new ArrayList<>();
        node<T> curr = subTree.getRoot();
        Deque<node<T>> stack = new LinkedList<>();
        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.getLeft();
            }
            else {
                curr = stack.pop();
                list.add(curr.getData());
                curr = curr.getRight();
            }
        }
        return list;
    }
}

