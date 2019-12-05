package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.GenericSinglyLinkedList;

import java.util.Random;

public class MiddleOfLinkedList {
    public static void main(String[] args) {
        GenericSinglyLinkedList<Integer> first = new GenericSinglyLinkedList<>();
        Random random = new Random();
        for(int i = 0; i < random.nextInt(100); i++)
            first.addLast(i);
        System.out.println("list is " + first + "\n" + "middle elements is " + getMiddleNode(first));

    }
    private static <T> GenericSinglyLinkedList.node<T> getMiddleNode(GenericSinglyLinkedList<T> first){
        GenericSinglyLinkedList.node<T> slowPointer = first.getHead();
        GenericSinglyLinkedList.node<T> fastPointer = first.getHead();
        while(fastPointer != null && fastPointer.getNext() != null){
            fastPointer = fastPointer.getNext().getNext();
            slowPointer = slowPointer.getNext();
        }
        return slowPointer;
    }
}
