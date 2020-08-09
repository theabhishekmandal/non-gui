package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;
import  static data_structures.linked_list.node.SinglyLinkedList.Node;

import java.util.Arrays;

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
        SinglyLinkedList<Integer> first;
        String[] arr = {
                "1 2 1",
                "1 2 3",
                "1 2 2 1",
                "2 2 2 1"
        };
        for(String string : arr){
            first = new SinglyLinkedList<>();
            Arrays.stream(string.split(" ")).map(Integer::parseInt).forEach(first::addLast);
            System.out.println(first + " is" + (isPalindrome(first) ? " a " : " not a ") + "palindrome list");
        }
   }

    private static <T extends Comparable<? super T>> boolean isPalindrome(SinglyLinkedList<T> temp) {
        if(temp == null || temp.getHead() == null) return false;
        Node<T> fast = temp.getHead();
        Node<T> slow = temp.getHead();

        while(fast != null && fast.getNext() != null){
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }

        // if odd length then shift the slow node by one
        if(fast != null) {
            slow = slow.getNext();
        }

        slow = reverse(slow);

        boolean isPalindrome = true;
        Node<T> firstHalf = temp.getHead();
        Node<T> otherhalf = slow;
        while(firstHalf != null && otherhalf != null){
            if(firstHalf.getData().compareTo(otherhalf.getData()) != 0){
                isPalindrome = false;
                break;
            }
            firstHalf = firstHalf.getNext();
            otherhalf = otherhalf.getNext();
        }
        return isPalindrome;
    }
    private static <T> Node<T> reverse(Node<T> node){
        Node<T> prev = null;
        Node<T> nex = null;
        while(node != null){
            nex = node.getNext();
            node.setNext(prev);
            prev = node;
            node = nex;
        }
        return prev;
    }
}
