package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;
import static DataStructures.linked_list.Node.SinglyLinkedList.node;

import java.util.Arrays;
import java.util.stream.IntStream;

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
        node<T> curr = first.getHead();
        if(curr.getNext() == null) return;
        node<T> prev = null;
        node<T> previous = null;
        node<T> next = null;
        boolean isLengthDivisibleByK = (length % k == 0);
        while(length >= k){
            prev = null;
            node<T> kthNode = getNode(curr, k);
            node<T> kthNextNode = kthNode.getNext();
            while(curr != kthNextNode){
                next = curr.getNext();
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
            if(length == first.getSize()){
                first.setHead(prev);
            }
            else if(isLengthDivisibleByK && length == k) {
                first.setTail(previous);
            }
            length -= k;
        }
    }

    private static <T> node<T> getNode(node<T> curr, int k) {
        node<T> temp = curr;
        while(k > 1){
            temp = temp.getNext();
            k--;
        }
        return temp;
    }
    private static void debug(Object...a){
        System.err.println(Arrays.deepToString(a));
    }
}
