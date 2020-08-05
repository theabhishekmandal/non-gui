package dataStructures.linkedList.problems;

import dataStructures.linkedList.node.SinglyLinkedList;

import java.util.Random;
import java.util.stream.IntStream;
import static dataStructures.linkedList.node.SinglyLinkedList.node;

public class OddOrEvenLengthLinkedList {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Random random = new Random();
        IntStream.range(0, random.nextInt(10)).forEach(first::addLast);
        System.out.println(first);
        System.out.println(getLength(first));
    }

    /*
        Using a 2x pointer that will determine if a given linked list is
        even length or odd length
     */
    private static <T> String getLength(SinglyLinkedList<T> first){
        String[] arr = {"even", "odd"};
        node<T> fast = first.getHead();
        while(fast != null && fast.getNext() != null){
            fast = fast.getNext().getNext();
        }
        if(fast == null) return arr[0];
        return arr[1];
    }
}
