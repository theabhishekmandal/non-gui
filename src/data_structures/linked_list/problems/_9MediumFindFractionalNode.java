package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 * Given n number of nodes and an integer k, find the first n / k element. Also length of the list is not known
 *
 * Approach:
 * Let us suppose there are n Number of nodes, then n / k will give i divisions. For eg 6/2 = 3, 6 can be divided in 3 parts.
 * Now, this 3 will be your third index element from the start.
 *
 *Input : list = 1->2->3->4->5->6
 *         k = 2
 * Output : 3
 * Since n = 6 and k = 2, we print (6/2)-th node
 * which is 3.
 *
 * Input : list = 2->7->9->3->5
 *         k = 3
 * Output : 7
 * Since n is 5 and k is 3, we print ceil(5/3)-th
 * node which is 2nd node, i.e., 7.
 */

public class _9MediumFindFractionalNode {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Random random = new Random();
        int n = random.nextInt(20) + 1;
        int k = 1 + random.nextInt(n / 2);
        IntStream.range(1, n).forEach(first::addLast);
        Node<Integer> node = findFractionalNode(first, k);
        System.out.println("n = " + n + " k = " + k);
        System.out.println(first);
        System.out.println(node);
    }
    private static <T> Node<T> findFractionalNode(SinglyLinkedList<T> first, int k){
        Node<T> fractionalNode = null;
        Node<T> temp = first.getHead();
        if(temp == null) return null;
        int i = 1;
        while(temp != null){
            if(i % k == 0){
                if(fractionalNode == null) fractionalNode = first.getHead();
                else fractionalNode = fractionalNode.getNext();
            }
            i++;
            temp = temp.getNext();
        }
        return fractionalNode;
    }
}
