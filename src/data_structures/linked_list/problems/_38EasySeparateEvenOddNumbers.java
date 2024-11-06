package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.stream.IntStream;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 * Given a linked list with even and odd numbers, reorder even number such that all the even numbers appear before
 * the linked list
 */

public class _38EasySeparateEvenOddNumbers {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        IntStream.range(1, 10).forEach(first::addLast);
        System.out.println("list before changes " + first);
        evenNumbersAtTheBeginning(first);
        System.out.println("list after changes " + first);
    }
    private static void evenNumbersAtTheBeginning(SinglyLinkedList<Integer> first){
        if(first == null) return;
        Node<Integer> newHead = first.getHead();
        Node<Integer> temp = first.getHead();
        while(temp.getNext() != null){
            Node<Integer> nex = temp.getNext();
            Integer value = nex.getData();
            if((value & 1) == 0){
                temp.setNext(nex.getNext());
                nex.setNext(newHead);
                newHead = nex;
            }
            else{
                first.setTail(temp);
                temp = nex;
            }
        }
        first.setHead(newHead);
    }
}
