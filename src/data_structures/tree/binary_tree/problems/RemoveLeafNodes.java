package data_structures.tree.binary_tree.problems;

import data_structures.tree.binary_tree.binary_tree_impl.BinaryTree;
import static data_structures.tree.binary_tree.binary_tree_impl.BinaryTree.Node;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a binary Tree remove all the leaf nodes from the tree
 *
 * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 *   0
 *  1 3
 * 2 9 4 *
 * 5 * * * 6 *
 * 8 7 * *
 *
 *   0
 *  1 3
 * 2 * 4 *
 * 5 * * *
 *
 * Approach
 *  -   For a given binary tree do the postOrder traversal
 *  -   During traversal either the given node is a leaf node or a non leaf node
 *      -   for the leaf node add them to a queue
 *      -   for non leaf node
 *          -   if the queue for leaf nodes is empty this means there are no leaf nodes, so just return
 *          -   if the queue is not empty first check if the first node is the left of the current node
 *                  -   if it is the left node then remove that node from queue and set the left as null
 *                  -   Also it can be possible that right node also exist in the queue, check for that also
 *          -   If the queue is not empty and the first node is right node means left node of that current node
 *              does not exists so simply, just make right as null remove that node from queue and return
 *
 */

public class RemoveLeafNodes {
    public static void main(String[] args) {
        var binaryTree = new BinaryTree<Integer>();
        List<Integer> list = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        System.out.println(list);
        list.forEach(x -> randomInsert(x, binaryTree));
        System.out.println(binaryTree.levelOrderPretty());
        removeLeafNodes(binaryTree);
        System.out.println(binaryTree.levelOrderPretty());
    }

    private static <T> void removeLeafNodes(BinaryTree<T> binaryTree) {
        if (binaryTree.getRoot() == null) {
            return;
        }
        var root = binaryTree.getRoot();
        Deque<Node<T>> stack = new ArrayDeque<>();
        var leafList = new ArrayDeque<Node<T>>();
        stack.push(root);
        stack.push(root);

        while (!stack.isEmpty()) {
            var curr = stack.pop();
            if (!stack.isEmpty() && curr == stack.peek()) {
                if (curr.getRight() != null) {
                    stack.push(curr.getRight());
                    stack.push(curr.getRight());
                }
                if (curr.getLeft() != null) {
                    stack.push(curr.getLeft());
                    stack.push(curr.getLeft());
                }
            } else {
                boolean isLeftNull = Objects.isNull(curr.getLeft());
                boolean isRightNull = Objects.isNull(curr.getRight());
                if(isLeftNull && isRightNull) {
                   leafList.add(curr);
                }
                else {
                    removeLeafIfNecessary(leafList, curr);
                }
            }
        }
    }

    private static <T> void removeLeafIfNecessary(ArrayDeque<Node<T>> leafList, Node<T> curr) {
        // if there is no leaf then return
        if(leafList.isEmpty()) {
            return;
        }

        var first = leafList.removeFirst();
        // first node can be either left node or right node

        // if first node is left node, then remove and set left as null
        // Also check if the right node exists or not
        if(curr.getLeft() == first) {
            curr.setLeft(null);
        }

        // if first node is right node then left node does not exists
        // return after setting null
        else if(curr.getRight() == first) {
           curr.setRight(null);
           return;
        }

        if(!leafList.isEmpty() && curr.getRight() == leafList.getFirst())  {
            curr.setRight(null);
            leafList.removeFirst();
        }
    }



    private static final Random random = new Random();
    private static <T> void randomInsert(T data, BinaryTree<T> binaryTree) {
        if(binaryTree.getRoot() == null) {
            binaryTree.setRoot(new Node<>(data));
            return;
        }
        Node<T> temp = binaryTree.getRoot();
        while(temp != null) {
            boolean addToRight = random.nextBoolean();
            if(addToRight) {
                if(temp.getRight() == null) {
                    temp.setRight(new Node<>(data));
                    return;
                }
                else {
                    temp = temp.getRight();
                }
            }
            else {
                if(temp.getLeft() == null) {
                    temp.setLeft(new Node<>(data));
                    return;
                }
                else {
                    temp = temp.getLeft();
                }
            }
        }
    }
}
