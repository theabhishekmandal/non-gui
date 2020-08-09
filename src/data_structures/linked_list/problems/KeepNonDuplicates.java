package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 * Given a sorted singly linked list, remove all the duplicates i.e keep only the distinct elements in the list
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 *
 * Approach
 *     -    We will use a flag isUnique to identify whether the given value is unique or not
 *     -    if we enter the inner while loop then it denotes that it is not a unique value, so we won't add it to the
 *          list
 *     -    else if it is unique value then we will create the new head and new tail and then at last set it to
 *          the singly linked list
 */
public class KeepNonDuplicates {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> sll = getSortedLinkedListWithDuplicates();
        System.out.println(sll);
        keepDistinct(sll);
        System.out.println(sll);
    }

    private static void keepDistinct(SinglyLinkedList<Integer> sll) {
        if(sll == null || sll.getSize() == 0) return;
        Node<Integer> head = null;
        Node<Integer> tail = null;
        Node<Integer> nex;

        Node<Integer> temp = sll.getHead();
        while(temp != null){
            boolean isUnique = true;
            while(temp.getNext() != null && temp.getData().compareTo(temp.getNext().getData()) == 0){
                isUnique = false;
                temp = temp.getNext();
            }
            nex = temp.getNext();
            if(isUnique){
                if(head == null){
                    head = temp;
                }
                else{
                    tail.setNext(temp);
                }
                temp.setNext(null);
                tail = temp;
            }
            temp = nex;
        }
        sll.setHead(head);
        sll.setTail(tail);
    }

    // to generate sorted duplicate linked list
    private static SinglyLinkedList<Integer> getSortedLinkedListWithDuplicates() {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for(var i = 0; i < 20; i++){
            boolean createDuplicate = random.nextBoolean();
            int val = random.nextInt(20);
            if(createDuplicate && !list.isEmpty()){
                int index = random.nextInt(list.size());
                list.add(list.get(index));
            }
            else{
                list.add(val);
            }
        }
        list.sort(Comparator.naturalOrder());
        SinglyLinkedList<Integer> sll = new SinglyLinkedList<>();
        list.forEach(sll::addLast);
        return sll;
    }
}
