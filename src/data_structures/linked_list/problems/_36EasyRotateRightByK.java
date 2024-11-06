package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 * Given a linked list 1, 2, 3, 4, 5 and a value k is non-negative, rotate the list to the right by k places.
 * Output: 4, 5, 1, 2, 3
 */

public class _36EasyRotateRightByK {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Random random = new Random();
        int n = 1 + random.nextInt(10);
        int k = 1 + random.nextInt(n);

        System.out.println("rotate by " + k);
        System.out.println("list before k right rotation " + first);
        rotateRightByk(first, k);
        System.out.println("list after k right rotation " + first);
    }

    private static <T> void rotateRightByk(SinglyLinkedList<T> first, int k){
        if(first == null || k == 0 || first.getHead() == null) {
            return;
        }
        Node<T> temp = first.getHead();
        Node<T> tail = first.getTail();
        Node<T> tempTail = tail;
        int length = 0;
        for (var i = first.getHead(); i != null; i = i.getNext()) {
            length++;
        }

        // make the linked list circular.
        tail.setNext(temp);

        // for k right rotation, pointer should travel n-k
        for (int i = 0; i < length - k; i++) {
            temp = temp.getNext();
            tempTail = tempTail.getNext();
        }

        first.setHead(temp);
        first.setTail(tempTail);
        first.getTail().setNext(null);
    }
}
