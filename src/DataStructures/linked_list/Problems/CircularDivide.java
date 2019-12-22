package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.CircularLinkedList;
import static DataStructures.linked_list.Node.CircularLinkedList.node;

import java.util.stream.IntStream;

public class CircularDivide {
    public static void main(String[] args) {
        CircularLinkedList<Integer> arr = new CircularLinkedList<>();
        IntStream.range(1, 5).forEach(arr::addLast);

        System.out.println("head is " + arr.getHead()  + "\nlinked list before reversing " + arr + "\ntail is " + arr.getTail()
            + "\ntail next is" + arr.getTail().getNext() + "\ntotal elements " + arr.getSize());
        CircularLinkedList<Integer> second = divideCircularList(arr, new CircularLinkedList<>());

        System.out.println("\nhead is " + arr.getHead()  + "\nlinked list before reversing " + arr + "\ntail is " + arr.getTail()
                + "\ntail next is" + arr.getTail().getNext() + "\ntotal elements " + arr.getSize());

        System.out.println("\nhead is " + second.getHead()  + "\nlinked list before reversing " + second + "\ntail is " + second.getTail()
                + "\ntail next is" + second.getTail().getNext() + "\ntotal elements " + second.getSize());
    }
    private static <T> CircularLinkedList<T> divideCircularList(CircularLinkedList<T> first, CircularLinkedList<T> second){
        node<T> slow = first.getHead();
        node<T> fast = first.getHead();
        while(fast != null && fast.getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if(slow == fast) break;
        }
        return second;
    }
}
