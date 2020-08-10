package miscellaneous.leetcode.challenge30_days_april.week2;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.Random;

/**
 * Week 2 day 1
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
    private static <T> SinglyLinkedList.Node<T> getMiddleNode(SinglyLinkedList<T> first){
        SinglyLinkedList.Node<T> slowPointer = first.getHead();
        SinglyLinkedList.Node<T> fastPointer = first.getHead();
        while(fastPointer != null && fastPointer.getNext() != null){
            fastPointer = fastPointer.getNext().getNext();
            slowPointer = slowPointer.getNext();
        }
        return slowPointer;
    }
}
