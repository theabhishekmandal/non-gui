package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.Random;

/**
 * n / 2 is the middle of the linked list if n starts from 0,
 */
public class _23EasyMiddleOfLinkedList {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Random random = new Random();
        for(int i = 0; i < random.nextInt(100); i++)
            first.addLast(i);
        System.out.println("list is " + first + "\n" + "middle elements is " + getMiddleNode(first));

    }
    private static <T> SinglyLinkedList.Node<T> getMiddleNode(SinglyLinkedList<T> first){
        SinglyLinkedList.Node<T> slowPointer = first.getHead();
        SinglyLinkedList.Node<T> fastPointer = first.getHead();
        if (fastPointer == null) {
            return fastPointer;
        }
        // [1, 2] -> using below logic 1 will come as middle element
        while(fastPointer.getNext() != null && fastPointer.getNext().getNext() != null){
            fastPointer = fastPointer.getNext().getNext();
            slowPointer = slowPointer.getNext();
        }
        return slowPointer;
    }
}
