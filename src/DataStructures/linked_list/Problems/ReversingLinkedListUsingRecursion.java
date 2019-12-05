package DataStructures.linked_list.Problems;

import DataStructures.linked_list.Node.GenericSinglyLinkedList;

import java.util.Random;

public class ReversingLinkedListUsingRecursion {
    public static void main(String[] args) {
        GenericSinglyLinkedList<Integer> first = new GenericSinglyLinkedList<>();
        Random random = new Random();
        for(int i = 0; i < random.nextInt(100); i++)
            first.addLast(i);
        System.out.println(first);
        first.setHead(reversingLinkedListUsingRecursion(first.getHead()));
        System.out.println(first);
    }
    private static <T> GenericSinglyLinkedList.node<T> reversingLinkedListUsingRecursion(GenericSinglyLinkedList.node<T> node){
        if(node.getNext() == null)
            return node;
        GenericSinglyLinkedList.node<T> nodetemp = reversingLinkedListUsingRecursion(node.getNext());

        // reversing the links
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
