package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 * Find common elements in two linked list.
 * Find common elements in two sorted linked list.
 */

public class _27EasyPrintCommonElements {
    public static void main(String[] args) {
        Random random = new Random();
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> second = new SinglyLinkedList<>();
        for(int i = 0; i < 20; i++){
            first.addLast(random.nextInt(20));
            second.addLast(random.nextInt(20));
        }
        System.out.println("first list is " + first);
        System.out.println("second list is" + second);
        findCommonElements(first, second);

        first = new SinglyLinkedList<>();
        first.addLast(2);
        first.addLast(3);
        first.addLast(4);
        first.addLast(4);
        first.addLast(5);
        second = new SinglyLinkedList<>();
        second.addLast(1);
        second.addLast(2);
        second.addLast(4);
        second.addLast(4);
        second.addLast(5);
        System.out.println("first list is " + first);
        System.out.println("second list is" + second);
        findCommonElementsInSortedLinkedList(first, second);
    }

    private static <T extends Comparable<? super T>> void findCommonElements(SinglyLinkedList<T> first, SinglyLinkedList<T> second){
        if(first == null || second == null) return;
        Set<T> set = new HashSet<>();
        Node<T> node = first.getHead();
        while(node != null){
            set.add(node.getData());
            node = node.getNext();
        }
        node = second.getHead();
        StringBuilder br = new StringBuilder("[");
        while(node != null){
            if(set.contains(node.getData())){
                br.append(node.getData()).append(", ");
            }
            node = node.getNext();
        }
        br.append("]");
        System.out.println(br);
    }

    private static <T extends Comparable<? super T>> void findCommonElementsInSortedLinkedList(SinglyLinkedList<T> first,
                                                                                               SinglyLinkedList<T> second){
        if(first == null || second == null) return;

        SinglyLinkedList<T> duplicatesList = new SinglyLinkedList<>();
        Node<T> firstHead = first.getHead();
        Node<T> secondHead = second.getHead();

        while(firstHead != null && secondHead != null){
            if(firstHead.getData().compareTo(secondHead.getData()) == 0){
                duplicatesList.addLast(firstHead.getData());
                firstHead = firstHead.getNext();
                secondHead = secondHead.getNext();
            }
            else if(firstHead.getData().compareTo(secondHead.getData()) > 0){
                secondHead = secondHead.getNext();
            }
            else
                firstHead = firstHead.getNext();
        }
        System.out.println(duplicatesList);
    }
}
