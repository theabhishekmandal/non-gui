package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 * Implementation of insertion sort for singlyLinked list
 */
public class _15MediumInsertionSort {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        first.addLast(9);
        first.addLast(8);
        first.addLast(6);
        first.addLast(5);
        first.addLast(7);
        first.addLast(4);
        System.out.println("list before sorting " + first);
        sort(first);
        System.out.println("list after sorting " + first);
    }

    // use this method for insertion sort better than the other one
    /*
        Gist of this solution is
        if this is the sorted list which is being created 5->6->8
        -   if next element is 4, it will be appended before 5, using new head pointer. -> 4->5->6->8
        -   if next element is 7, it will be appended after 6, by using the temp pointer ->
            4->5->6->8
            temp pointer is at 6, temp.next is 8 which is greater than 7
            temp will now point to 7, and 7 will now point to 8
            4->5->6->7->8


     */
    private static <T extends Comparable<? super T>> void sort(SinglyLinkedList<T> head){
        if(head == null) return;
        Node<T> curr = head.getHead();
        Node<T> nex = null;
        Node<T> newHead = null;

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
                Node<T> temp = newHead;
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

    private static <T extends Comparable<? super T>> Node<T> sort2(SinglyLinkedList<T> first){
        Node<T> head = first.getHead();
        if(head == null || head.getNext() == null) return head;
        Node<T> newHead = new Node<>(head.getData(), null);
        Node<T> pointer = head.getNext();
        while(pointer != null){
            Node<T> innerPointer = newHead;
            Node<T> next = pointer.getNext();
            if(pointer.getData().compareTo(newHead.getData()) <= 0){
                Node<T> oldHead = newHead;
                newHead = pointer;
                newHead.setNext(oldHead);
            }
            else{
                while(innerPointer.getNext() != null){
                    if(pointer.getData().compareTo(innerPointer.getData()) > 0 &&
                     pointer.getData().compareTo(innerPointer.getNext().getData()) <= 0){
                        Node<T> oldNext = innerPointer.getNext();
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
