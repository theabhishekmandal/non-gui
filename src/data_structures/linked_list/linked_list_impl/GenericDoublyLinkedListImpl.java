package data_structures.linked_list.linked_list_impl;

import data_structures.linked_list.node.DoublyLinkedList;

public class GenericDoublyLinkedListImpl {
    public static void main(String[] args){
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addFirst("I");
        list.addLast("Abhishek");
        list.insertInTheMiddle(list.getSize() / 2, "am");
        list.addLast("tail");
        list.addFirst("head");
        print(list, null);

        DoublyLinkedList<String> list2 = new DoublyLinkedList<>(list);
        DoublyLinkedList<String> list3 = new DoublyLinkedList<>(list);
        DoublyLinkedList<String> list4 = new DoublyLinkedList<>(list);

        System.out.println("list2 before deletion from end" +  list2);
        // using this type of loop because the getsize() keeps on changing
        while (list2.getSize() > 0) {
            print(list2, list2.deleteLast());
        }

        System.out.println("list3 before deletion from begining" +  list3);
        // using this type of loop because the getsize() keeps on changing
        while (list3.getSize() > 0) {
            print(list3, list3.deleteFirst());
        }

        System.out.println("list4 before deletion from middle" +  list4);
        // using this type of loop because the getsize() keeps on changing
        while (list4.getSize() > 0) {
            print(list4, list4.deleteInTheMiddle(list4.getSize() >> 1));
        }
    }
    private static <T> void print(DoublyLinkedList<T> list, DoublyLinkedList.Node<T> deletedNode){
        DoublyLinkedList.Node<T> head = list.getHead();
        DoublyLinkedList.Node<T> headPrev = (head != null) ? head.getPrevious() : null;
        DoublyLinkedList.Node<T> headNext = (head != null) ? head.getNext() : null;
        DoublyLinkedList.Node<T> tail = list.getTail();
        DoublyLinkedList.Node<T> tailPrev = (tail != null) ? tail.getPrevious() : null;
        DoublyLinkedList.Node<T> tailNext = (tail != null) ? tail.getNext() : null;
        System.out.println("list is " + list
                + "\nhead is " + head
                + "\nhead next is " + headNext
                + "\nhead previous is " + headPrev
                + "\ntail is " + tail
                + "\ntail next is " + tailNext
                + "\ntail previous is " + tailPrev
                + ((deletedNode == null) ? "" : "\ndeleted node is" + deletedNode)
                + "\nnumber of elements are " + list.getSize()
                + "\n\n");
    }
}
