package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.GenericSinglyLinkedList;

import java.util.Random;
import java.util.stream.IntStream;

public class ReversingLinkedListUsingRecursion {
    public static void main(String[] args) {
        GenericSinglyLinkedList<Integer> first = new GenericSinglyLinkedList<>();
        Random random = new Random();
        IntStream.range(0, random.nextInt(100)).forEach(first::addLast);
        System.out.println("list before reversing " + first + "\nlist head " + first.getHead() +
                "\nlist tail " + first.getTail());
        reverseTheList(first);
        System.out.println("list after reversing " + first + "\nlist head " + first.getHead() +
                "\nlist tail " + first.getTail());
    }

    // here we are interchanging the head and tail
    private static <T> void reverseTheList(GenericSinglyLinkedList<T> first){
        first.setTail(first.getHead());
        first.setHead(reversingLinkedListUsingRecursion(first.getHead()));
    }
    private static <T> GenericSinglyLinkedList.node<T> reversingLinkedListUsingRecursion(GenericSinglyLinkedList.node<T> node){
        if(node.getNext() == null)
            return node;
        GenericSinglyLinkedList.node<T> nodetemp = reversingLinkedListUsingRecursion(node.getNext());

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
