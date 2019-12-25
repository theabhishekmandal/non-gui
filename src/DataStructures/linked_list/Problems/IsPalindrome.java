package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;
import  static DataStructures.linked_list.Node.SinglyLinkedList.node;

import java.util.stream.Stream;

/**
 * Check whether a given list is palindromic or not
 *
 * Approach:
 *      -   first find the mid element
 *      -   reverse the first half of the linked list
 *      -   Compare the first half with the second half
 *      -   reverse back the first half of the linked list
 */

public class IsPalindrome {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Stream.of(1, 2, 3, 1).forEach(first::addLast);
        System.out.println(first + " is" + (isPalindrome(first) ? " a " : " not a ") + "palindrome list");
        System.out.println(first);
    }

    private static <T extends Comparable<? super T>> boolean isPalindrome(SinglyLinkedList<T> temp) {
        int midlength = temp.getSize() >> 1;
        node<T> midpointer = temp.getHead();

        // for even length the midpointer will be midlength index node if indexing starts from 0
        // for odd length the midpointer will be the middle element
        for(int i = 0; i < midlength ; i++) midpointer = midpointer.getNext();

        reverseTillMiddleNode(temp, midpointer);

        node<T> firstPointer = temp.getHead();
        node<T> secondPointer = ((temp.getSize() & 1) == 0)? midpointer : midpointer.getNext();

        boolean isPalindrome = true;
        while(secondPointer != null){
            if(secondPointer.getData().compareTo(firstPointer.getData()) != 0){
                isPalindrome = false;
                break;
            }
            secondPointer = secondPointer.getNext();
            firstPointer = firstPointer.getNext();
        }

        reverseTillMiddleNode(temp, midpointer);
        return isPalindrome;
    }

    private static <T> void reverseTillMiddleNode(SinglyLinkedList<T> temp, node<T> midpointer){
        // prev is set to midpointer because the head will point to mid element
        node<T> prev = midpointer;
        node<T> next = null;
        node<T> curr = temp.getHead();

        while(curr != midpointer){
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        temp.setHead(prev);
    }
}
