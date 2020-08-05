package dataStructures.Tree.BinaryTree.Problems;


import dataStructures.Tree.BinaryTree.BinaryTreeImpl.BinaryTree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a binary tree, print the elements in zig zag order.
 * Ex: if tree is 1
 *              2   3
 *            4  5  6  7
 *
 *  then zigzag traversal will be 1 3 2 4 5 6 7
 *  Approach:
 *      There are two approaches
 *          -   Using single queue but multiple traversal (better for space complexity)
 *          -   Using multiple queue but less traversal (better for time complexity)
 *  Using Single Queue
 *      -   In Single queue add the node to nodeList just like level order traversal
 *      -   But, while adding to the finalList we will check through the flag whether is set or not
 *      -   If it is set then we will reverse the elements of the list using a stack and then add to the finalList
 *
 *  Using Double Queue
 *      -   There will be two queues leftQueue and rightQueue
 *      -   Properties of insertion and removal in both queues are as follows:
 *              -   adding  -   In leftQueue addition will be done using addFirst and right child
 *                              is inserted before the left child. Also, the null value is added using addLast
 *                              method
 *              -   adding  -   In rightQueue addition will be done using addLast and left child
 *                              is inserted before the right child. Also, the null value is added using addFirst method
 *              -   removal -   In leftQueue removal operation will be in same direction as that of the add operation
 *                              i.e removeFirst
 *              -   removal -   In rightQueue removal operation will be in same direction as that of the add operation
 *                              i.e removeLast
 *      -   Using these properties it will be easier to remember.
 */
public class ZigZagTraversal {
    public static void main(String[] args) throws IOException {
        BinaryTree<Integer> tree = new BinaryTree<>();
        IntStream.range(1, 10).forEach(tree::insertInBinaryTreeLevelOrder);
        System.out.println(tree.levelOrder());
        long starttime = System.currentTimeMillis();
        String zigZag = printInZigZagPattern(tree.getRoot());
        long stoptime = System.currentTimeMillis();
        String finalAnswer = "first string " + zigZag + " time taken is= " + (stoptime - starttime) + " milli seconds";

        starttime = System.currentTimeMillis();
        String zigZag2 = printInZigZagPatternNew(tree.getRoot());
        stoptime = System.currentTimeMillis();
        String finalAnswer2 = "first string " + zigZag2 + " time taken is= " + (stoptime - starttime)+ " milli seconds";

        System.out.println(zigZag.equals(zigZag2));
        BufferedWriter br = new BufferedWriter(new FileWriter("hello.txt"));
        br.write(finalAnswer);
        br.write(finalAnswer2);
        br.flush();
        br.close();
    }

    private static <T> String printInZigZagPatternNew(BinaryTree.node<T> root){
        LinkedList<String> nodeList = new LinkedList<>();
        List<List<String>> finalList = new ArrayList<>();
        Deque<BinaryTree.node<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        boolean leftToRight = true;
        while(!queue.isEmpty()){
            BinaryTree.node<T> curr = queue.poll();
            if(curr != null){
                nodeList.add(curr.getData().toString());
                if(curr.getLeft() != null){
                    queue.add(curr.getLeft());
                }
                if(curr.getRight() != null){
                    queue.add(curr.getRight());
                }
            }
            else{
                if (!leftToRight) {
                    Deque<String> stack = new LinkedList<>();
                    while (!nodeList.isEmpty()) stack.push(nodeList.removeFirst());
                    while (!stack.isEmpty()) nodeList.addLast(stack.pop());
                }
                finalList.add(nodeList);
                nodeList = new LinkedList<>();
                if(!queue.isEmpty()){
                    leftToRight = !leftToRight;
                    queue.add(null);
                }
            }
        }
        return "[" + finalList.stream()
                .map(list -> "(" + String.join(", ", list) + ")")
                .collect(Collectors.joining(", \n")) + "]";
    }
    private static <T> String printInZigZagPattern(BinaryTree.node<T> root){
        List<String> nodeList = new ArrayList<>();
        List<List<String>> finalList = new ArrayList<>();

        Deque<BinaryTree.node<T>> leftQueue = new LinkedList<>();
        Deque<BinaryTree.node<T>> rightQueue = new LinkedList<>();
        leftQueue.addFirst(null);
        leftQueue.addFirst(root);

        while(true){
            if(!leftQueue.isEmpty()){
                while (!leftQueue.isEmpty()){
                    BinaryTree.node<T> node = leftQueue.pollFirst();
                    if(node != null){
                        nodeList.add(node.getData().toString());
                        addToQueueUsingFlag(rightQueue, node.getLeft(), node.getRight(), true);
                    }
                    else{
                        finalList.add(nodeList);
                        nodeList = new ArrayList<>();
                        if(!rightQueue.isEmpty()){
                            rightQueue.addFirst(null);
                        }
                    }
                }
            }
            else if(!rightQueue.isEmpty()){
                while(!rightQueue.isEmpty()){
                    BinaryTree.node<T> node = rightQueue.pollLast();
                    if(node != null){
                        nodeList.add(node.getData().toString());
                        addToQueueUsingFlag(leftQueue, node.getLeft(), node.getRight(), false);
                    }
                    else{
                        finalList.add(nodeList);
                        nodeList = new ArrayList<>();
                        if(!leftQueue.isEmpty()){
                            leftQueue.addLast(null);
                        }
                    }
                }
            }
            else{
                break;
            }
        }
        return "[" + finalList.stream()
                .map(list -> "(" + String.join(", ", list) + ")")
                .collect(Collectors.joining(", \n")) + "]";
    }

    private static <T> void addToQueueUsingFlag(Deque<BinaryTree.node<T>> queue, BinaryTree.node<T> left, BinaryTree.node<T> right, boolean addLast) {
        if(addLast){
           if(left != null){
               queue.addLast(left);
           }
           if(right != null){
               queue.addLast(right);
           }
        }
        else{
            if(right != null){
                queue.addFirst(right);
            }
            if(left != null){
                queue.addFirst(left);
            }
        }
    }
}
