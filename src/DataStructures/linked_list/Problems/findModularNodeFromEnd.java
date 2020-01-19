package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;
import static DataStructures.linked_list.Node.SinglyLinkedList.node;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 *  Problem statement:
 *  Find the first node from the end of the list that satisfies n % k == 0, where n is the index
 *  starting from 1 from end of the list.
 */

public class findModularNodeFromEnd {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt() + 1;
        int k = s.nextInt();
        IntStream.range(1, n).forEach(first::addLast);
        node<Integer> node = findModularNodeFromEnd(first, k);
        System.out.println(node);
    }

    private static <T> node<T> findModularNodeFromEnd(SinglyLinkedList<T> first, int k){
        node<T> modularNodeFromEnd = null;
        node<T> temp = first.getHead();
        if(temp == null) return null;
        int i = 0;
        while(temp != null){
            if(i < k) i++;
            else{
                if(modularNodeFromEnd == null) modularNodeFromEnd = first.getHead();
                else
                    modularNodeFromEnd = modularNodeFromEnd.getNext();
            }
            temp = temp.getNext();
        }
        return modularNodeFromEnd;
    }
}
