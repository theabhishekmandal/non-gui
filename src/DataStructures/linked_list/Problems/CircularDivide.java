package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.CircularLinkedList;
import static DataStructures.linked_list.Node.CircularLinkedList.node;

import java.util.stream.IntStream;

/*
    Split the circular linked list in two parts. If the number of nodes in the list are odd then make first list one node
    extra than second list
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
        node<T> slow = first.getHead();
        node<T> fast = first.getHead();
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

        int firstlength, secondlength;
        firstlength = ((length & 1) != 0)? (length + 1) >> 1 : length >> 1;
        secondlength = length - firstlength;

        node<T> pointer = first.getHead();
        for(int i = 0; i < firstlength - 1; i++) pointer = pointer.getNext();


        second.setHead(pointer.getNext());
        second.setTail(first.getTail());
        second.getTail().setNext(pointer.getNext());
        second.setSize(secondlength);

        first.setTail(pointer);
        first.getTail().setNext(first.getHead());
        first.setSize(firstlength);

        return second;
    }
}
