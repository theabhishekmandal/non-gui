package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.Comparator;
import java.util.stream.IntStream;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 * Given a linked list and a value k, partition it such that all nodes less than k come before nodes greater than or
 * equal to K.
 * eg: 1, 4, 3, 2, 5, 2 and k = 3, return 1, 2, 2, 4, 3, 5
 */

public class _26EasyPartitionNodesLessThanK {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();

        IntStream.range(0, 10).boxed().sorted(Comparator.reverseOrder()).forEach(first::addLast);
        Integer k = 5;

        System.out.println("first list is " + first + " k is " + k);
        partitionNodesLessThanK(k, first);
        System.out.println("first list is " + first);
    }

    private static <T extends Comparable<? super T>> void partitionNodesLessThanK(T key, SinglyLinkedList<T> first) {
        if (first == null) return;
        Node<T> temp = first.getHead();
        Node<T> next = null;

        Node<T> nodeHeadLessThanK = null;
        Node<T> nodeTailLessThanK = null;

        Node<T> nodeHeadGreaterThanK = null;
        Node<T> nodeTailGreaterThanK = null;

        while (temp != null) {
            next = temp.getNext();
            if (temp.getData().compareTo(key) >= 0) {
                if (nodeHeadGreaterThanK == null) {
                    nodeHeadGreaterThanK = temp;
                } else {
                    nodeTailGreaterThanK.setNext(temp);
                }
                nodeTailGreaterThanK = temp;
            } else {
                if (nodeHeadLessThanK == null) {
                    nodeHeadLessThanK = temp;
                } else {
                    nodeTailLessThanK.setNext(temp);
                }
                nodeTailLessThanK = temp;
            }
            temp.setNext(null);
            temp = next;
        }
        // if number of elements less than k does not exists then this null check is important
        if (nodeTailLessThanK != null) {
            nodeTailLessThanK.setNext(nodeHeadGreaterThanK);
            first.setHead(nodeHeadLessThanK);
        } else {
            first.setHead(nodeHeadGreaterThanK);
        }
        first.setTail(nodeTailGreaterThanK);
    }
}
