package DataStructures.Tree.BinaryTree.Problems;

import DataStructures.Tree.BinaryTree.TreeImpl.BinaryTree;

/**
 * Construct a binary Tree from given InOrder and PreOrder traversals
 *
 * In a PrOrder sequence, leftmost element denotes the root of the tree. So we know 'A' is the root
 * of the given sequences. By searching 'A' in Inorder sequence we can find out all elements on the left side of 'A'
 * which come under the left subtree, and elements on the right side of 'A', which come under the right subtree. So
 * we get the structure as seen below
 *
 *                        DBE<-----A---->FC
 * We recursively follow the above steps to generate the tree
 */
public class CreateTreeUsingPreOrderAndInOrder {
    public static void main(String[] args) {
        char[] in = new char[] { 'D', 'B', 'E', 'A', 'F', 'C' };
        char[] pre = new char[] { 'A', 'B', 'D', 'E', 'C', 'F' };
        BinaryTree<Character> binaryTree = createTree(in, pre);
        System.out.println(binaryTree.levelOrder());
        System.out.println(binaryTree.preOrder());
        System.out.println(binaryTree.inOrder());
    }

    private static BinaryTree<Character> createTree(char[] in, char[] pre){
        BinaryTree<Character> binaryTree = new BinaryTree<>();
        if(in == null || pre == null || (in.length == 0  && in.length == pre.length) || in.length != pre.length)
            return binaryTree;
        /**
         * Using inclusive ranges and not exclusive
         * @param in inorder values given
         * @param inStart 0
         * @param inEnd in.length - 1
         * @param pre preorder values given
         * @param preStart 0
         * @param preEnd pre.length - 1
         */
        BinaryTree.node<Character> node = getBinaryTree(in, 0, in.length - 1, pre, 0, pre.length - 1);
        binaryTree.setRoot(node);
        return binaryTree;
    }

    private static BinaryTree.node<Character> getBinaryTree(char[] in, int inStart, int inEnd, char[] pre, int preStart, int preEnd) {
        if(inStart > inEnd || preStart > preEnd) return null;

        // get the current node
        BinaryTree.node<Character> curr = new BinaryTree.node<>(pre[preStart]);

        // find the above current node above in inorder array, within the index range
        int index = getIndex(in, pre[preStart], inStart, inEnd);

        if(index == -1){
            return null;
        }

        curr.setLeft(
                /**
                 * Using inclusive ranges and not exclusive
                 * @param in inorder values given
                 * @param inStart starting index for inorder array
                 * @param inEnd end index - 1, because index is consumed above
                 * @param pre preorder values given
                 * @param preStart starting index will be consumed preStart + 1
                 * @param preEnd endIndex will be consumed index + length of array which contains left child
                 */
                getBinaryTree(in, inStart, index - 1, pre, preStart + 1, preStart + (index - inStart))
        );

        curr.setRight(
                /**
                 * Using inclusive ranges and not exclusive
                 * @param in inorder values given
                 * @param inStart starting index for inorder array will be consumed index + 1
                 * @param inEnd end index, will be current inEnd
                 * @param pre preorder values given
                 * @param preStart starting index will be consumed index + 1 + length of array which contains left child
                 * @param preEnd endIndex will be the current preEnd
                 */
                getBinaryTree(in, index + 1, inEnd, pre, preStart + (index - inStart + 1), preEnd)
        );

        // return the current node after setting left and right values
        return curr;
    }

    private static int getIndex(char[] in, char c, int start, int end) {
        int index = -1;
        for(int i = start; i <= end; i++){
            if(in[i] == c){
                index = i;
                break;
            }
        }
        return index;
    }
}
