package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.stream.IntStream;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 * Given a singly Linked List L: L1->L2->L3->...->Ln-1->Ln reorder it to:L1->Ln->L2->Ln-1->.....
 */
public class AlternateAdd {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        IntStream.range(1, 2).forEach(first::addLast);
        System.out.println("List before rordering\n" + first);
        reorderList(first);
        System.out.println("List after rordering\n" + first);
    }

    private static <T> void reorderList(SinglyLinkedList<T> first) {
        if(first == null) return;

        // finding the middle element of the linked list
        Node<T> slowPointer = first.getHead();
        Node<T> fastPointer = first.getHead();
        while(fastPointer != null && fastPointer.getNext() != null){
            slowPointer = slowPointer.getNext();
            fastPointer = fastPointer.getNext().getNext();
        }

        // reversing the right half of the list
        Node<T> curr = slowPointer;
        Node<T> prev = null;
        Node<T> nex = null;
        while(curr != null){
            nex = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = nex;
        }

        // now reordering the elements of the list
        Node<T> newHead = prev;
        Node<T> newHeadNext = null;
        Node<T> oldHead = first.getHead();
        Node<T> oldHeadNext = null;
        while(newHead != slowPointer){
            newHeadNext = newHead.getNext();
            oldHeadNext = oldHead.getNext();

            oldHead.setNext(newHead);
            newHead.setNext(oldHeadNext);

            oldHead = oldHeadNext;
            newHead = newHeadNext;
        }

        // setting the middle value as the tail of the list
        first.setTail(slowPointer);
    }
}
