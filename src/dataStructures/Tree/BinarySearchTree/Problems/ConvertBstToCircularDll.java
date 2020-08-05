package dataStructures.Tree.BinarySearchTree.Problems;

import dataStructures.Tree.BinarySearchTree.BinarySearchTreeImpl.BinarySearchTree;
import static dataStructures.Tree.BinarySearchTree.BinarySearchTreeImpl.BinarySearchTree.node;

import java.util.*;
import java.util.stream.IntStream;

public class ConvertBstToCircularDll {
    static class Pair<U, T> {
        U first;
        T second;
        Pair(U first, T second) {
            this.first = first;
            this.second = second;
        }
        public static <U, T> Pair<U, T> of(U first, T second) {
            return new Pair<>(first, second);
        }
    }

    public static void main(String[] args) {
        var bst = new BinarySearchTree<Integer>();
        var random = new Random();
        IntStream.range(0, 10).forEach(x -> bst.insertInBst(random.nextInt(20)));
        System.out.println(bst.inOrder());
        var pairString = getBstToDllAsString(bst);
        System.out.println(pairString.first);
        System.out.println(pairString.second);
    }

    private static <T extends Comparable<? super T>> Pair<String, String> getBstToDllAsString(BinarySearchTree<T> bst) {
        var pair = bstToDll(bst);
        if(pair == null) {
            return Pair.of("[]", "[]");
        }
        var head = pair.first;
        var tail = pair.second;
        var tempTail = tail;
        var builder = new StringBuilder("[");
        var builder2 = new StringBuilder("[");
        while(head != tail) {
            builder.append(head.getData()).append(", ");
            builder2.append(tempTail.getData()).append(", ");
            head = head.getRight();
            tempTail = tempTail.getLeft();
        }
        builder.append(tail.getData()).append("]");
        builder2.append(tempTail.getData()).append("]");
        return Pair.of(builder.toString(), builder2.toString());
    }

    private static <T extends Comparable<? super T>> Pair<node<T>, node<T>> bstToDll(BinarySearchTree<T> bst) {
        node<T> node;
        if(bst == null || (node = bst.getRoot()) == null) {
           return null;
        }
        Deque<node<T>> stack = new LinkedList<>();
        node<T> curr = node;

        node<T> head = null;
        node<T> tail = null;
        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.getLeft();
            }
            else{
                curr = stack.pop();
                node<T> right = curr.getRight();
                if(head == null) {
                    curr.setRight(curr);
                    curr.setLeft(curr);
                    head = tail = curr;
                }
                else {
                    tail.setRight(curr);
                    curr.setLeft(tail);
                    tail = tail.getRight();
                }
                curr = right;
            }
        }
        if(head != null) {
            head.setLeft(tail);
            tail.setRight(head);
        }
        return Pair.of(head, tail);
    }
}
