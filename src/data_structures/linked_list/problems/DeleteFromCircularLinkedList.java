package data_structures.linked_list.problems;

import data_structures.linked_list.node.CircularLinkedList;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static data_structures.linked_list.node.CircularLinkedList.Node;

/**
 * Given a circular linked list and a value k, delete a node of value k from the linked list
 * Approach
 *  -   if size is one and the head.data matches the value then make the linked list null and return, otherwise return without modifying
 *      the list
 *  -   Now starting from first node check if the next node value matches the value of k, do this till we reach the head again
 *      Otherwise, we will be in infinite loop
 *  -   even after the loop the value does not match then return without modifying the list
 *  -   if the value matches then there would be three cases
 *          -   if the value to be deleted is tail
 *              -   In this case if temp.next is tail which is also the value to be deleted, then first change temp.next = tail.next,
 *                  tail.next = null and make temp as the new tail
 *          -   if the value to be deleted is head
 *              -   In this case if temp.next is head which is also the value to be deleted, then first change temp.next = head.next,
 *                  head.next = null and make temp.next as new head.
 *
 */
public class DeleteFromCircularLinkedList {
    public static void main(String[] args) {
        CircularLinkedList<Integer> cl = new CircularLinkedList<>();
        Random random = new Random();
        random.ints(0, 20).limit(10).forEach(cl::addLast);
        List<Integer> list = Arrays.asList(cl.getHead().getData(), cl.getTail().getData(), cl.getHead().getNext().getData());
        for(int k : list) {
            System.out.println("Before deletion " + cl + " head is = " + cl.getHead() + " tail is = " + cl.getTail() + " tail.next = " + cl.getTail().getNext());
            System.out.println("element to be deleted = " + k);
            deleteNode(cl, k);
            System.out.println("After deletion " + cl + " head is = " + cl.getHead() + " tail is = " + cl.getTail() + " tail.next = " + cl.getTail().getNext());
            System.out.println();
        }
    }

    private static <T> void deleteNode(CircularLinkedList<T> cl, T k) {
        if (cl == null || cl.getHead() == null) {
            return;
        }
        if (cl.getSize() == 1) {
            if(cl.getHead().getData().equals(k)) {
                // reset
                cl.setHead(null);
                cl.setTail(null);
                cl.setSize(0);
            }
            return;
        }
        Node<T> temp = cl.getHead();
        while (!temp.getNext().getData().equals(k) && temp.getNext() != cl.getHead()) {
           temp = temp.getNext();
        }
        if (!temp.getNext().getData().equals(k)) {
            return;
        }
        Node<T> toBeDel = temp.getNext();
        Node<T> tail = cl.getTail();
        Node<T> head = cl.getHead();

        temp.setNext(toBeDel.getNext());
        toBeDel.setNext(null);
        if (toBeDel == tail) {
            cl.setTail(temp);
        } else if (toBeDel == head) {
            cl.setHead(temp.getNext());
        }
        cl.setSize(cl.getSize() - 1);
    }
}
