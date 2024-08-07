package data_structures.linked_list.problems;

import data_structures.linked_list.node.DoublyLinkedList;
import data_structures.linked_list.node.SinglyLinkedList;

import java.util.Random;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 * Insert a node in a sorted singly linked list and insert a node in doubly linked list
 */

public class _14EasyInsertingInSortedList {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> sll = new SinglyLinkedList<>();
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();

        Random random = new Random();
        random.ints(0, 11).limit(10).sorted().forEach(sll::addLast);
        random.ints(0, 11).limit(10).sorted().forEach(dll::addLast);

        Integer value = random.nextInt(10);

        System.out.println("singly list before insertion "  + sll + "value to be inserted " + value);
        insertNodeInSortedList(sll, value);
        System.out.println("singly list after insertion "  + sll);

        System.out.println("doubly list before insertion "  + dll + "value to be inserted " + value);
        insertNodeInSortedDoublyList(dll, value);
        System.out.println("doubly list after insertion "  + dll);
    }

    private static <T extends Comparable<? super T>> void insertNodeInSortedList(SinglyLinkedList<T> sll, T nextValue) {
        if(sll == null) {
            sll = new SinglyLinkedList<>();
        }
        if(sll.getHead() == null){
            sll.addLast(nextValue);
            return;
        }

        Node<T> temp = sll.getHead();
        Node<T> newNode = new Node<>(nextValue, null);
        if(sll.getHead().getData().compareTo(nextValue) > 0){
            newNode.setNext(sll.getHead());
            sll.setHead(newNode);
            return;
        }
        while(temp.getNext() != null){
            if(temp.getNext().getData().compareTo(nextValue) > 0){
                newNode.setNext(temp.getNext());
                temp.setNext(newNode);
                return;
            }
            temp = temp.getNext();
        }
        temp.setNext(newNode);
    }

    private static <T extends Comparable<? super T>> void insertNodeInSortedDoublyList(DoublyLinkedList<T> dll, T value) {
        if(dll == null || dll.getHead() == null) {
            dll = new DoublyLinkedList<>();
        }
        if(dll.getHead() == null){
            dll.addLast(value);
            return;
        }

        DoublyLinkedList.Node<T> curr = dll.getHead();
        DoublyLinkedList.Node<T> newNode = new DoublyLinkedList.Node<>(null, value, null);

        while(curr.getNext() != null){
            if(curr.getData().compareTo(value) > 0){
                DoublyLinkedList.Node<T> prev = curr.getPrevious();

                // to check if first element is greater than current
                if(prev == null){
                    newNode.setNext(curr);
                    dll.setHead(newNode);
                    return;
                }
                prev.setNext(newNode);
                newNode.setPrevious(prev);

                newNode.setNext(curr);
                curr.setPrevious(newNode);
                return;
            }
            curr = curr.getNext();
        }

        curr.setNext(newNode);
        newNode.setPrevious(curr);
    }
}
