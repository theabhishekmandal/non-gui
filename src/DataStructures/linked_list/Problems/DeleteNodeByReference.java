package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;
import static DataStructures.linked_list.Node.SinglyLinkedList.node;

import java.util.stream.IntStream;

/**
 * Given a reference of the node to be deleted, how would you delete it?
 * Initial approach would be to find the previous of the node that you want to delete, that you
 * can't perform in this situation.
 *
 * Second approach would be to copy the next element value to the current node to be deleted, and perform
 * this until the last of the node. After that remove the last node from the list.
 */
public class DeleteNodeByReference {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        IntStream.range(0, 11).forEach(first::addLast);
        int k = 5;
        deleteNode(first, k);
        System.out.println(first);

    }
    private static <T> void deleteNode(SinglyLinkedList<T> first, int k){
        int i = 1;
        node<T> temp = first.getHead();
        while(i <= k && temp != null){
            i++;
            temp = temp.getNext();
        }

        // copy all the elements
        while(temp != null && temp.getNext() != null){
            temp.setData(temp.getNext().getData());
            if(temp.getNext().getNext() == null){
                first.setTail(temp);
                temp.setNext(null);
            }
            temp = temp.getNext();
        }
    }
}
