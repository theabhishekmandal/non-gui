package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.GenericSinglyLinkedList;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FindingInterSectionPoint {
    public static void main(String[] args) {
        Random random = new Random();

        GenericSinglyLinkedList<Integer> first = new GenericSinglyLinkedList<>();
        for(int i = 0; i < 5; i++) first.addLast(random.nextInt(100));

        GenericSinglyLinkedList<Integer> second = new GenericSinglyLinkedList<>();
        for(int i = 0; i < 5; i++) second.addLast(random.nextInt(100));

        GenericSinglyLinkedList<Integer> merged = new GenericSinglyLinkedList<>();
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
    private static <T> GenericSinglyLinkedList.node<T> getIntersectionNodeUsingSet(GenericSinglyLinkedList<T> first,
                                                                           GenericSinglyLinkedList<T> second){
        Set<GenericSinglyLinkedList.node<T>> firstSet = new HashSet<>();
        for(GenericSinglyLinkedList.node<T> temp = first.getHead(); temp != null; temp = temp.getNext()){
            firstSet.add(temp);
        }
        for(GenericSinglyLinkedList.node<T> temp = second.getHead(); temp != null; temp = temp.getNext()){
            if(firstSet.contains(temp))
                return temp;
        }
        return null;
    }

    // using three iterations
    private static <T> GenericSinglyLinkedList.node<T> getIntersectionNodeUsingArray(GenericSinglyLinkedList<T> first,
                                                                                     GenericSinglyLinkedList<T> second){
        Object[] firstArray = new Object[first.getSize()];
        GenericSinglyLinkedList.node<T> temp = null;
        int i = 0;
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
                return (GenericSinglyLinkedList.node<T>)firstArray[firstArray.length - i];
            }
        }
        return null;
    }

    private static <T> GenericSinglyLinkedList.node<T> getIntersectionNodeUsingSingleScan(GenericSinglyLinkedList<T> first,
                                                                                          GenericSinglyLinkedList<T> second){
        GenericSinglyLinkedList<T> tempfirst = first;
        GenericSinglyLinkedList<T> tempSecond = second;
        int d = 0;
        if(first.getSize() > second.getSize()){
            d = first.getSize() - second.getSize();
        }
        else{
            d = second.getSize() - first.getSize();
            first = tempSecond;
            second = tempfirst;
        }
        GenericSinglyLinkedList.node<T> temp = first.getHead();
        for(int i = 0; i < d; i++){
            temp = temp.getNext();
        }
        GenericSinglyLinkedList.node<T> tempanother = second.getHead();
        GenericSinglyLinkedList.node<T> ans = null;
        while(temp != null){
            if(temp == tempanother){
                ans = temp;
                break;
            }
            temp = temp.getNext();
            tempanother = tempanother.getNext();
        }
        first = tempfirst;
        second = tempSecond;
        return ans;
    }
}
