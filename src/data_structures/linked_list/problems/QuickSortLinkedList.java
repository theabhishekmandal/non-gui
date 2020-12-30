package data_structures.linked_list.problems;

import data_structures.linked_list.node.Sl;
import static data_structures.linked_list.node.Sl.Node;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Given a linked list, apply quicksort to sort them
 * Approach
 *  -   To sort a linked list it can be done either by changing the links or by changing the values. Merge sort is done
 *      by changing the links
 *  -   In case of quicksort, changing of values is considered better option
 */

public class QuickSortLinkedList {

    private static final Random random = new Random();
    public static void main(String[] args) {
        Sl sl = new Sl();
        random.ints(0, 20).limit(10).forEach(sl::insert);
//        List<Integer> list = Arrays.asList(2, 5, 12, 6, 18, 15, 8 , 10, 3, 2).forEach(sl::insert);
        System.out.println(sl);
        quickSort(sl.head);
        System.out.println(sl);
    }

    private static void quickSort(Node head) {
       if(head == null || head.next == null) {
           return;
       }
       Node tail = head;
       while(tail.next != null) {
           tail = tail.next;
       }
       quickSort(head, tail);
    }

    private static void quickSort(Node head, Node tail) {
        // this will be the base condition
        if(head == tail) {
            return;
        }
        Node prev = partition(head, tail);
        quickSort(head, prev);
        quickSort(prev.next, tail);
    }

    // this is returning the previous node of the sorted node
    private static Node partition(Node head, Node end) {
        Node traver = head;
        Node prev = traver;
        int pivot = end.data;
        while(head != end) {
            if(head.data <= pivot) {
                prev = traver;
                swap(traver, head);
                traver = traver.next;
            }
            head = head.next;
        }
        swap(traver, end);
        return prev;
    }

    private static void swap(Node one, Node two) {
        int temp = one.data;
        one.data = two.data;
        two.data = temp;
    }
}
