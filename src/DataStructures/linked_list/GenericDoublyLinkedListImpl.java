package DataStructures.linked_list;

import DataStructures.linked_list.Node.GenericDoublyLinkedList;

public class GenericDoublyLinkedListImpl {
    public static void main(String args[]){
        GenericDoublyLinkedList<String> list = new GenericDoublyLinkedList<String>();
        list.addFirst("I");
        list.addLast("Abhishek");
        list.insertInTheMiddle(list.getSize() / 2, "am");
        list.addLast("tail");
        list.addFirst("head");
        print(list, null);

        GenericDoublyLinkedList<String> list2 = new GenericDoublyLinkedList<>(list);
        GenericDoublyLinkedList<String> list3 = new GenericDoublyLinkedList<>(list);
        GenericDoublyLinkedList<String> list4 = new GenericDoublyLinkedList<>(list);

        System.out.println("list2 before deletion from end" +  list2);
        // using this type of loop because the getsize() keeps on changing
        for(;list2.getSize() > 0;){
            print(list2, list2.deleteLast());
        }

        System.out.println("list3 before deletion from begining" +  list3);
        // using this type of loop because the getsize() keeps on changing
        for(;list3.getSize() > 0;){
            print(list3, list3.deleteFirst());
        }

        System.out.println("list4 before deletion from end" +  list4);
        // using this type of loop because the getsize() keeps on changing
        for(;list4.getSize() > 0;){
            print(list4, list4.deleteInTheMiddle(list4.getSize() >> 1));
        }
    }
    private static <T> void print(GenericDoublyLinkedList<T> list, GenericDoublyLinkedList.node<T> deletedNode){
        GenericDoublyLinkedList.node<T> head = list.getHead();
        GenericDoublyLinkedList.node<T> headPrev = (head != null) ? head.getPrevious() : null;
        GenericDoublyLinkedList.node<T> headNext = (head != null) ? head.getNext() : null;
        GenericDoublyLinkedList.node<T> tail = list.getTail();
        GenericDoublyLinkedList.node<T> tailPrev = (tail != null) ? tail.getPrevious() : null;
        GenericDoublyLinkedList.node<T> tailNext = (tail != null) ? tail.getNext() : null;
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
