package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.GenericSinglyLinkedList;

import java.util.ArrayList;
import java.util.Scanner;

public class FindingNthNodeFromEnd {
    private static <T> GenericSinglyLinkedList.node<?> getNthNodeFromEnd(GenericSinglyLinkedList<?> arr, int n){
        if(n > arr.getSize()) throw new IllegalStateException("Out of bounds");
        int nthNode = arr.getSize() - n + 1;
        GenericSinglyLinkedList.node<?> temp = null;
        for(int i = 0; i < nthNode; i++){
            if(temp == null){
                temp = arr.getHead();
                continue;
            }
            temp = temp.getNext();
        }
        return temp;
    }
    public static void main(String[] args) {
        GenericSinglyLinkedList<Integer> arr = new GenericSinglyLinkedList<>();
        for(int i = 0; i < 10; i++) arr.addLast(i);
        Scanner s = new Scanner(System.in);
        System.out.println("enter the nth node from the end that you want to find");
        int n = s.nextInt();
        System.out.println(getNthNodeFromEnd(arr, n));
    }
}