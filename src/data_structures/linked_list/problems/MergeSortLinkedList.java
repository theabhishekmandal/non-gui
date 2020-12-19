package data_structures.linked_list.problems;

import data_structures.linked_list.node.Sl;
import static data_structures.linked_list.node.Sl.Node;


/**
 * Given a linked list sort them using merge sort. Time complexity - O(nlogn), Space complexity - O(1).
 * Here in normal merge sort, the space complexity is more than O(1). But, in case of linked list merge sort
 * can be done without using any extra space
 *
 * Approach
 *  -   In normal linked list we find the middle index, similarly, here we will find middle element using hare tortoise algorithm
 *      which is slightly modified.
 *
 *  -   As soon as we find the middle element first we save the middle.next, and make middle.next to null. This is an important
 *      step as it will allow us to follow divide and conquer strategy used in merge sort.
 *
 *  -   In mergesort the base condition was that if low > high then we stop, but here the base condition is if head is null or
 *      head.next is null then we return head. head.next == null check is important as we are dividing the list and there will
 *      be a point when a single element will remain. So in that case we require that condition.
 *
 *  -   We use this recursive method for head and the middleNext element and then we merge them both
 *  -   Note here sorting here is done by changing the links and not by changing the values, In quicksort method, changing the values
 *      will be used rather than changing the links.
 */
public class MergeSortLinkedList {
    public static void main(String[] args) {
        Sl sl = new Sl();
        sl.insert(9);
        sl.insert(8);
        sl.insert(7);
        sl.insert(6);
        sl.insert(1);

        Node ans = mergeSort(sl.head);
        StringBuilder br = new StringBuilder();
        while(ans != null) {
            br.append(ans.data).append("-->");
            ans = ans.next;
        }
        System.out.println(br);
    }

    private static Node mergeSort(Node head) {
        if(head == null || head.next == null) {
            return head;
        }
        Node middle = getMiddle(head);
        Node middleNext = middle.next;
        middle.next = null;

        Node left = mergeSort(head);
        Node right = mergeSort(middleNext);
        return merge(left, right);
    }

    private static Node merge(Node left, Node right) {
        if(left == null) {
            return right;
        }
        if(right == null) {
            return left;
        }
        Node result = null;
        Node temp = null;
        while(left != null && right != null) {
            Node nodeToBeAdded;
            if(left.data > right.data) {
                nodeToBeAdded = right;
                right = right.next;
            }
            else {
                nodeToBeAdded = left;
                left = left.next;
            }

            if(result == null) {
                result = nodeToBeAdded;
                temp = result;
            }
            else {
                temp.next = nodeToBeAdded;
                temp = temp.next;
            }
        }
        while(left != null) {
            temp.next = left;
            temp = temp.next;
            left = left.next;
        }

        while(right != null) {
            temp.next = right;
            temp = temp.next;
            right = right.next;
        }
        return result;
    }

    private static Node getMiddle(Node head) {
        Node hare = head;
        Node tortoise = head;
        // modified while check
        // hare != null && hare.next != null cannot be used here as it will return the second element always ex[1, 2]
        // we require 1 as middle element but we are getting 2
        while(hare.next != null && hare.next.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
        }
        return tortoise;
    }
}