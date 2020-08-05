package dataStructures.linkedList.problems;

import dataStructures.linkedList.node.SinglyLinkedList;
import static dataStructures.linkedList.node.SinglyLinkedList.node;
import java.util.Random;
import java.util.stream.IntStream;

public class ReversingLinkedListUsingIteration {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Random random = new Random();
        IntStream.range(0, random.nextInt(10)).forEach(first::addLast);
        System.out.println("list before reversing " + first + "\nlist head " + first.getHead() +
                "\nlist tail " + first.getTail());
        reverseTheList(first);
        System.out.println("list after reversing " + first + "\nlist head " + first.getHead() +
                "\nlist tail " + first.getTail());
    }

    // here we are interchanging the head and tail
    private static <T> void reverseTheList(SinglyLinkedList<T> first){
        first.setTail(first.getHead());
        first.setHead(generateReverseLinkedList(first.getHead()));
    }

    private static <T> SinglyLinkedList.node<T> generateReverseLinkedList(node<T> current){
        node<T> prev = null;
        node<T> next;

        /*
            here there are three pointers prev, curr and next
            prev is behind curr and curr is behind next

            first save the next pointer of current
            the set the next pointer of current to previous
            make the current to new previous and next to new current
         */
        while(current != null){
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        return prev;
    }
}
