package DataStructures.linked_list;

import java.util.*;
class DlinkedList<T>{
    private node<T> head;
    private node<T> tail;
    private int size;

    static class node<T>{
        T data;
        node<T> previous;
        node<T> next;
        node(node<T> previous, T data, node<T> next){
            this.previous = previous;
            this.data = data;
            this.next = next;
        }
        @Override
        public String toString(){
            return "[" + this.data + "]";
        }
    }

    public DlinkedList(){}

    public DlinkedList(DlinkedList<T> list){
        copyAll(list);
    }

    public int getSize(){
        return this.size;
    }

    public node<T> getHead(){
        return this.head;
    }

    public node<T> getTail(){
        return this.tail;
    }

    public void addLast(T data){
        final node<T> last = tail;
        final node<T> newnode = new node<>(last, data, null);
        tail = newnode;
        if(last == null)
            head = newnode;
        else
            last.next = newnode;
        size++;
    }

    public void addFirst(T data){
        final node<T> first = head;
        final node<T> newnode = new node<>(null, data, first);
        head = newnode;
        if(first == null)
            tail = newnode;
        else
            first.previous = newnode;
        size++;
    }

    public void insertInTheMiddle(int pos, T data){
        if(pos <= 0) {
            addFirst(data);
            return;
        }
        if(pos >= size){
            addLast(data);
            return;
        }
        final node<T> succ = getNode(pos);
        final node<T> pred = succ.previous;
        node<T> newnode = new node<>(pred, data, succ);
        succ.previous = newnode;
        pred.next = newnode;
        size++;
    }

    private node<T> getNode(int pos){
        node<T> temp = null;
        if(pos < size >> 1){
            temp = head;
            for(int i = 0; i < pos; i++)
                temp = temp.next;
        }
        else{
            temp = tail;
            for(int i = size - 1; i > pos; i--)
                temp = temp.previous;
        }
        return temp;
    }

    public node<T> deleteLast(){
        if(tail == null) throw new NoSuchElementException();
        final node<T> newnode = tail;
        tail = tail.previous;

        // deleting the previous pointer to null
        newnode.previous = null;

        if(tail == null) head = null;
        else
            tail.next = null;
        size--;
        return newnode;
    }

    public node<T> deleteFirst(){
        if(head == null) throw new NoSuchElementException();
        final node<T> newnode = head;
        head = head.next;

        // deleting the next pointer to null
        newnode.next = null;

        if(head == null) tail = null;
        else
            head.previous = null;
        size--;
        return newnode;
    }

    public node<T> deleteInTheMiddle(int pos){
        if(pos < 0 || pos >= size) throw new IndexOutOfBoundsException();
        if(pos == 0) return deleteFirst();
        if(pos == size - 1) return deleteLast();
        node<T> currnode = getNode(pos);
        final node<T> pred = currnode.previous;
        final node<T> succ = currnode.next;
        currnode.next = currnode.previous = null;
        pred.next = succ;
        succ.previous = pred;
        size--;
        return currnode;
    }

    public void copyAll(DlinkedList<T> list){
        if(list == null) throw new NullPointerException();
        for(node<T> temp = list.head; temp != null; temp = temp.next){
            this.addLast(temp.data);
        }
    }
    @Override
    public String toString(){
        StringBuilder br = new StringBuilder();
        for(node<T> temp = head; temp != null; temp = temp.next){
            br.append(temp + "-->");
        }
        return br.toString();
    }
}
public class GenericDoublyLinkedList{
    public static void main(String args[]){
        DlinkedList<String> list = new DlinkedList<String>();
        list.addFirst("I");
        list.addLast("Abhishek");
        list.insertInTheMiddle(list.getSize() / 2, "am");
        list.addLast("tail");
        list.addFirst("head");
        print(list, null);

        DlinkedList<String> list2 = new DlinkedList<>(list);
        DlinkedList<String> list3 = new DlinkedList<>(list);
        DlinkedList<String> list4 = new DlinkedList<>(list);

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
    private static <T> void print(DlinkedList<T> list, DlinkedList.node<T> deletedNode){
        DlinkedList.node<T> head = list.getHead();
        DlinkedList.node<T> headPrev = (head != null) ? head.previous : null;
        DlinkedList.node<T> headNext = (head != null) ? head.next : null;
        DlinkedList.node<T> tail = list.getTail();
        DlinkedList.node<T> tailPrev = (tail != null) ? tail.previous : null;
        DlinkedList.node<T> tailNext = (tail != null) ? tail.next : null;
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
