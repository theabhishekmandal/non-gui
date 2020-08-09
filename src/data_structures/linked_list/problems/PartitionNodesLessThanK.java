package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;
import static data_structures.linked_list.node.SinglyLinkedList.Node;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Given a linked list and a value k, partition it such that all nodes less than k come before nodes greater than or
 * equal to K.
 * eg: 1, 4, 3, 2, 5, 2 and k = 3, return 1, 2, 2, 4, 3, 5
 */

public class PartitionNodesLessThanK {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> second = new SinglyLinkedList<>();
        Random random = new Random();

        IntStream.range(0, 10).forEach(x -> first.addLast(random.nextInt(10)));
        second.copyAll(first);

        Integer k = random.nextInt(10);

        System.out.println("first list is " + first + " k is " + k);
        partitionNodesLessThanK(first, k);
        System.out.println("first list is " + first);

        partitionNodesLessThanK(k, second);
        System.out.println("second list is " + second);

    }

    /*
        Approach
            -   while traversing the list check whether the current node is smaller than k
            -   if it is smaller than k then add it to new list
            -   else keep track of these nodes using a pointer
     */
    private static <T extends Comparable<? super T>> void partitionNodesLessThanK(SinglyLinkedList<T> first, T k){
        if(first == null) return;

        Node<T> head = null;
        Node<T> tail = null;

        Node<T> nex = null;
        Node<T> prev = null;
        Node<T> temp = first.getHead();
        Node<T> headOfNodesGreaterOrEqualToK = null;

        while(temp != null){
            nex = temp.getNext();
            if(temp.getData().compareTo(k) < 0){
                if(head == null){
                    head = temp;
                }
                else{
                    tail.setNext(temp);
                }
                tail = temp;
                tail.setNext(null);
                if(prev != null)
                    prev.setNext(nex);
            }
            else{
                if(headOfNodesGreaterOrEqualToK == null)
                    headOfNodesGreaterOrEqualToK = temp;
                prev = temp;
            }
            temp = nex;
        }
        // if number of elements less than k does not exists then this null check is important
        if(tail != null){
            tail.setNext(headOfNodesGreaterOrEqualToK);
            first.setHead(head);
        }
        else
            first.setHead(headOfNodesGreaterOrEqualToK);
        first.setTail(prev);
    }
    private static <T extends Comparable<? super T>> void partitionNodesLessThanK(T key, SinglyLinkedList<T> first){
        if(first == null) return;
        Node<T> temp = first.getHead();
        Node<T> next = null;

        Node<T> nodeHeadLessThanK = null;
        Node<T> nodeTailLessThanK = null;

        Node<T> nodeHeadGreaterThanK = null;
        Node<T> nodeTailGreaterThanK = null;

        while(temp != null){
            next = temp.getNext();
            if(temp.getData().compareTo(key) >= 0){
                if(nodeHeadGreaterThanK == null){
                    nodeHeadGreaterThanK = temp;
                }
                else{
                    nodeTailGreaterThanK.setNext(temp);
                }
                nodeTailGreaterThanK = temp;
            }
            else{
                if(nodeHeadLessThanK == null){
                    nodeHeadLessThanK = temp;
                }
                else{
                    nodeTailLessThanK.setNext(temp);
                }
                nodeTailLessThanK = temp;
            }
            temp.setNext(null);
            temp = next;
        }
        // if number of elements less than k does not exists then this null check is important
        if(nodeTailLessThanK != null){
            nodeTailLessThanK.setNext(nodeHeadGreaterThanK);
            first.setHead(nodeHeadLessThanK);
        }
        else
            first.setHead(nodeHeadGreaterThanK);
        first.setTail(nodeTailGreaterThanK);
    }
}
