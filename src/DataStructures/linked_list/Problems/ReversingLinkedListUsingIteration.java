package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.GenericSinglyLinkedList;
import static DataStructures.linked_list.Node.GenericSinglyLinkedList.node;
import java.util.Random;
import java.util.stream.IntStream;

public class ReversingLinkedListUsingIteration {
    public static void main(String[] args) {
        GenericSinglyLinkedList<Integer> first = new GenericSinglyLinkedList<>();
        Random random = new Random();
        IntStream.range(0, random.nextInt(10)).forEach(first::addLast);
        System.out.println(first);
        System.out.println(generateReverseLinkedList(first));
    }

    private static <T> GenericSinglyLinkedList<T> generateReverseLinkedList(GenericSinglyLinkedList<T> first){
        node<T> current = first.getHead();
        node<T> prev = null;
        GenericSinglyLinkedList<T> second = new GenericSinglyLinkedList<>();
        second.setTail(current);
        node<T> next  = null;

        while(current != null){
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        second.setHead(prev);
        return second;
    }
}
