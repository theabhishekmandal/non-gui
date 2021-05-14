package data_structures.linked_list.problems;

import data_structures.linked_list.node.DoublyLinkedList;

import java.util.Random;

import static data_structures.linked_list.node.DoublyLinkedList.Node;

/**
 * Given a Doubly linked list. Reverse it's elements
 */
public class ReversingDoublyLinkedList {
    public static void main(String[] args) {
        var dl = new DoublyLinkedList<Integer>();
        Random random = new Random();
        random.ints(0, 20).limit(11).forEach(dl::addLast);
        System.out.println("list before reversing = " + dl);
        reverse(dl);
        System.out.println("list after reversing = " + dl);
    }

    private static <T> void reverse(DoublyLinkedList<T> list) {
        if(list == null || list.getHead() == null) {
            return;
        }
        Node<T> first = list.getHead();
        Node<T> last = list.getTail();
        int size = list.getSize() >> 1;
        for (int i = 0; i < size; i++) {
           T val = first.getData();
           first.setData(last.getData());
           last.setData(val);
           first = first.getNext();
           last = last.getPrevious();
        }
    }
}
