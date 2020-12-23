package data_structures.linked_list.problems;

import data_structures.linked_list.node.DoublyLinkedList;
import static data_structures.linked_list.node.DoublyLinkedList.Node;

import java.util.PriorityQueue;
import java.util.stream.Stream;

/**
 * Given a doubly linked list containing n nodes, where each node is at most k away from its target position in the list.
 * The problem is to sort the given doubly linked list.
 * Same as SortAlreadySortedArray.java
 */
public class SortAlreadySortedDll {
    public static void main(String[] args) {
        var dl = new DoublyLinkedList<Integer>();
        Stream.of(6, 5, 3, 2, 8, 10, 9).forEach(dl::addLast);
        int k = 3;
        System.out.println("list before almost sorted " + dl + " distance is = " + k);
        sortAlmostSorted(dl, k);
        System.out.println("list after completely sorted " + dl);
    }

    private static void sortAlmostSorted(DoublyLinkedList<Integer> dl, int k) {
        if (dl == null || dl.getHead() == null) {
            return;
        }
        var heap = new PriorityQueue<Integer>();
        Node<Integer> first = dl.getHead();
        for (int i = 0; i < k + 1 && first != null; i++, first = first.getNext()) {
            heap.add(first.getData());
        }
        Node<Integer> second = dl.getHead();
        while (first != null) {
            if(!heap.isEmpty()) {
                second.setData(heap.poll());
            }
            heap.add(first.getData());
            second = second.getNext();
            first = first.getNext();
        }
        var iter = heap.iterator();
        while(iter.hasNext() && second != null) {
            if(!heap.isEmpty()) {
                second.setData(heap.poll());
            }
            second = second.getNext();
        }
    }
}
