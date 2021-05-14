package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary tree, return the tilt of the whole tree.
 *
 * The tilt of a tree node is defined as the absolute difference between the sum of all left
 * subtree node values and the sum of all right subtree node values. Null node has tilt 0.
 *
 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
 *
 * Example:
 *
 * Input:
 *          1
 *        /   \
 *       2     3
 *     /  \   /
 *    4    5 6
 * Output: 1
 * Explanation:
 * Tilt of node 4 : 0
 * Tilt of node 5 : 0
 * Tilt of node 2 : 0 + |5 - 4| = 1
 * Tilt of node 6 : 0
 * Tilt of node 3 : 1 + |6 - 0| = 7
 * Tilt of node 1 : 7 + |11 - 9| = 9
 *
 *
 * Approach:
 *  -   Do the post Order Traversal using double push
 *  -   store the nodes to stack(ans) if they are leaf nodes
 *  -   if they are not leaf nodes, pop only one node(left or right) if it has one child otherwise pop both nodes(left and right) from stack(ans)
 *  -   calculate the tilt value by taking the abs(left - right)
 *  -   then store the current.val + leftValue + rightValue in stack(ans)
 */

public class TiltTree {

    public static void main(String[] args) {
        var random = new Random();
        int t = 5;
        while(t-- > 0) {
            var tree = new BinaryTree<Integer>();
            IntStream.rangeClosed(0, random.nextInt(10)).forEach(tree::insertInBinaryTreeLevelOrder);
            System.out.println(tree.levelOrder() + "\n the tilt of the tree is = " + getTilt(tree.getRoot()) + "\n");
        }
    }

    private static int getTilt(Node<Integer> root) {
        if(root == null) {
            return 0;
        }

        var ans = new LinkedList<Integer>();
        var stack = new LinkedList<Node<Integer>>();
        stack.push(root);
        stack.push(root);

        int tilt = 0;

        // double push for postOrder traversal
        while(!stack.isEmpty()) {
            var curr = stack.pop();
            if(!stack.isEmpty() && curr == stack.peek()) {
                if(curr.getRight() != null) {
                    stack.push(curr.getRight());
                    stack.push(curr.getRight());
                }
                if(curr.getLeft() != null) {
                    stack.push(curr.getLeft());
                    stack.push(curr.getLeft());
                }
            }
            else {
                boolean isLeftNull = Objects.isNull(curr.getLeft());
                boolean isRightNull = Objects.isNull(curr.getRight());

                // if both children are null, then add the current node's value to list
                if(isLeftNull && isRightNull) {
                    ans.add(curr.getData());
                }
                // if both or one child are present
                else{
                    var left = 0;
                    var right = ans.pop();
                    if(!isLeftNull && !isRightNull) {
                        left = ans.pop();
                    }
                    tilt += Math.abs(left - right);
                    ans.push(curr.getData() + left + right);
                }
            }
        }
        return tilt;
    }
}
