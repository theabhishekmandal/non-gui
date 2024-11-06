package data_structures.linked_list.problems;

import data_structures.linked_list.node.DoublyLinkedList;

import java.util.function.Function;
import java.util.stream.IntStream;

import static data_structures.linked_list.node.DoublyLinkedList.Node;

/**
 * Given a doubly linked list and a value k, reverse k nodes.
 *
 */
public class _37MediumRotateRightByKInDll {
    public static void main(String[] args) {
        var dl = new DoublyLinkedList<Integer>();
        IntStream.range(1, 11).forEach(dl::addLast);
        int k = 3;
        System.out.println(dl + " k is = " + k);
        solve(dl, k);
        System.out.println(dl);
        System.out.println(verify.apply(dl));
    }

    static Function<DoublyLinkedList<Integer>, String> verify = x -> {
        Node<Integer> tail = x.getTail();
        StringBuilder br = new StringBuilder();
        while(tail != null) {
            br.append(tail.getData()).append(" ");
            tail = tail.getPrevious();
        }
        return br.toString();
    };


    private static <T> Node<T> getKthNode(Node<T> curr, int k) {
        Node<T> kthNode = curr;
        for(int i = 1; i < k; i++) {
            kthNode = kthNode.getNext();
        }
        return kthNode;
    }

    private static <T> void solve(DoublyLinkedList<T> dl, int rotation) {
        if (dl == null || dl.getHead() == null || rotation == 0) {
            return;
        }

        int length = dl.getSize();
        Node<T> curr = dl.getHead();
        if(curr.getNext() == null) return;
        Node<T> prev = null;
        Node<T> previous = null;
        Node<T> next = null;

        while (length >= rotation) {
            Node<T> kthNode = getKthNode(curr, rotation);
            Node<T> rightLinkNode = kthNode.getNext();

            // to link two kth nodes list
            if(previous != null) {
                previous.setNext(kthNode);
            }
            kthNode.setPrevious(previous);

            // prev variable to link next when pointer is not at end points
            prev = previous;
            for (int i = 1; i <= rotation; i++) {
                next = curr.getNext();
                if(i == 1) {
                   curr.setNext(rightLinkNode);
                   previous = curr;
                }
                else {
                   curr.setNext(prev);
                }

                // if i == rotation means kth node reached, no need to set previous
                if(i != rotation) {
                   curr.setPrevious(next);
                }
                prev = curr;
                curr = next;
            }

            // separated if statement to handle condition when rotation is of length of list
            if (length == dl.getSize()) {
                dl.setHead(prev);
            }

            if(length - rotation < rotation) {
                // if current is not null that means list is not divisible so don't change tail
                // otherwise set the tail
                if(curr != null) {
                    curr.setPrevious(previous);
                }
                else {
                    dl.setTail(previous);
                }
                return;
            }
            length -= rotation;
        }
    }
}
