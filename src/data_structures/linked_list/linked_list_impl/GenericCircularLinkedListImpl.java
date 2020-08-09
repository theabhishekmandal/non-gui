package data_structures.linked_list.linked_list_impl;

import data_structures.linked_list.node.CircularLinkedList;

public class GenericCircularLinkedListImpl {
    public static void main(String[] args) {
        CircularLinkedList<String> list = new CircularLinkedList<>();
        list.addLast("hello");
        list.addFirst("world");
        list.addLast("Abhishek");
        list.addFirst("Mandal");
        list.insertInTheMiddle(list.getSize() >> 1, "middle");
        print(list, null);

        CircularLinkedList<String> list2 = new CircularLinkedList<>(list);
        CircularLinkedList<String> list3 = new CircularLinkedList<>(list);
        CircularLinkedList<String> list4 = new CircularLinkedList<>(list);

        System.out.println("list2 before deletion from end " + list2);
        while (list2.getSize() > 0) {
            print(list2, list2.deleteLast());
        }

        System.out.println("list3 before deletion from begining " + list3);
        while (list3.getSize() > 0) {
            print(list3, list3.deleteFirst());
        }

        System.out.println("list4 before deletion from middle " + list4);
        while (list4.getSize() > 0) {
            print(list4, list4.deleteInTheMiddle(list4.getSize() >> 1));
        }
    }
    private static <T> void print(CircularLinkedList<T> list, CircularLinkedList.Node<T> deletedNode){
        CircularLinkedList.Node<T> head = list.getHead();
        CircularLinkedList.Node<T> headNext = (head != null)? head.getNext() : null;
        CircularLinkedList.Node<T> tail = list.getTail();
        CircularLinkedList.Node<T> tailNext = (tail != null) ? tail.getNext() : null;

        System.out.println(((deletedNode == null) ? "" :"deleted node is " + deletedNode)
                + "\nlist is " + list
                + "\nhead is " + head
                + "\nhead next is " + headNext
                + "\ntail is " + tail
                + "\ntail next is " + tailNext
                +"\nnumber of elements are " + list.getSize()
                + "\n\n");
    }
}
