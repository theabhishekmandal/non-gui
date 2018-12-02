package DataStructures.Tree;

/**
 * This is a node for a binary tree. Here we have a data member and two left and right pointers
 * that point to left and right children of the Tree.
 * A count variable to keep the track of the duplicates.
 */

public class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    int count;
    TreeNode(int data)
    {
        left=right=null;
        count=0;
        this.data=data;
    }

}
