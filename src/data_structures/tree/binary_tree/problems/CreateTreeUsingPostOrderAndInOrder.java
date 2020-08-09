package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given the inOrder and postOrder traversals generate the binaryTree
 */
public class CreateTreeUsingPostOrderAndInOrder {
    public static void main(String[] args) {
        Random random = new Random();
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        IntStream.range(0, random.nextInt(20)).forEach(binaryTree::insertInBinaryTreeLevelOrder);
        int[] in = get(binaryTree.inOrder());
        int[] post = get(binaryTree.postOrder());

        BinaryTree<Integer> binaryTree1 = createTree(in, post);
        String levelOrder1 = binaryTree.levelOrder();
        String levelOrder2 = binaryTree1.levelOrder();
        System.out.println(levelOrder1.equals(levelOrder2));
        System.out.println(
                levelOrder1 + "\n\n" + levelOrder2
        );
    }

    private static BinaryTree<Integer> createTree(int[] in, int[] post){
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        if(in == null || post == null || (in.length == 0 && in.length == post.length) || in.length != post.length)
            return binaryTree;
        /**
         * Using inclusive ranges and not exclusive
         * @param in inOrder array
         * @param inStart 0 initial start
         * @param inEnd length - 1
         * @param post postOrder array
         * @param postStart 0 initial start
         * @param postEnd length -1
         */
        binaryTree.setRoot(getBinaryTree(in, 0, in.length - 1, post, 0, post.length - 1));
        return binaryTree;
    }

    private static Node<Integer> getBinaryTree(int[] in, int inStart, int inEnd, int[] post, int postStart, int postEnd){
       if(inStart > inEnd || postStart > postEnd)
           return null;

        // get the current node
       Node<Integer> curr = new Node<>(post[postEnd]);

        // find the above current node above in inorder array, within the index range
       int index = getIndex(in, inStart, inEnd, post[postEnd]);

       if(index == -1){
           return null;
       }
       curr.setLeft(
               /**
                * Using inclusive ranges and not exclusive
                * @param in inOrder array
                * @param inStart inStart
                * @param inEnd index - 1 as index is consumed above
                * @param post postOrder array
                * @param postStart postStart
                * @param postEnd postStart + length of the possible left child array(index - inStart) - 1
                */
               getBinaryTree(in, inStart, index - 1, post, postStart, postStart + (index - inStart) - 1)
       );
       curr.setRight(
               /**
                * Using inclusive ranges and not exclusive
                * @param in inOrder array
                * @param inStart index + 1 as index is consumed above
                * @param inEnd inEnd
                * @param post postOrder array
                * @param postStart postStart + length of possible right child array(index - inStart)
                * @param postEnd postEnd - 1 because postEnd is already consumed above
                */
            getBinaryTree(in, index + 1, inEnd, post, postStart + (index - inStart), postEnd - 1)
       );

       // return the current node after setting left and right values
       return curr;
    }

    private static int getIndex(int[] in, int start, int end, int c) {
        int index = -1;
        for(int i = start; i <= end; i++){
            if(in[i] == c){
                index = i;
                break;
            }
        }
        return index;
    }

    private static int[] get(String traversedString) {
        StringTokenizer st = new StringTokenizer(traversedString, "[], ");
        int[] arr = new int[st.countTokens()];
        int i = 0;
        while(st.hasMoreTokens()){
            arr[i++] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }
}
