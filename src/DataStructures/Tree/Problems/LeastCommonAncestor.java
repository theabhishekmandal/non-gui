package DataStructures.Tree.Problems;

import DataStructures.Tree.TreeImpl.BinaryTree;
import static DataStructures.Tree.TreeImpl.BinaryTree.node;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Give an algorithm for find LCA(Least Common Ancestor) of two nodes in a Binary Tree
 */
public class LeastCommonAncestor {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        int first = random.nextInt(20);
        int second = random.nextInt(20);
        IntStream.range(0, 20).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        System.out.println(binaryTree.levelOrder() + "\nfirst value = " + first  + "\nsecond value = " + second);
        Integer value = getLeastCommonAncestor(binaryTree.getRoot(), first, second);
        System.out.println(value);
    }

    private static Integer getLeastCommonAncestor(node<Integer> root, int first, int second) {
        if(root == null) return null;
        return getAncestor(root, first, second).getData();
    }

    private static node<Integer> getAncestor(node<Integer> root, int first, int second){
        if(root == null)
            return null;
        if(root.getData() == first || root.getData() == second)
            return root;
        node<Integer> left = getAncestor(root.getLeft(), first, second);
        node<Integer> right = getAncestor(root.getRight(), first, second);
        if(left != null && right != null){
            return root;
        }
        else{
           return (left != null)? left : right;
        }
    }
}
