package dataStructures.linkedList.problems;

import dataStructures.linkedList.node.SinglyLinkedList;
import static dataStructures.linkedList.node.SinglyLinkedList.node;

import java.util.Scanner;

/**
 * Implementation of insertion sort for singlyLinked list
 */
public class InsertionSort {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        first.addLast(1);
        first.addLast(3);
        first.addLast(9);
        first.addLast(2);
        first.addLast(0);
        first.addLast(7);
        System.out.println("list before sorting " + first);
        sort(first);
        System.out.println("list after sorting " + first);
    }

    // use this method for insertion sort better than the other one
    private static <T extends Comparable<? super T>> void sort(SinglyLinkedList<T> head){
        if(head == null) return;
        node<T> curr = head.getHead();
        node<T> nex = null;
        node<T> newHead = null;

        while(curr != null){
            nex = curr.getNext();
            // if first time or if current node value in list is smaller than new node
            // eg: if current node is 1 and the newHead is 3
            if(newHead == null || curr.getData().compareTo(newHead.getData()) <= 0){
                curr.setNext(newHead);
                newHead = curr;
            }
            // if current node value in list is greater than the newHead
            // eg: if current node is 3 and newHead is 1,
            // then you want to traverse and check where does 3 best fits
            else{
                node<T> temp = newHead;
                while(temp.getNext() != null && curr.getData().compareTo(temp.getNext().getData()) > 0){
                    temp = temp.getNext();
                }
                curr.setNext(temp.getNext());
                temp.setNext(curr);
            }
            if(nex == null) head.setTail(curr);
            curr = nex;
        }
        head.setHead(newHead);
    }

    private static <T extends Comparable<? super T>> node<T> sort2(SinglyLinkedList<T> first){
        node<T> head = first.getHead();
        if(head == null || head.getNext() == null) return head;
        node<T> newHead = new node<>(head.getData(), null);
        node<T> pointer = head.getNext();
        while(pointer != null){
            node<T> innerPointer = newHead;
            node<T> next = pointer.getNext();
            if(pointer.getData().compareTo(newHead.getData()) <= 0){
                node<T> oldHead = newHead;
                newHead = pointer;
                newHead.setNext(oldHead);
            }
            else{
                while(innerPointer.getNext() != null){
                    if(pointer.getData().compareTo(innerPointer.getData()) > 0 &&
                     pointer.getData().compareTo(innerPointer.getNext().getData()) <= 0){
                        node<T> oldNext = innerPointer.getNext();
                        innerPointer.setNext(pointer);
                        pointer.setNext(oldNext);
//                        break;
                    }
                    innerPointer = innerPointer.getNext();
                }
                if(innerPointer.getNext() == null && pointer.getData().compareTo(innerPointer.getData()) > 0){
                    innerPointer.setNext(pointer);
                    pointer.setNext(null);
                }
                if(innerPointer.getNext() == null) first.setTail(innerPointer);
            }
            pointer = next;
        }
        first.setHead(newHead);
        return newHead;
    }
}
