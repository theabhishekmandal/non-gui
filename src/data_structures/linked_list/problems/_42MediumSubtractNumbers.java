package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;

import java.util.LinkedList;
import java.util.function.BiConsumer;

import static data_structures.linked_list.node.SinglyLinkedList.Node;

/**
 * Given two numbers in the form of linked list, subtract and return the result
 * Conditions:
 *  -   If leading zeros are present then remove them
 *  -   If answer has leading zeros then remove them
 *  -   If answer has all zeros then return 0 value node
 */

public class _42MediumSubtractNumbers {
    public static void main(String[] args) {
        String[] arr = new String[]{
                "0 7 1 5 3 6 1 0 7",
                "0 7 1 5 3 6 1 0 7",
                "6 9 5 3 4 3 7 2 2",
                "5 3 9 8 6 1 3 0 5",
                "0 2 5 2 6 9 6 7 2 4 5 0 2 1 7 5",
                "7 0 4 7 9 1 4 5 6 0 8 9 0 0 6",
        };

        /*
            expected answers
            [0]
            [1, 5, 5, 4, 8, 2, 4, 1, 7]
            [4, 5, 2, 0, 9, 4, 7, 3, 1, 5, 8, 6, 8, 3, 1
         */
        BiConsumer<String, SinglyLinkedList<Integer>> func = (x, y) -> {
            for(char c : x.toCharArray()) {
                if(Character.isDigit(c)) {
                    y.addLast(c - '0');
                }
            }
        };
        for(int i = 0; i < arr.length - 1; i += 2) {
            SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
            SinglyLinkedList<Integer> second = new SinglyLinkedList<>();
            func.accept(arr[i], first);
            func.accept(arr[i + 1], second);
            Node<Integer> answer = subTractLinkedList(first.getHead(), second.getHead());
//            SinglyLinkedList<Integer> answer = new SinglyLinkedList<>();
            System.out.println("first list = " + first);
            System.out.println("second list = " + second);


            StringBuilder br = new StringBuilder("[");
            boolean firstTime = true;
            while(answer != null) {
                if(firstTime) {
                    firstTime = false;
                    br.append(answer.getData());
                }
                else {
                    br.append(", ").append(answer.getData());
                }
                answer = answer.getNext();
            }
            br.append("]");
            System.out.println(br);
        }
    }

    private static Node<Integer> subTractLinkedList(Node<Integer> one, Node<Integer> two) {
        if(one == null) {
            return two;
        }
        if(two == null) {
            return one;
        }

        Node<Integer> first = one;
        Node<Integer> second = two;

        // Using queue so as to check the first digit is greater or not if both list have equal length
        LinkedList<Node<Integer>> largeQueue = new LinkedList<>();
        LinkedList<Node<Integer>> smallQueue = new LinkedList<>();

        while(one != null || two != null) {
            if(one != null) {
                // don't push leading zeros
                if(!largeQueue.isEmpty() || one.getData() != 0) {
                    largeQueue.addLast(one);
                }
                one = one.getNext();
            }
            if(two != null) {
                // don't push leading zeros
                if(!smallQueue.isEmpty() || two.getData() != 0) {
                    smallQueue.addLast(two);
                }
                two = two.getNext();
            }
        }

        // if largeSize < smallSize or largeSize == smallSize but large.firstValue < small.firstValue
        if(largeQueue.size() < smallQueue.size() ||
                (largeQueue.size() == smallQueue.size() && largeQueue.getFirst().getData() < smallQueue.getFirst().getData())) {
           LinkedList<Node<Integer>> temp = largeQueue;
           largeQueue = smallQueue;
           smallQueue = temp;

           // changing the large list head
            first = second;
        }

        boolean borrow = false;
        while(!smallQueue.isEmpty()) {
            Node<Integer> large = largeQueue.pollLast();
            borrow = checkForBorrow(borrow, large);
            Node<Integer> small = smallQueue.pollLast();
            int value;
            if(large != null && small != null) {
                if(large.getData() < small.getData()) {
                    value = 10 + large.getData() - small.getData();
                    borrow = true;
                }
                else {
                    value = large.getData() - small.getData();
                }
                large.setData(value);
            }
        }

        // check for borrow in large list
        if(borrow) {
            while(!largeQueue.isEmpty()) {
                borrow = checkForBorrow(borrow, largeQueue.pollLast());
            }
        }

        // remove all trailing zeros
        while(first != null && first.getData() == 0) {
            first = first.getNext();
        }

        // if head is null then return 0 value node
        return (first == null)? new Node<>(0, null) : first;
    }

    private static boolean checkForBorrow(boolean borrow, Node<Integer> large) {
        // assign borrow to true if large.getData() == 0
        if(borrow) {
           if(large.getData() > 0) {
               large.setData(large.getData() - 1);
               return false;
           }
           else {
               large.setData(9);
               return true;
           }
        }
        return false;
    }
}
