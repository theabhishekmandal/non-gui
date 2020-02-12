package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;
import static DataStructures.linked_list.Node.SinglyLinkedList.node;

import java.util.Scanner;
/**
 * Finding Nth node from end in single ll.
 * Approach:
 *      Move the first pointer till nth node, then place the second node to the head,
 *      move both the pointer till first pointer next != null
 */

public class FindingNthNodeFromEnd {
    private static <T> SinglyLinkedList.node<T> getNthNodeFromEnd2(SinglyLinkedList<T> arr, int n){
        if(arr == null || arr.getHead() == null) throw new NullPointerException();
        node<T> first = arr.getHead();
        int counter = 1;
        node<T> second = null;
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
        Scanner s = new Scanner(System.in);
        System.out.println("enter the nth node from the end that you want to find");
        int n = s.nextInt();
        System.out.println(arr);
        System.out.println(getNthNodeFromEnd2(arr, n));
    }
}
