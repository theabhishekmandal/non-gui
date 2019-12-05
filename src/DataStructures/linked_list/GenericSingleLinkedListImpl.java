package DataStructures.linked_list;

import DataStructures.linked_list.Node.GenericSinglyLinkedList;

public class GenericSingleLinkedListImpl {
    public static void main(String[] args) {
        GenericSinglyLinkedList<Integer> list = new GenericSinglyLinkedList<>();
        list.addLast(2);
        list.addFirst(3);
        list.addLast(4);
        list.addFirst(5);
        list.insertInTheMiddle(list.getSize() / 2, 10);
        System.out.println("list is " + list + "\nhead is " + list.getHead() + "\ntail is " + list.getTail() + "\ntail next is " + list.getTail().getNext()
                + "\nnumber of elements are " + list.getSize() + "\n\n");


        GenericSinglyLinkedList<Integer> list2 = new GenericSinglyLinkedList<>(list);
        GenericSinglyLinkedList<Integer> list3 = new GenericSinglyLinkedList<>(list);
        GenericSinglyLinkedList<Integer> list4 = new GenericSinglyLinkedList<>(list);

        System.out.println("list2 before deletion from end" +  list2);
        // using this type of loop because the getsize() keeps on changing
        for(;list2.getSize() > 0;){
            print(list2, list2.deleteLast());
        }

        System.out.println("\nlist3 before deletion from begining" +  list3);
        // using this type of loop because the getsize() keeps on changing
        for(;list3.getSize() > 0;){
            print(list3, list3.deleteFirst());
        }

        System.out.println("\nlist4 before deletion from middle" + list4);
        // using this type of loop because the getsize() keeps on changing
        for(;list4.getSize() > 0;){
            print(list4, list4.deleteInTheMiddle(list4.getSize() >> 1));
        }
    }
    private static <T> void print(GenericSinglyLinkedList<T> list, GenericSinglyLinkedList.node<T> deletednode){
        System.out.println("deleted node is " + deletednode +
                "\nlist is " + list + "\nhead is " + list.getHead() + "\ntail is " + list.getTail() +
                "\nnumber of elements are " + list.getSize() + "\n\n");
    }
}