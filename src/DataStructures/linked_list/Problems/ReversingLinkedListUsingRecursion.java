package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.SinglyLinkedList;

import java.util.Random;
import java.util.stream.IntStream;

public class ReversingLinkedListUsingRecursion {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        Random random = new Random();
        IntStream.range(0, random.nextInt(100)).forEach(first::addLast);
        System.out.println("list before reversing " + first + "\nlist head " + first.getHead() +
                "\nlist tail " + first.getTail());
        reverseTheList(first);
        System.out.println("list after reversing " + first + "\nlist head " + first.getHead() +
                "\nlist tail " + first.getTail());
    }

    // here we are interchanging the head and tail
    private static <T> void reverseTheList(SinglyLinkedList<T> first){
        first.setTail(first.getHead());
        first.setHead(reversingLinkedListUsingRecursion(first.getHead()));
    }
    private static <T> SinglyLinkedList.node<T> reversingLinkedListUsingRecursion(SinglyLinkedList.node<T> node){
        if(node.getNext() == null)
            return node;
        SinglyLinkedList.node<T> nodetemp = reversingLinkedListUsingRecursion(node.getNext());

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
