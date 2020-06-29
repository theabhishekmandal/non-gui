package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;
import static DataStructures.linked_list.Node.SinglyLinkedList.node;

import java.util.*;
import java.util.stream.IntStream;

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
public class keepNonDuplicates {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> sll = getSortedLinkedListWithDuplicates();
        System.out.println(sll);
        keepDistinct(sll);
        System.out.println(sll);
    }

    private static void keepDistinct(SinglyLinkedList<Integer> sll) {
        if(sll == null || sll.getSize() == 0) return;
        node<Integer> head = null;
        node<Integer> tail = null;
        node<Integer> nex;

        node<Integer> temp = sll.getHead();
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
        for(int ignored : IntStream.range(0, 20).toArray()){
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
