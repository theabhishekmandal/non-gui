package DataStructures.linked_list;

import DataStructures.linked_list.Node.RandomLinkedList;

import java.util.stream.IntStream;

public class GenericRandomLinkedListImpl {
    public static void main(String[] args) {
        RandomLinkedList<Integer> randomLinkedList = new RandomLinkedList<>();
        IntStream.range(1, 10).forEach(randomLinkedList::addLast);
        System.out.println(randomLinkedList);
    }
}
