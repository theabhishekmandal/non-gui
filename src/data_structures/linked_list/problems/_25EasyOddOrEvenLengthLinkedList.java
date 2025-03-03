package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

public class _25EasyOddOrEvenLengthLinkedList {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Random random = new Random();
        IntStream.range(0, random.nextInt(10)).forEach(first::addLast);
        System.out.println(first);
        System.out.println(getLength(first));
    }

    /*
        Using a 2x pointer that will determine if a given linked list is
        even length or odd length
     */
    private static <T> String getLength(SinglyLinkedList<T> first){
        String[] arr = {"even", "odd"};
        Node<T> fast = first.getHead();
        while(fast != null && fast.getNext() != null){
            fast = fast.getNext().getNext();
        }
        if(fast == null) return arr[0];
        return arr[1];
    }
}
