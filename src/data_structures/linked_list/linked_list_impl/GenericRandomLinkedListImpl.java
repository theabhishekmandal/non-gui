package data_structures.linked_list.linked_list_impl;

import data_structures.linked_list.node.RandomLinkedList;

import java.util.stream.IntStream;

public class GenericRandomLinkedListImpl {
    public static void main(String[] args) {
        RandomLinkedList<Integer> randomLinkedList = new RandomLinkedList<>();
        IntStream.range(1, 10).forEach(randomLinkedList::addLast);
        randomLinkedList.assignRandomPointers();
        System.out.println(randomLinkedList);
    }
}
