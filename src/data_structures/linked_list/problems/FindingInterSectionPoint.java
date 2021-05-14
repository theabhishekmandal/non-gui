package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 * Given two linked list combined with each other, find the intersection point of the two lists.
 */

public class FindingInterSectionPoint {
    public static void main(String[] args) {
        var random = new Random();

        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        for (var i = 0; i < 5; i++) first.addLast(random.nextInt(100));

        SinglyLinkedList<Integer> second = new SinglyLinkedList<>();
        for (var i = 0; i < 5; i++) second.addLast(random.nextInt(100));

        SinglyLinkedList<Integer> merged = new SinglyLinkedList<>();
        for (var i = 0; i < 5; i++) merged.addLast(random.nextInt(100));

        System.out.println("first list without merging " + first);
        System.out.println("second list without merging " + second);
        System.out.println("list to be merged " + merged);

        // merging the first and second with the third one
        first.merge(merged);
        second.merge(merged);
        System.out.println("first list after merging " + first);
        System.out.println("second list after merging " + second);
        System.out.println("the intersection point is " + getIntersectionNodeUsingSet(first, second));
        System.out.println("the intersection point is " + getIntersectionNodeUsingArray(first, second));
        System.out.println("the intersection point is " + getIntersectionNodeUsingSingleScan(first, second));
        System.out.println("first list " + first);
        System.out.println("second list " + second);

    }

    // using two iterations
    /*
        Approach
            -   In two iterations approach scan the first list and put it in the set
            -   traverse the second list and check if the node is in the set, if it is found then return
     */
    private static <T> Node<T> getIntersectionNodeUsingSet(SinglyLinkedList<T> first,
                                                           SinglyLinkedList<T> second) {
        Set<Node<T>> firstSet = new HashSet<>();
        for (Node<T> temp = first.getHead(); temp != null; temp = temp.getNext()) {
            firstSet.add(temp);
        }
        Node<T> ans = null;
        for (Node<T> temp = second.getHead(); temp != null; temp = temp.getNext()) {
            if (firstSet.contains(temp)) {
                ans = temp;
                break;
            }
        }
        return ans;
    }

    // using three iterations
    /*
        Approach
            -   In three iterations approach, traverse both the list and add it to the array
            -   Now, find the length of the smaller list, and traverse both the array in reverse order
            -   return if the common node is found
     */
    private static <T> Node<T> getIntersectionNodeUsingArray(SinglyLinkedList<T> first,
                                                             SinglyLinkedList<T> second) {
        var firstArray = new Object[first.getSize()];
        Node<T> temp;
        Node<T> ans = null;
        int i;
        for (i = 0, temp = first.getHead(); i < firstArray.length; temp = temp.getNext(), i++) {
            firstArray[i] = temp;
        }

        var secondArray = new Object[second.getSize()];
        for (i = 0, temp = second.getHead(); i < secondArray.length; temp = temp.getNext(), i++) {
            secondArray[i] = temp;
        }

        int minimum = Math.min(firstArray.length, secondArray.length);
        for (i = 0; i < minimum; i++) {
            var firstNode = firstArray[firstArray.length - i - 1];
            var secondNode = secondArray[secondArray.length - i - 1];
            if (firstNode != secondNode) {
                //noinspection unchecked
                ans = (Node<T>) firstArray[firstArray.length - i];
                break;
            }
        }
        return ans;
    }

    // using single scan
    /*
       Approach
        -   if two lists are given then first find the length of the two lists
        -   take the difference d of the two lists and find which list is greater
        -   traverse till d distance in larger list, so as to make the pointer at equal distance in two lists.
        -   now traverse in both list one by one and return the common node when found
     */
    private static <T> Node<T> getIntersectionNodeUsingSingleScan(SinglyLinkedList<T> first,
                                                                  SinglyLinkedList<T> second) {
        var tempfirst = first;
        var tempSecond = second;
        int d;
        if (first.getSize() > second.getSize()) {
            d = first.getSize() - second.getSize();
        } else {
            d = second.getSize() - first.getSize();
            first = tempSecond;
            second = tempfirst;
        }
        Node<T> temp = first.getHead();
        for (var i = 0; i < d; i++) {
            temp = temp.getNext();
        }
        Node<T> tempanother = second.getHead();
        Node<T> ans = null;
        while (temp != null) {
            if (temp == tempanother) {
                ans = temp;
                break;
            }
            temp = temp.getNext();
            tempanother = tempanother.getNext();
        }
        return ans;
    }
}
