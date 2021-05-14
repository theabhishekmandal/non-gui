package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 *  Given a singly linked list, find the last element from the beginning whose n % k == 0
 *  where n is the number of elements in the list and k is an integer constant.
 *
 *  Basically find the node whose's position is multiple of k in a list of length n
 *
 *  if 2->4->3->8->9->10->11 and k = 2,
 *  6th position node is the last node that is divisible by 2 so answer is 10
 *
 *  Approach,
 *      take the counter = 1 and head, whenever counter % k == 0 get that location node
 *      do this till the end of the list.
 */
public class FindModularNode {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Random random = new Random();
        int n = random.nextInt(10) + 1;
        int k = random.nextInt(10);
        IntStream.range(1, n).forEach(x -> first.addLast(random.nextInt(n)));

        System.out.println(first);
        Node<Integer> node = findModularNode(first, k);
        System.out.println(node);
        Node<Integer> node1 = findModularNode1(first, k);
        System.out.println(node1);
    }

    // if size of the list is given
    private static <T> Node<T> findModularNode(SinglyLinkedList<T> first, int k){
        int length = first.getSize();
        int pos = (length / k) * k;
        return first.getNode(pos);
    }

    // if size of the list is not given
    private static <T> Node<T> findModularNode1(SinglyLinkedList<T> first, int k){
        Node<T> firstnode = first.getHead();
        Node<T> modularNode = null;
        int i = 1;
        while(firstnode != null) {
            if (i % k == 0) {
                modularNode = firstnode;
            }
            i++;
            firstnode = firstnode.getNext();
        }
        return modularNode;
    }
}
