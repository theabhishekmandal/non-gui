package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;
import static DataStructures.linked_list.Node.SinglyLinkedList.node;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *  Given two linked list combined with each other, find the intersection point of the two lists.
 */

public class FindingInterSectionPoint {
    public static void main(String[] args) {
        Random random = new Random();

        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        for(int i = 0; i < 5; i++) first.addLast(random.nextInt(100));

        SinglyLinkedList<Integer> second = new SinglyLinkedList<>();
        for(int i = 0; i < 5; i++) second.addLast(random.nextInt(100));

        SinglyLinkedList<Integer> merged = new SinglyLinkedList<>();
        for(int i = 0; i < 5; i++) merged.addLast(random.nextInt(100));

        System.out.println("first list without merging " + first);
        System.out.println("second list without merging " + second);
        System.out.println("list to be merged " + merged);

        // merging the first and second with the third one
        first.merge(merged);
        second.merge(merged);
        System.out.println("first list after merging " + first);
        System.out.println("second list after merging " + second);
        System.out.println("the intersection point is " + getIntersectionNodeUsingSet(first, second));
        System.out.println("the intersection point is " + getIntersectionNodeUsingArray(first, second));
        System.out.println("the intersection point is " + getIntersectionNodeUsingSingleScan(first, second));
        System.out.println("first list " + first);
        System.out.println("second list " + second);

    }

    // using two iterations
    /*
        Approach
            -   In two iterations approach scan the first list and put it in the set
            -   traverse the second list and check if the node is in the set, if it is found then return
     */
    private static <T> node<T> getIntersectionNodeUsingSet(SinglyLinkedList<T> first,
                                                                            SinglyLinkedList<T> second){
        Set<node<T>> firstSet = new HashSet<>();
        for(node<T> temp = first.getHead(); temp != null; temp = temp.getNext()){
            firstSet.add(temp);
        }
        node<T> ans = null;
        for(node<T> temp = second.getHead(); temp != null; temp = temp.getNext()){
            if(firstSet.contains(temp)){
                ans = temp;
                break;
            }
        }
        return ans;
    }

    // using three iterations
    /*
        Approach
            -   In three iterations approach, traverse both the list and add it to the array
            -   Now, find the length of the smaller list, and traverse both the array in reverse order
            -   return if the common node is found
     */
    private static <T> node<T> getIntersectionNodeUsingArray(SinglyLinkedList<T> first,
                                                                              SinglyLinkedList<T> second){
        Object[] firstArray = new Object[first.getSize()];
        node<T> temp;
        node<T> ans = null;
        int i;
        for(i = 0, temp = first.getHead(); i < firstArray.length; temp = temp.getNext(), i++){
            firstArray[i] = temp;
        }

        Object[] secondArray = new Object[second.getSize()];
        for(i = 0, temp = second.getHead(); i < secondArray.length; temp = temp.getNext(), i++){
            secondArray[i] = temp;
        }

        int minimum = Math.min(firstArray.length, secondArray.length);
        for(i = 0; i < minimum; i++){
            Object firstNode = firstArray[firstArray.length - i - 1];
            Object secondNode = secondArray[secondArray.length - i - 1];
            if(firstNode != secondNode){
                //noinspection unchecked
                ans = (node<T>)firstArray[firstArray.length - i];
                break;
            }
        }
        return ans;
    }

    // using single scan
    /*
       Approach
        -   if two lists are given then first find the length of the two lists
        -   take the difference d of the two lists and find which list is greater
        -   traverse till d distance in larger list, so as to make the pointer at equal distance in two lists.
        -   now traverse in both list one by one and return the common node when found
     */
    private static <T> node<T> getIntersectionNodeUsingSingleScan(SinglyLinkedList<T> first,
                                                                                   SinglyLinkedList<T> second){
        SinglyLinkedList<T> tempfirst = first;
        SinglyLinkedList<T> tempSecond = second;
        int d;
        if(first.getSize() > second.getSize()){
            d = first.getSize() - second.getSize();
        }
        else{
            d = second.getSize() - first.getSize();
            first = tempSecond;
            second = tempfirst;
        }
        node<T> temp = first.getHead();
        for(int i = 0; i < d; i++){
            temp = temp.getNext();
        }
        node<T> tempanother = second.getHead();
        node<T> ans = null;
        while(temp != null){
            if(temp == tempanother){
                ans = temp;
                break;
            }
            temp = temp.getNext();
            tempanother = tempanother.getNext();
        }
        return ans;
    }
}
