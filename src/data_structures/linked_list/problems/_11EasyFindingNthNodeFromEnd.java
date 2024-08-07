package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.Random;

import static data_structures.linked_list.node.SinglyLinkedList.Node;
/**
 * Finding Nth node from end in single ll.
 * Approach:
 *      Move the first pointer till nth node, then place the second node to the head,
 *      move both the pointer till first pointer next != null
 */

public class _11EasyFindingNthNodeFromEnd {
    private static <T> Node<T> getNthNodeFromEnd2(SinglyLinkedList<T> arr, int n){
        if(arr == null || arr.getHead() == null) throw new NullPointerException();
        Node<T> first = arr.getHead();
        int counter = 1;
        Node<T> second = null;
        while(first.getNext() != null){
            if(counter == n){
                second = arr.getHead();
            }
            if(second != null) second = second.getNext();
            first = first.getNext();
            counter++;
        }
        return second;
    }
    public static void main(String[] args) {
        SinglyLinkedList<Integer> arr = new SinglyLinkedList<>();
        for(int i = 1; i < 10; i++) arr.addLast(i);
        Random random = new Random();
        int n = random.nextInt(10);
        System.out.println(arr);
        System.out.println("n = " + n);
        System.out.println("nth node from end is " + getNthNodeFromEnd2(arr, n));
    }
}
