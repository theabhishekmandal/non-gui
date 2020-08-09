package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.Random;
import java.util.stream.IntStream;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 * Given n number of nodes and an integer k, find the first n / k element. Also length of the list is not known
 *
 * Approach:
 * Let us suppose there are n Number of nodes, then n / k will give the number of elements that satisfy i % k == 0 where
 * 1 <= i <= n, So n / k == number of i % k
 *
 * If after first search of  i % k == 0, we will assign a new pointer to head and will traverse when
 * next i % k == 0 will be found. After this way we can find the n / k
 */

public class FindFractionalNode {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Random random = new Random();
        int n = random.nextInt(20) + 1;
        int k = random.nextInt(n / 2);
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
