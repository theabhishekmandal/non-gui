package data_structures.linked_list.problems;

import data_structures.linked_list.node.RandomLinkedList;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static data_structures.linked_list.node.RandomLinkedList.Node;

/**
 * clone a singly linked list in which there are random pointers that point to random nodes in the linked list
 * Approach:
 *  -   In first pass make a map of X and Y where X is the original node
 *      and Y is the cloned node having pointers set to null
 *  -   In second pass assign the pointers of Y by getting the value of X from map
 *
 */

public class _4EasyCloneRandomLinkedList {
    public static void main(String[] args) {
        RandomLinkedList<Integer> randomLinkedList = new RandomLinkedList<>();
        IntStream.range(1, 11).forEach(randomLinkedList::addLast);
        randomLinkedList.assignRandomPointers();
        System.out.println("first list " + randomLinkedList + "\nhead is " + randomLinkedList.getHead() +
                "\ntail is " + randomLinkedList.getTail());

        RandomLinkedList<Integer> randomLinkedList1 = cloneRandomLinkedList(randomLinkedList);
        System.out.println("second list " + randomLinkedList1 + "\nhead is " + randomLinkedList1.getHead() +
                "\ntail is " + randomLinkedList1.getTail());
    }

    private static <T> RandomLinkedList<T> cloneRandomLinkedList(RandomLinkedList<T> first){
        Map<Node<T>, Node<T>> map = new HashMap<>();
        Node<T> firstNode = first.getHead();
        RandomLinkedList<T> second = new RandomLinkedList<>();
        while(firstNode != null){
            Node<T> secondNode = new Node<>(firstNode.getData(), null);
            map.put(firstNode, secondNode);
            firstNode = firstNode.getNext();
        }
        firstNode = first.getHead();
        second.setHead(map.get(firstNode));
        second.setSize(first.getSize());
        Node<T> previous = null;

        while(firstNode != null){
            Node<T> secondNode = map.get(firstNode);
            secondNode.setNext(map.get(firstNode.getNext()));
            secondNode.setRandom(map.get(firstNode.getRandom()));
            previous = firstNode;
            firstNode = firstNode.getNext();
        }
        second.setTail(previous);
        return second;
    }
}
