package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;
import static data_structures.linked_list.node.SinglyLinkedList.Node;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Given a linked list 1 2 3 4 5 6 and a number k, reverse every k nodes if exists
 * if k = 2 then
 *      2 1 4 3 6 5
 * if k = 3 then
 *      3 2 1 6 5 4
 */
public class ReverseKNodes {
    public static void main(String[] args) {
        int k = 3;
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        IntStream.range(1, 10).forEach(first::addLast);
        System.out.println("list before reversing " + first);
        reverseListByKNodes(first, k);
        System.out.println("list after reversing " + first);
    }

    private static <T> void reverseListByKNodes(SinglyLinkedList<T> first, int k) {
        int length = first.getSize();
        Node<T> curr = first.getHead();
        if(curr.getNext() == null) return;
        Node<T> prev = null;
        Node<T> previous = null;
        Node<T> next = null;

        // if the current length is not divisible by k then
        // the last element will be the tail automatically
        boolean isLengthDivisibleByK = (length % k == 0);
        while(length >= k){
            prev = null;

            // getting the kth node and k+1th node
            Node<T> kthNode = getNode(curr, k);
            Node<T> kthNextNode = kthNode.getNext();
            while(curr != kthNextNode){

                // save the next pointer first
                // if next is calculated using next.getNext() then nullPointer will be thrown
                next = curr.getNext();

                // after reversing every k nodes prev will become null
                if(prev == null){
                    curr.setNext(kthNextNode);
                    if(previous != null) previous.setNext(kthNode);
                    previous = curr;
                }
                else
                    curr.setNext(prev);
                prev = curr;
                curr = next;
            }

            // for first iteration the given prev node will become head
            if(length == first.getSize()){
                first.setHead(prev);
            }
            // if length is divisible and last iteration then set the tail
            else if(isLengthDivisibleByK && length == k) {
                first.setTail(previous);
            }
            length -= k;
        }
    }

    private static <T> Node<T> getNode(Node<T> curr, int k) {
        Node<T> temp = curr;
        while(k > 1){
            temp = temp.getNext();
            k--;
        }
        return temp;
    }
}
