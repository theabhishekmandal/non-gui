package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

/**
 * Given a binary Tree remove all the nodes that have only one child.
 * While removing don't remove the leaf nodes.
 * eg
 * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 *    0
 *   3 1
 *  4 5 * 2
 * * 7 * 6 8 *
 * * * * * * 9
 *
 *   0
 *  3 9
 * 7 6 * *
 *
 * Approach:
 *  - First do the postOrderTraversal of the tree
 *  - Now for every node check whether it is leaf node or not
 *      -   if both of the children are absent then continue
 *      -   if one or both children are present then
 *              -   check whether curr.left is none then return null
 *              -   check whether curr.left.left is none and curr.left.right is none then return curr
 *                  this is the leaf node
 *              -   check whether curr.left.left is none then return curr.left.right(one child condition)
 *              -   check whether curr.left.left and curr.left.right is present means if both child
 *                  are present then return the child
 *
 *  - do same case with right hand side
 */

public class RemoveHalfNodes {
    public static void main(String[] args) {
        var binaryTree = new BinaryTree<Integer>();
        List<Integer> list = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        System.out.println(list);
        list.forEach(x -> randomInsert(x, binaryTree));
        System.out.println(binaryTree.levelOrderPretty());
        removeHalfNodes(binaryTree);
        System.out.println(binaryTree.levelOrderPretty());
    }

    private static <T> void removeHalfNodes(BinaryTree<T> binaryTree) {
        if(binaryTree.getRoot() == null) {
            return;
        }
        var root = binaryTree.getRoot();
        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(root);
        stack.push(root);

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
                boolean isLeftNotNull = Objects.nonNull(curr.getLeft());
                boolean isRightNotNull = Objects.nonNull(curr.getRight());
                if(isLeftNotNull || isRightNotNull) {
                    curr.setLeft(whichNode(curr.getLeft()));
                    curr.setRight(whichNode(curr.getRight()));
                }
            }
        }
    }

    private static <T> Node<T> whichNode(Node<T> child) {
        // if child is null means just return null
        if(child == null) {
            return null;
        }
        // if both of them are null means it is leaf node don't mess with it
        if(child.getLeft() == null && child.getRight() == null) {
            return child;
        }
        // if one of them is absent return the other one
        if(child.getLeft() == null) {
            return child.getRight();
        }
        else if(child.getRight() == null) {
            return child.getLeft();
        }
        // if both of them present then return it
        return child;
    }

    private static final Random random = new Random();
    private static <T> void randomInsert(T data, BinaryTree<T> binaryTree) {
        if(binaryTree.getRoot() == null) {
            binaryTree.setRoot(Node.of(data));
            return;
        }
        Node<T> temp = binaryTree.getRoot();
        while(temp != null) {
            boolean addToRight = random.nextBoolean();
            if(addToRight) {
                if(temp.getRight() == null) {
                    temp.setRight(Node.of(data));
                    return;
                }
                else {
                    temp = temp.getRight();
                }
            }
            else {
                if(temp.getLeft() == null) {
                    temp.setLeft(Node.of(data));
                    return;
                }
                else {
                    temp = temp.getLeft();
                }
            }
        }
    }
}
