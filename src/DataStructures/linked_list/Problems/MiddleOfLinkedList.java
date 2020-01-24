package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;

import java.util.Random;

/**
 * n / 2 is the middle of the linked list if n starts from 0,
 */
public class MiddleOfLinkedList {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Random random = new Random();
        for(int i = 0; i < random.nextInt(100); i++)
            first.addLast(i);
        System.out.println("list is " + first + "\n" + "middle elements is " + getMiddleNode(first));

    }
    private static <T> SinglyLinkedList.node<T> getMiddleNode(SinglyLinkedList<T> first){
        SinglyLinkedList.node<T> slowPointer = first.getHead();
        SinglyLinkedList.node<T> fastPointer = first.getHead();
        while(fastPointer != null && fastPointer.getNext() != null){
            fastPointer = fastPointer.getNext().getNext();
            slowPointer = slowPointer.getNext();
        }
        return slowPointer;
    }
}
