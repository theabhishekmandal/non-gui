package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;
import static DataStructures.linked_list.Node.SinglyLinkedList.node;

import java.util.Scanner;
import java.util.stream.IntStream;

public class findFractionalNode {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt() + 1;
        int k = s.nextInt();
        IntStream.range(1, n).forEach(first::addLast);
        node<Integer> node = findFractionalNode(first, k);
    }
    private static <T> node<T> findFractionalNode(SinglyLinkedList<T> first, int k){
        node<T> fractionalNode = null;
        node<T> temp = first.getHead();
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
