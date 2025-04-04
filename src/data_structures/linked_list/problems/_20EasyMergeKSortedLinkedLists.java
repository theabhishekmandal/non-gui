package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 * Given n number of sorted linked lists, merge them into a single sorted linked lists
 * <p>
 * Approach
 * -   The approach is similar to merging 2 sorted linked lists multiplied by n - 1 times
 */
public class _20EasyMergeKSortedLinkedLists {
    private static final Random random = new Random();

    public static void main(String[] args) {
        var list = getSortedArrayOfLinkedList();

        System.out.println("array list before merging");
        list.forEach(System.out::println);

        System.out.println("\n merge lists" + solve(list) + "\n");

        System.out.println("array list after merging");
        list.forEach(System.out::println);
    }

    private static <T extends Comparable<? super T>> SinglyLinkedList<T> solve(List<SinglyLinkedList<T>> lists) {
        if (lists == null) {
            return null;
        }
        if (lists.size() == 1) {
            return lists.get(0);
        }

        // first list will be used as a input
        var first = lists.get(0);
        for (int i = 1; i < lists.size(); i++) {
            merge(first, lists.get(i));
        }
        var newList = new SinglyLinkedList<T>();
        newList.setHead(first.getHead());
        newList.setTail(first.getTail());

        // making the reference null
        first.setHead(null);
        first.setTail(null);

        return newList;
    }
    /*
        1 3 5 7 9 11
        2 4 6 8 10 12
     */

    private static <T extends Comparable<? super T>> SinglyLinkedList<T> merge(SinglyLinkedList<T> first, SinglyLinkedList<T> second) {
        var temp = first.getHead();
        var temp2 = second.getHead();

        Node<T> head = null;
        Node<T> tail = null;

        // making the reference null as we don't want to keep the reference
        second.setHead(null);
        second.setTail(null);

        while (temp != null && temp2 != null) {
            Node<T> toBeAdded;
            if (temp.getData().compareTo(temp2.getData()) >= 0) {
                toBeAdded = temp2;
                temp2 = temp2.getNext();
            } else {
                toBeAdded = temp;
                temp = temp.getNext();
            }
            toBeAdded.setNext(null);
            if (head == null) {
                head = tail = toBeAdded;
            } else {
                tail.setNext(toBeAdded);
                tail = toBeAdded;
            }
        }

        // if somehow second list is greater than first list
        if (temp == null && temp2 != null) {
            temp = temp2;
        }

        // checking if any node remains to be visited
        while (temp != null) {
            var toBeAdded = temp;
            temp = temp.getNext();
            toBeAdded.setNext(null);
            if (head == null) {
                head = tail = toBeAdded;
            } else {
                tail.setNext(toBeAdded);
                tail = toBeAdded;
            }
        }

        // assigning the new reference
        first.setHead(head);
        first.setTail(tail);

        return first;
    }

    // method to generate sorted lists of single linked list
    private static List<SinglyLinkedList<Integer>> getSortedArrayOfLinkedList() {
        var list = new ArrayList<SinglyLinkedList<Integer>>();
        int size = 2 + random.nextInt(5);
        int startRange = 0;
        int offSet = 10;
        for (int i = 0; i < size; i++) {
            var linkedList = new SinglyLinkedList<Integer>();
            random.ints(startRange, startRange + offSet)
                    .limit(random.nextInt(10)).sorted().forEach(linkedList::addLast);
            list.add(linkedList);
            startRange = startRange + offSet;
        }
        Collections.shuffle(list);
        return list;
    }
}
