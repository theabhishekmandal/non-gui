package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;
import static DataStructures.linked_list.Node.SinglyLinkedList.node;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 *  Given a singly linked list, find the last element from the beginning whose n % k == 0
 *  where n is the number of elements in the list and k is an integer constant.
 *
 *  Basically find the node whose's position is multiple of k in a list of length n
 */
public class findModularNode {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt() + 1;
        int k = s.nextInt();
        IntStream.range(1, n).forEach(first::addLast);

        System.out.println(first);
        node<Integer> node = findModularNode(first, k);
        System.out.println(node);
        node<Integer> node1 = findModularNode1(first, k);
        System.out.println(node1);
    }

    // if size of the list is given
    private static <T> node<T> findModularNode(SinglyLinkedList<T> first, int k){
        int length = first.getSize();
        int pos = (length / k) * k;
        node<T> node = first.getNode(pos);
        return node;
    }

    // if size of the list is not given
    private static <T> node<T> findModularNode1(SinglyLinkedList<T> first, int k){
        node<T> firstnode = first.getHead();
        node<T> modularNode = null;
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
