package DataStructures.linked_list.Node;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T>{
    private node<T> head;
    private node<T> tail;
    private int size;
    public static class node<T>{
        private T data;
        private node<T> next;
        node(T data, node<T> next){
            this.data = data;
            this.next = next;
        }
        public T getData(){
            return this.data;
        }
        public node<T> getNext(){
            return this.next;
        }
        public void setData(T data){
            this.data = data;
        }
        public void setNext(node<T>  next){
            this.next = next;
        }
        @Override
        public String toString(){
            return "[" + this.data + "]";
        }
    }
    public SinglyLinkedList(){}

    public SinglyLinkedList(SinglyLinkedList<T> list){
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
        final node<T> newnode = new node<>(data, null);
        tail = newnode;
        if(last == null)
            head = newnode;
        else
            last.next = newnode;
        size++;
    }

    public void addFirst(T data){
        final node<T> first = head;
        final node<T> newnode = new node<>(data, first);
        head = newnode;
        if(first == null)
            tail = newnode;
        size++;
    }

    public void insertInTheMiddle(int pos, T data){
        if(pos <= 0){
            addFirst(data);
            return;
        }
        if(pos >= size){
            addLast(data);
            return;
        }
        // getting the previous node of the current node
        // in the doubly linked list class this getNode will fetch
        // the current node rather than previous node
        node<T> pred = getNode(pos);
        final node<T> nextnode = pred.next;
        final node<T> newnode = new node<>(data, nextnode);
        pred.next = newnode;
        size++;
    }

    public node<T> getNode(int pos){
        if(pos < 1 || pos > size) throw new IndexOutOfBoundsException();
        node<T> temp = head;
        if(head == tail) return null;
        for(int i = 0; i < pos - 1; i++){
            temp = temp.next;
        }
        return  temp;
    }

    public node<T> deleteLast(){
        if(tail == null) throw new NoSuchElementException();
        final node<T> newnode = tail;
        tail = getNode(size - 1);

        if(tail == null) head = null;
        else{
            tail.next = null;
        }
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
        size--;
        return newnode;
    }

    public node<T> deleteInTheMiddle(int pos){
        if(pos < 0 || pos >= size) throw new IndexOutOfBoundsException();
        if(pos == 0) return deleteFirst();
        if(pos == size - 1) return deleteLast();
        node<T> temp = getNode(pos);
        node<T> currentNode = temp.next;
        temp.next = currentNode.next;
        currentNode.next = null;
        size--;
        return currentNode;
    }

    public void copyAll(SinglyLinkedList<T> list){
        if(list == null) throw new NullPointerException();
        for(node<T> temp = list.head; temp != null; temp = temp.next){
            this.addLast(temp.data);
        }
    }

    public void merge(SinglyLinkedList<T> list){
        if(list == null) throw new NullPointerException();
        if(list.size >= 0) this.size = this.size + list.size;
        else return;
        this.tail.next = list.head;
        this.tail = list.tail;
    }

    @Override
    public String toString(){
        StringBuilder br = new StringBuilder();
        for(node<T> temp = head; temp != null; temp = temp.next)
            br.append(temp + "-->");
        return br.toString();
    }
}
