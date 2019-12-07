package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.GenericSinglyLinkedList;
import static DataStructures.linked_list.Node.GenericSinglyLinkedList.node;

import java.util.Random;
import java.util.stream.IntStream;

public class MergeTwoSortedLinkedLists {
    public static void main(String[] args) {
        GenericSinglyLinkedList<Integer> first = new GenericSinglyLinkedList<>();
        GenericSinglyLinkedList<Integer> second = new GenericSinglyLinkedList<>();
        Random random = new Random();
        IntStream.range(0, 15).forEach(first::addLast);
        IntStream.range(15, 20).forEach(second::addLast);
        System.out.println("first list " + first);
        System.out.println("second list " + second);
        GenericSinglyLinkedList<Integer> third = mergeTwoSortedLists(first, second);
        System.out.println("merged list " + third);
    }
    private static <T extends Comparable<? super T>> GenericSinglyLinkedList<T> mergeTwoSortedLists(GenericSinglyLinkedList<T> first,
                                                                      GenericSinglyLinkedList<T> second){
        GenericSinglyLinkedList<T> third = new GenericSinglyLinkedList<>();
        node<T> firsthead = first.getHead();
        node<T> secondhead = second.getHead();

        while(firsthead != null && secondhead != null){
            if(firsthead.getData().compareTo(secondhead.getData()) > 0){
                third.addLast(secondhead.getData());
                secondhead = secondhead.getNext();
            }
            else{
                third.addLast(firsthead.getData());
                firsthead = firsthead.getNext();
            }
        }

        if(firsthead == null && secondhead != null)
             firsthead = secondhead;

        while(firsthead != null){
            third.addLast(firsthead.getData());
            firsthead = firsthead.getNext();
        }
        return third;
    }
}
