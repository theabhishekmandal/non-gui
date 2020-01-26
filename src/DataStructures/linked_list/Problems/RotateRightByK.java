package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;
import static DataStructures.linked_list.Node.SinglyLinkedList.node;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Given a linked list 1, 2, 3, 4, 5 and a value k is non-negative, rotate the list to the right by k places.
 * Output: 4, 5, 1, 2, 3
 */

public class RotateRightByK {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Random random = new Random();
        int n = 1 + random.nextInt(10);
        IntStream.range(1, n).forEach(first::addLast);
        int k = 1 + random.nextInt(n);

        System.out.println("rotate by " + k);
        System.out.println("list before k right rotation " + first);
        rotateRightByk(first, k);
        System.out.println("list after k right rotation " + first);
    }
    private static <T> void rotateRightByk(SinglyLinkedList<T> first, int k){
        if(first == null || k == 0) return;
        node<T> temp = first.getHead();
        for(int i = 0; i < k; i++){
            temp = temp.getNext();
            if(temp == null) return;
        }
        node<T> curr = first.getHead();
        while(temp.getNext() != null){
            temp = temp.getNext();
            curr = curr.getNext();
        }

        node<T> nextHead = curr.getNext();
        node<T> newPointer = nextHead;
        first.setTail(curr);
        curr.setNext(null);
        while(newPointer.getNext() != null){
            newPointer = newPointer.getNext();
        }
        newPointer.setNext(first.getHead());
        first.setHead(nextHead);
    }
}
