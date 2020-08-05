package dataStructures.linkedList.problems;

import dataStructures.linkedList.node.SinglyLinkedList;
import static dataStructures.linkedList.node.SinglyLinkedList.node;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 *  To remove duplicates from linked list, either by looping or by using hashing technique.
 *  - removeDuplicatesInSortedList : this method will work in sorted linked list
 *  - removeDuplicatesByHashing : this method will work in non sorted linked list
 *  Approach
 *      For a given node check if the next node is in the set or not,
 *      if it is in the set then set the current node next to next node's next
 */

public class RemoveDuplicates {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        IntStream.range(0, 5).forEach(first::addLast);
        IntStream.range(0, 5).forEach(first::addLast);
        System.out.println("list before removing duplicates " + first);
        removeDuplicatesByHashing(first);
        System.out.println("list after removing duplicates " + first);
    }

    private static <T extends Comparable<? super T>> void removeDuplicatesInSortedList(SinglyLinkedList<T> first){
        if(first == null || first.getHead().getNext() == null) return;
        node<T> newHead = first.getHead();
        node<T> newHeadPointer = newHead;
        node<T> nodeToTrackTail = null;

        while(newHeadPointer != null){
            nodeToTrackTail = newHeadPointer;
            node<T> temp = newHeadPointer.getNext();

            node<T> nex = null;
            while(temp != null && newHeadPointer.getData().compareTo(temp.getData()) == 0){
                nex = temp.getNext();
                temp.setNext(null);
                temp = nex;
            }

            newHeadPointer.setNext(temp);
            newHeadPointer = temp;
        }
        first.setHead(newHead);
        first.setTail(nodeToTrackTail);
    }

    private static <T extends Comparable<? super T>> void removeDuplicatesByHashing(SinglyLinkedList<T> first){
        if(first == null) return;
        Set<T> set = new HashSet<>();
        node<T> nodeToTrackTail = null;
        node<T> temp = first.getHead();
        node<T> nex = null;
        set.add(temp.getData());
        while(temp.getNext() != null){
            nodeToTrackTail = temp;
            nex = temp.getNext();
            if(set.contains(nex.getData())){
                temp.setNext(nex.getNext());
                nex.setNext(null);
            }
            else{
                set.add(nex.getData());
                temp = nex;
            }
        }
        first.setTail(nodeToTrackTail);
    }
}
