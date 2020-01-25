package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;
import static DataStructures.linked_list.Node.SinglyLinkedList.node;

import java.util.Scanner;

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
        node<Integer> head = sort2(first);
        String answer = "";
        for(;head != null; head = head.getNext()) answer += head.getData() + ",";
        System.out.println("list after sorting " + first);
    }

    private static <T extends Comparable<? super T>> void sort(SinglyLinkedList<T> head){
        node<T> temp = head.getHead();
        while(temp != null){
            node<T> next = temp.getNext();
            temp = temp.getNext();
        }
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
