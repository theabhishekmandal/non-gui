package data_structures.linked_list.problems;

import data_structures.linked_list.node.DoublyLinkedList;
import static data_structures.linked_list.node.DoublyLinkedList.Node;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Given a doubly linked list, rotate the linked list counter-clockwise by N nodes.
 * Here N is a given positive integer and is smaller than the count of nodes in linked list.
 *
 * Approach:
 *  -   First convert the dll to cll
 *  -   Then move each head and tail pointer by k times
 *  -   Then convert cll to dll and set the head and tail.
 */
public class RotateDllByNNodes {
    public static void main(String[] args) {
        var dl = new DoublyLinkedList<Integer>();
        var random = new Random();
        Stream.of(6, 5, 3, 2, 8, 10, 9).forEach(dl::addLast);
        random.ints(0, 20).limit(5).forEach(dl::addLast);
        int rotation = 1 + random.nextInt(5);
        System.out.println("list before almost sorted " + dl + " counter clockwise rotation is = " + rotation);
        rotateNNodes(dl, rotation);
        System.out.println("list after completely sorted " + dl);
    }

    private static <T> void rotateNNodes(DoublyLinkedList<T> dl, int rotation) {
        if (dl == null || dl.getHead() == null) {
            return;
        }
        Node<T> first = dl.getHead();
        Node<T> last = dl.getTail();
        last.setNext(first);
        first.setPrevious(last);
        for (int i = 0; i < rotation; i++) {
            first = first.getNext();
            last = last.getNext();
        }
        first.setPrevious(null);
        last.setNext(null);
        dl.setHead(first);
        dl.setTail(last);
    }
}
