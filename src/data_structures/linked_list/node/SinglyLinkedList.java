package data_structures.linked_list.node;

import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class SinglyLinkedList<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;
    public static class Node<T>{
        private T data;
        private Node<T> next;
        public Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }
        public T getData(){
            return this.data;
        }
        public Node<T> getNext(){
            return this.next;
        }
        public void setData(T data){
            this.data = data;
        }
        public void setNext(Node<T> next){
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

    public Node<T> getHead(){
        return this.head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node<T> getTail(){
        return this.tail;
    }

    public void addLast(T data){
        final Node<T> last = tail;
        final Node<T> newNode = new Node<>(data, null);
        tail = newNode;
        if(last == null)
            head = newNode;
        else
            last.next = newNode;
        size++;
    }

    public void addFirst(T data){
        final Node<T> first = head;
        final Node<T> newNode = new Node<>(data, first);
        head = newNode;
        if(first == null)
            tail = newNode;
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
        Node<T> pred = getNode(pos);
        final Node<T> nextNode = pred.next;
        pred.next = new Node<>(data, nextNode);
        size++;
    }

    public Node<T> getNode(int pos){
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> temp = head;
        if (head == tail) {
            return null;
        }
        for(int i = 0; i < pos - 1; i++){
            temp = temp.next;
        }
        return  temp;
    }

    public Node<T> deleteLast(){
        if(tail == null) throw new NoSuchElementException();
        final Node<T> newNode = tail;
        tail = getNode(size - 1);

        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return newNode;
    }

    public Node<T> deleteFirst(){
        if(head == null) throw new NoSuchElementException();
        final Node<T> newNode = head;
        head = head.next;

        // deleting the next pointer to null
        newNode.next = null;

        if(head == null) tail = null;
        size--;
        return newNode;
    }

    public Node<T> deleteInTheMiddle(int pos){
        if(pos < 0 || pos >= size) throw new IndexOutOfBoundsException();
        if(pos == 0) return deleteFirst();
        if(pos == size - 1) return deleteLast();
        Node<T> temp = getNode(pos);
        Node<T> currentNode = temp.next;
        temp.next = currentNode.next;
        currentNode.next = null;
        size--;
        return currentNode;
    }

    public void copyAll(SinglyLinkedList<T> list){
        if(list == null) throw new NullPointerException();
        for(Node<T> temp = list.head; temp != null; temp = temp.next){
            this.addLast(temp.data);
        }
    }

    public void merge(SinglyLinkedList<T> list){
        if(list == null) throw new NullPointerException();
        if(list.size >= 0) this.size = this.size + list.size;
        else return;
        this.tail.next = list.head;
//        list.head = this.head; don't change the head of the list to be merged
        this.tail = list.tail;
    }

    @Override
    public String toString(){
        StringJoiner br = new StringJoiner("-->");
        for(Node<T> temp = head; temp != null; temp = temp.next) {
            br.add(String.valueOf(temp));
        }
        return br.toString();
    }
}
