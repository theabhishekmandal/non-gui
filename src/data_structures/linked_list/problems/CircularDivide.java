package data_structures.linked_list.problems;

import data_structures.linked_list.node.CircularLinkedList;
import static data_structures.linked_list.node.CircularLinkedList.Node;

import java.util.stream.IntStream;

/**
 *  Split the circular linked list in two parts. If the number of nodes in the list are odd then make first list one node
 *  extra than second list
 */

public class CircularDivide {
    public static void main(String[] args) {
        CircularLinkedList<Integer> arr = new CircularLinkedList<>();
        IntStream.range(1, 6).forEach(arr::addLast);

        System.out.println("head is " + arr.getHead()  + "\nlinked list before reversing " + arr + "\ntail is " + arr.getTail()
            + "\ntail next is" + arr.getTail().getNext() + "\ntotal elements " + arr.getSize());
        CircularLinkedList<Integer> second = divideCircularList(arr, new CircularLinkedList<>());

        System.out.println("\nhead is " + arr.getHead()  + "\nlinked list before reversing " + arr + "\ntail is " + arr.getTail()
                + "\ntail next is" + arr.getTail().getNext() + "\ntotal elements " + arr.getSize());

        System.out.println("\nhead is " + second.getHead()  + "\nlinked list before reversing " + second + "\ntail is " + second.getTail()
                + "\ntail next is" + second.getTail().getNext() + "\ntotal elements " + second.getSize());
    }
    private static <T> CircularLinkedList<T> divideCircularList(CircularLinkedList<T> first, CircularLinkedList<T> second){
        Node<T> slow = first.getHead();
        Node<T> fast = first.getHead();
        boolean isCycle = false;
        while(fast != null && fast.getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if(slow == fast){
                isCycle = true;
                break;
            }
        }
        if(!isCycle) return second;

        int length = 0;
        do{
            fast = fast.getNext();
            length++;
        }while(fast != slow);

        int firstLength;
        int secondLength;
        firstLength = ((length & 1) != 0)? (length + 1) >> 1 : length >> 1;
        secondLength = length - firstLength;

        Node<T> pointer = first.getHead();
        for(int i = 0; i < firstLength - 1; i++) pointer = pointer.getNext();


        second.setHead(pointer.getNext());
        second.setTail(first.getTail());
        second.getTail().setNext(pointer.getNext());
        second.setSize(secondLength);

        first.setTail(pointer);
        first.getTail().setNext(first.getHead());
        first.setSize(firstLength);

        return second;
    }
}
