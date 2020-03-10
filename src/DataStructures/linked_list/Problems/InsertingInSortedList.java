package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;
import static DataStructures.linked_list.Node.SinglyLinkedList.node;

import java.util.Random;
import java.util.stream.IntStream;

public class InsertingInSortedList {
    public static void main(String[] args) {
        Random random = new Random();
        SinglyLinkedList<Integer> sll = new SinglyLinkedList<>();
        IntStream.range(1, 11).forEach(sll::addLast);
        System.out.println("list before insertion "  + sll);
        insertNodeInSortedList(sll, random.nextInt(10));
        System.out.println("list before insertion "  + sll);
    }

    private static <T extends Comparable<? super T>> void insertNodeInSortedList(SinglyLinkedList<T> sll, int nextInt) {
        if(sll == null || sll.getHead() == null) return;

       node<T> temp = sll.getHead();
       node<T> prev = null;
       while(temp != null){

           temp = temp.getNext();
       }
    }
}
