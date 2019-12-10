package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
    private static <T> SinglyLinkedList.node<T> getIntersectionNodeUsingSet(SinglyLinkedList<T> first,
                                                                            SinglyLinkedList<T> second){
        Set<SinglyLinkedList.node<T>> firstSet = new HashSet<>();
        for(SinglyLinkedList.node<T> temp = first.getHead(); temp != null; temp = temp.getNext()){
            firstSet.add(temp);
        }
        SinglyLinkedList.node<T> ans = null;
        for(SinglyLinkedList.node<T> temp = second.getHead(); temp != null; temp = temp.getNext()){
            if(firstSet.contains(temp)){
                ans = temp;
                break;
            }
        }
        return ans;
    }

    // using three iterations
    private static <T> SinglyLinkedList.node<T> getIntersectionNodeUsingArray(SinglyLinkedList<T> first,
                                                                              SinglyLinkedList<T> second){
        Object[] firstArray = new Object[first.getSize()];
        SinglyLinkedList.node<T> temp = null;
        SinglyLinkedList.node<T> ans = null;
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
                ans = (SinglyLinkedList.node<T>)firstArray[firstArray.length - i];
                break;
            }
        }
        return ans;
    }

    // using single scan
    private static <T> SinglyLinkedList.node<T> getIntersectionNodeUsingSingleScan(SinglyLinkedList<T> first,
                                                                                   SinglyLinkedList<T> second){
        SinglyLinkedList<T> tempfirst = first;
        SinglyLinkedList<T> tempSecond = second;
        int d = 0;
        if(first.getSize() > second.getSize()){
            d = first.getSize() - second.getSize();
        }
        else{
            d = second.getSize() - first.getSize();
            first = tempSecond;
            second = tempfirst;
        }
        SinglyLinkedList.node<T> temp = first.getHead();
        for(int i = 0; i < d; i++){
            temp = temp.getNext();
        }
        SinglyLinkedList.node<T> tempanother = second.getHead();
        SinglyLinkedList.node<T> ans = null;
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
