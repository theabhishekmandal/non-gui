package data_structures.linked_list.problems;

import data_structures.linked_list.node.Sl;

import java.util.Arrays;
import java.util.Random;

import static data_structures.linked_list.node.Sl.Node;

/**
 * Given a linked list, apply quicksort to sort them
 * Approach
 * -   To sort a linked list it can be done either by changing the links or by changing the values. Merge sort is done
 * by changing the links
 * -   In case of quicksort, changing of values is considered better option
 */

public class _29MediumQuickSortLinkedList {

    private static final Random random = new Random();

    public static void main(String[] args) {
        Sl sl = new Sl();
//        random.ints(0, 20).boxed().limit(10).sorted(Comparator.reverseOrder())
//                .forEach(sl::insert);
        Arrays.asList(9, 8, 6, 3, 4, 2, 1, 5).forEach(sl::insert);
        System.out.println(sl);
        quickSort(sl.head);
        System.out.println(sl);
    }

    private static void quickSort(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        quickSort(head, tail);
    }

    private static void quickSort(Node head, Node tail) {
        // this will be the base condition
        if (head == tail) {
            return;
        }
        Node prev = partition(head, tail);
        quickSort(head, prev);
        quickSort(prev.next, tail);
    }

    // this is returning the previous node of the sorted node
    private static Node partition(Node head, Node end) {
        Node lessThanPivot, prev, current;
        prev = lessThanPivot = current = head;

        int pivot = end.data;
        /*
            -   Here for partitioning, we are using the last element as pivot.
            -   current pointer used to compare the current node with pivot.
            -   lessThanPivot pointer is used for identifying nodes which are less than pivot.
            -   prev denotes pointer behind pivot which needs to be sorted.
            -   at the end we will replace lessThanPivot to end, because
                -   on left all the elements will be smaller than pivot
                -   on rigth all the elements will be greater than pivot.
            -   Gist here is, all the elements which are smaller than pivot will be present in
                -   node head to node lessThanPivot.
            -   Also if lessThanPivot is not moving it means all the elements are greater than pivot. And at the end
                we will swap it with pivot
         */
        while(current != end) {
            if(current.data <= pivot) {
                prev = lessThanPivot;
                swap(lessThanPivot, current);
                lessThanPivot = lessThanPivot.next;
            }
            current = current.next;
        }
        swap(lessThanPivot, end);
        return prev;
    }

    private static void swap(Node one, Node two) {
        int temp = one.data;
        one.data = two.data;
        two.data = temp;
    }
}
