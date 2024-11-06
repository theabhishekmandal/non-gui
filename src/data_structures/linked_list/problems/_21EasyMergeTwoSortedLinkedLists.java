package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.stream.IntStream;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

public class _21EasyMergeTwoSortedLinkedLists {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> second = new SinglyLinkedList<>();
        IntStream.range(0, 15).forEach(first::addLast);
        IntStream.range(15, 20).forEach(second::addLast);
        System.out.println("first list " + first);
        System.out.println("second list " + second);
        SinglyLinkedList<Integer> third = mergeTwoSortedLists(first, second);
        System.out.println("merged list " + third);
    }
    private static <T extends Comparable<? super T>> SinglyLinkedList<T> mergeTwoSortedLists(SinglyLinkedList<T> first,
                                                                                             SinglyLinkedList<T> second){
        SinglyLinkedList<T> third = new SinglyLinkedList<>();
        Node<T> firstHead = first.getHead();
        Node<T> secondHead = second.getHead();

        while(firstHead != null && secondHead != null){
            if(firstHead.getData().compareTo(secondHead.getData()) > 0){
                third.addLast(secondHead.getData());
                secondHead = secondHead.getNext();
            }
            else{
                third.addLast(firstHead.getData());
                firstHead = firstHead.getNext();
            }
        }

        if(firstHead == null && secondHead != null) {
            firstHead = secondHead;
        }

        while(firstHead != null){
            third.addLast(firstHead.getData());
            firstHead = firstHead.getNext();
        }
        return third;
    }
}
