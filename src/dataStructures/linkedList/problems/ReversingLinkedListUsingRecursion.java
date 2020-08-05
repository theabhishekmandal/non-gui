package dataStructures.linkedList.problems;

import dataStructures.linkedList.node.SinglyLinkedList;
import static dataStructures.linkedList.node.SinglyLinkedList.node;

import java.util.Random;
import java.util.stream.IntStream;

public class ReversingLinkedListUsingRecursion {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Random random = new Random();
        IntStream.range(0, random.nextInt(20)).forEach(first::addLast);
        System.out.println("list before reversing " + first + "\nlist head " + first.getHead() +
                "\nlist tail " + first.getTail());
        reverseTheList(first);
        System.out.println("list after reversing " + first + "\nlist head " + first.getHead() +
                "\nlist tail " + first.getTail());
    }

    // here we are interchanging the head and tail
    /*
        Approach
            -   call the next node in the recursive method till the passed node's next value is not
                equal to null. then return the node only
            -   now when after returning use the return node's previous node to set it's pointer to the previous node
                eg: a->b->c, return node is c, it's previous is b, use b.next.next = b && b.next = null i.e a->b<-c,
            -   note that we are returning the last node only from the recursion call, because this will be set as head
                later
     */
    private static <T> void reverseTheList(SinglyLinkedList<T> first){
        first.setTail(first.getHead());
        first.setHead(reversingLinkedListUsingRecursion(first.getHead()));
    }
    private static <T> SinglyLinkedList.node<T> reversingLinkedListUsingRecursion(node<T> node){
        if(node.getNext() == null)
            return node;
        node<T> nodetemp = reversingLinkedListUsingRecursion(node.getNext());

        // reversing the links and returning only the last NODE which will become head
        /*
        if A->B->C->null

           A->B<-C
              |
              null

           null<-A<-B<-C
         */
        node.getNext().setNext(node);
        node.setNext(null);
        return nodetemp;
    }
}
