package DataStructures.Tree.BinaryTree.Problems;

import DataStructures.Tree.BinaryTree.TreeImpl.BinaryTree;
import static DataStructures.Tree.BinaryTree.TreeImpl.BinaryTree.node;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Give an algorithm for find LCA(Least Common Ancestor) of two nodes in a Binary Tree
 * Using recursion in O(n) is the faster way
 * Approach
 *      -   Traverse the tree using post order recursion, we will set v1 or v2 if first or second matches the
 *          root
 *      -   now the result of left and right can be (null, null), (nonnull, null), (null, nonnull) and (nonnull, nonnull)
 *      -   if both are not null then there common part is root then we return root
 *      -   if both of them are null then we return null
 *      -   if one of them is null, then in this case the flags will be used, if at the end of recursion
 *          if these flags are not set then we will return null because there can be a possibility while finding
 *          the common root of both first and second node,
 */
public class LeastCommonAncestor {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        int first = random.nextInt(10);
        int second = random.nextInt(10);
        IntStream.range(0, 10).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder() + "\nfirst value = " + first  + "\nsecond value = " + second);
        Integer value = getLeastCommonAncestor(binaryTree.getRoot(), first, second);
        System.out.println(value);
    }

    private static boolean v1, v2;
    private static Integer getLeastCommonAncestor(node<Integer> root, int first, int second) {
        if(root == null) return null;
        v1 = false; v2 = false;
        node<Integer> node = getAncestor(root, first, second);
        if(v1 && v2)
            return node.getData();
        return null;
    }

    private static node<Integer> getAncestor(node<Integer> root, int first, int second){
        if(root == null)
            return null;

        node<Integer> temp = null;
        // if root matches with first and second then we assign it to temp
        // use of v1 and v2 is that, if both of the values are set then we will return the answer
        // it is used because it assumes that first and second is already present in the tree
        // suppose if first is not present then it will return root, but we should return null instead

        // Also, value of temp will change according to first and second.
        // we don't return after assigning temp = root because, suppose if first=5 and second=6
        // and 5 is parent of 6, then we have to traverse 6 also, we can't return if we found first

        // Also, don't use this statement after recursion call, because suppose if left and right both are same value
        // then both flags won't be set
        if(root.getData() == first){
            v1 = true;
            temp = root;
        }
        if(root.getData() == second){
            v2 = true;
            temp = root;
        }
        node<Integer> left = getAncestor(root.getLeft(), first, second);
        node<Integer> right = getAncestor(root.getRight(), first, second);
        if(temp != null){
            return temp;
        }
        if(left != null && right != null){
            return root;
        }
        else{
           return (left != null)? left : right;
        }
    }
}
