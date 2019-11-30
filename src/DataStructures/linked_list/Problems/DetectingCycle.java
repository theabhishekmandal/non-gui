package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.GenericSinglyLinkedList;

import java.util.Random;

public class DetectingCycle {
    public static void main(String[] args) {
        GenericSinglyLinkedList<Integer> arr = new GenericSinglyLinkedList<>();
        for(int i = 0; i < 10; i++) arr.addLast(i);
        Random random = new Random();
        // setting the tail to the random node from the list to make it a circular
        arr.getTail().setNext(arr.getNode(random.nextInt(arr.getSize())));

        GenericSinglyLinkedList.node<Integer> fastNode = arr.getHead().getNext();
        GenericSinglyLinkedList.node<Integer> slowNode = arr.getHead();
        while(fastNode != slowNode){
            fastNode = fastNode.getNext().getNext();
            slowNode = slowNode.getNext();
        }

        // setting the next to null
        fastNode.setNext(null);
        System.out.println(fastNode);
        System.out.println(arr);
    }
}
