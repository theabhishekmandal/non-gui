package data_structures.linked_list.problems;

import data_structures.linked_list.node.DoublyLinkedList;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static data_structures.linked_list.node.DoublyLinkedList.Node;

/**
 * Given a sorted Dll and a value k find pairs such that their sum is equal to k
 * Approach:
 *  -   as the list is sorted, initialise the first pointer as head and the last pointer as tail
 *  -   now for each first and last pointer combination, check if first.data + last.data == k
 *  -   if both sum equal then add the pairs to list and move
 *  -   if sum is greater then move the last pointer
 *  -   otherwise move the first pointer
 */
public class _12EasyFindPairsInDll {
    public static void main(String[] args) {
        var dl = new DoublyLinkedList<Integer>();
        Random random = new Random();
        random.ints(0, 20).limit(10).sorted().forEach(dl::addLast);
        int k = 1 + random.nextInt(20);
        System.out.println("list is = " + dl + " k = " + k);
        var list = getPairs(dl, k);
        System.out.println("list of pairs are = " + list);
    }

    private static List<SimpleEntry<Integer, Integer>> getPairs(DoublyLinkedList<Integer> dl, int k) {
        if(dl == null || dl.getHead() == null) {
            return Collections.emptyList();
        }
        Node<Integer> first = dl.getHead();
        Node<Integer> last = dl.getTail();

        var list = new ArrayList<SimpleEntry<Integer, Integer>>();
        while(first.getData() < last.getData()) {
            int temp = first.getData() + last.getData();
            if(temp == k) {
                list.add(new SimpleEntry<>(first.getData(), last.getData()));
                first = first.getNext();
                last = last.getPrevious();
            }
            else if(temp > k) {
                last = last.getPrevious();
            }
            else {
                first = first.getNext();
            }
        }
        return list;
    }
}
