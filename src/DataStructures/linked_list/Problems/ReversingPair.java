package DataStructures.linked_list.Problems;

import DataStructures.linked_list.GenericSingleLinkedListImpl;
import DataStructures.linked_list.Node.SinglyLinkedList;

import java.util.stream.IntStream;
import static DataStructures.linked_list.Node.SinglyLinkedList.node;

/**
 * if linked list is 1->2->3->4 then the reversed list should be 2->1->4->3
 */
public class ReversingPair {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> arr = new SinglyLinkedList<>();
        IntStream.range(1, 4).forEach(arr::addLast);
        System.out.println("head is " + arr.getHead()  + "\nlinked list before reversing " + arr + "\ntail is " + arr.getTail());
        reverseInPair(arr);
        System.out.println("head is " + arr.getHead()  + "\nlinked list before reversing " + arr + "\ntail is " + arr.getTail());
    }

    private static <T> void reverseInPair(SinglyLinkedList<T> arr) {
        node<T> first = arr.getHead();
        node<T> second = null;
        if(first.getNext() == null) return;

        // setting the second element as head
        arr.setHead(first.getNext());

        while(first != null && first.getNext() != null){

            if(second != null) second.setNext(first.getNext());

            second = first.getNext();
            first.setNext(second.getNext());
            second.setNext(first);

            second = first;
            first = first.getNext();
        }

        if(first == null) arr.setTail(second);
    }
}
