package DataStructures.linked_list.Node;

import java.util.NoSuchElementException;

public class GenericDoublyLinkedList<T>{
    private node<T> head;
    private node<T> tail;
    private int size;

    public static class node<T>{
        private T data;
        private node<T> previous;
        private node<T> next;
        node(node<T> previous,T data, node<T> next){
            this.previous = previous;
            this.data = data;
            this.next = next;
        }
        public T getData(){
            return this.data;
        }
        public node<T> getPrevious(){
            return this.previous;
        }
        public node<T> getNext(){
            return this.next;
        }
        public void setData(T data){
            this.data = data;
        }
        public void setPrevious(node<T> previous){
            this.previous = previous;
        }
        public void setNext(node<T> next){
            this.next = next;
        }
        @Override
        public String toString(){
            return "[" + this.data + "]";
        }
    }

    public GenericDoublyLinkedList(){}

    public GenericDoublyLinkedList(GenericDoublyLinkedList<T> list){
        copyAll(list);
    }

    public int getSize(){
        return this.size;
    }

    public node<T> getHead(){
        return this.head;
    }

    public void setHead(node<T> head) {
        this.head = head;
    }

    public void setTail(node<T> tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
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

    public node<T> getNode(int pos){
        if(pos < 1 || pos > size) throw new IndexOutOfBoundsException();
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

    public void copyAll(GenericDoublyLinkedList<T> list){
        if(list == null) throw new NullPointerException();
        for(node<T> temp = list.head; temp != null; temp = temp.next){
            this.addLast(temp.data);
        }
    }

    public void merge(GenericDoublyLinkedList<T> list){
        if(list == null) throw new NullPointerException();
        if(list.size >= 0) this.size = this.size + list.size;
        else return;
        this.tail.next = list.head;
        list.head.previous = this.tail;
        this.tail = list.tail;
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
