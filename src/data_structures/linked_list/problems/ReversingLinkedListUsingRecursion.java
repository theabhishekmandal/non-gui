package data_structures.linked_list.problems;

import data_structures.linked_list.node.SinglyLinkedList;
import static data_structures.linked_list.node.SinglyLinkedList.Node;

import java.util.ArrayDeque;
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

        var second = new SinglyLinkedList<Integer>();
        IntStream.range(0, random.nextInt(20)).forEach(second::addLast);
        System.out.println("list before reversing " + second + "\nlist head " + second.getHead() +
                "\nlist tail " + second.getTail());
        reverseTheList2(second);
        System.out.println("list after reversing " + second + "\nlist head " + second.getHead() +
                "\nlist tail " + second.getTail());
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
    private static <T> Node<T> reversingLinkedListUsingRecursion(Node<T> node){
        if(node.getNext() == null)
            return node;
        Node<T> nodetemp = reversingLinkedListUsingRecursion(node.getNext());

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

    // same as above but without using recursion and using stack
    private static<T> void reverseTheList2(SinglyLinkedList<T> first) {
        first.setTail(first.getHead());
        first.setHead(reverseLinkedListUsingStack(first.getHead()));
    }

    private static <T> Node<T> reverseLinkedListUsingStack(Node<T> node) {
        if(node == null) {
            return null;
        }
        var stack = new ArrayDeque<Node<T>>();
        while(node != null) {
            stack.push(node);
            node = node.getNext();
        }
        node = stack.peek();
        Node<T> temp;
        while(!stack.isEmpty()) {
           temp = stack.pop();
           if(stack.isEmpty()) {
               temp.setNext(null);
           }
           else {
               temp.setNext(stack.peek());
           }
        }
       return node;
    }
}
