package data_structures.linked_list.problems;

import data_structures.linked_list.node.DoublyLinkedList;
import static data_structures.linked_list.node.DoublyLinkedList.Node;

import java.util.*;

/**
 * Given a sorted doubly linked list of distinct nodes(no two nodes have the same data) and a value x.
 * Count triplets in the list that sum up to a given value x.
 *
 * Approach:
 *  -   First fix the first element of the triplet
 *  -   Then fix two pointers, one at i + 1 and the other at n – 1. And look at the sum,
 *      -   If the sum is smaller than the required sum, increment the first pointer.
 *      -   Else, If the sum is bigger, Decrease the end pointer to reduce the sum.
 *      -   Else, if the sum of elements at two-pointer is equal to given sum then print the triplet and break.
 */
public class TripletSum {
    public static void main(String[] args) {
        var dl = new DoublyLinkedList<Integer>();
        Random random = new Random();
        random.ints(0, 20).limit(10).sorted().forEach(dl::addLast);
        int k = 1 + random.nextInt(20);
        System.out.println("list is = " + dl + " k = " + k);
        var list = getTriplets(dl, k);
        System.out.println("list of triplets are= " + list);
    }

    private static List<List<Integer>> getTriplets(DoublyLinkedList<Integer> dl, int k) {
        if (dl == null || dl.getHead() == null) {
            return Collections.emptyList();
        }

        Node<Integer> temp = dl.getHead();
        var list = new ArrayList<List<Integer>>();
        while (temp != dl.getTail()) {
            Node<Integer> next = temp.getNext();
            Node<Integer> last = dl.getTail();
            while (next.getData() < last.getData()) {
                int sum = temp.getData() + next.getData() + last.getData();
                if (sum == k) {
                    list.add(Arrays.asList(temp.getData(), next.getData(), last.getData()));
                    next = next.getNext();
                    last = last.getPrevious();
                }
                else if (sum > k) {
                    last = last.getPrevious();
                }
                else {
                    next = next.getNext();
                }
            }
            temp = temp.getNext();
        }
        return list;
    }
}
