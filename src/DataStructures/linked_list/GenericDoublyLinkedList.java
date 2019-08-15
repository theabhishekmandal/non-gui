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

    public void addBefore(T data){
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
            addBefore(data);
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
    public node<T> deleteLast(){
        if(tail == null) throw new NoSuchElementException();
        final node<T> newnode = tail;
        tail = tail.previous;

        // deleting the value and previous pointer to null
        newnode.previous = null;
        newnode.data = null;

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

        // deleting the value and next pointer to null
        newnode.next = null;
        newnode.data = null;

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

    public void copyAll(DlinkedList<T> list){
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
        list.addBefore("I");
        list.addLast("Abhishek");
        list.insertInTheMiddle(1, "am");
        list.insertInTheMiddle(1, "hello");
        list.insertInTheMiddle(1, "gibberish");

        DlinkedList<String> list2 = new DlinkedList<>(list);
        System.out.println(list);
        DlinkedList.node<String> node = list.deleteInTheMiddle(2);
        System.out.println(node + " " + node.previous + " " + node.next);

        System.out.println("list2 before deleting " + list2);
        for(;list2.getSize() > 0;){
            list2.deleteInTheMiddle(list2.getSize() / 2);
            System.out.println(list2);
        }
    }
}
