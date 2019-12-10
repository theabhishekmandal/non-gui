package DataStructures.linked_list;

import DataStructures.linked_list.Node.CircularLinkedList;

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
        for(;list2.getSize() > 0;){
            print(list2, list2.deleteLast());
        }

        System.out.println("list3 before deletion from begining " + list3);
        for(;list3.getSize() > 0;){
            print(list3, list3.deleteFirst());
        }

        System.out.println("list4 before deletion from middle " + list4);
        for(;list4.getSize() > 0;){
            print(list4, list4.deleteInTheMiddle(list4.getSize() >> 1));
        }
    }
    private static <T> void print(CircularLinkedList<T> list, CircularLinkedList.node<T> deletednode){
        CircularLinkedList.node<T> head = list.getHead();
        CircularLinkedList.node<T> headNext = (head != null)? head.getNext() : null;
        CircularLinkedList.node<T> tail = list.getTail();
        CircularLinkedList.node<T> tailNext = (tail != null) ? tail.getNext() : null;

        System.out.println(((deletednode == null) ? "" :"deleted node is " + deletednode)
                + "\nlist is " + list
                + "\nhead is " + head
                + "\nhead next is " + headNext
                + "\ntail is " + tail
                + "\ntail next is " + tailNext
                +"\nnumber of elements are " + list.getSize()
                + "\n\n");
    }
}
