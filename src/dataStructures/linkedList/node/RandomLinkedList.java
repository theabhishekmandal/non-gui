package dataStructures.linkedList.node;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

public class RandomLinkedList<T>{
    private node<T> head;
    private node<T> tail;
    private int size;
    public static class node<T>{
        private T data;
        private node<T> random;
        private node<T> next;
        public node(T data, node<T> next){
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
        public node<T> getRandom(){
            return this.random;
        }
        public void setRandom(node<T> random){
            this.random = random;
        }
        public void setNext(node<T>  next){
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            node<?> node = (node<?>) o;
            return Objects.equals(data, node.data) &&
                    Objects.equals(random, node.random) &&
                    Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }

        @Override
        public String toString(){
            return "[" + this.data + "]";
        }

    }
    public RandomLinkedList(){}

    public RandomLinkedList(RandomLinkedList<T> list){
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
        pred.next = new node<>(data, nextnode);
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

    public void copyAll(RandomLinkedList<T> list){
        if(list == null) throw new NullPointerException();
        for(node<T> temp = list.head; temp != null; temp = temp.next){
            this.addLast(temp.data);
        }
    }

    public void merge(RandomLinkedList<T> list){
        if(list == null) throw new NullPointerException();
        if(list.size >= 0) this.size = this.size + list.size;
        else return;
        this.tail.next = list.head;
        this.tail = list.tail;
    }

    private static final Random random = new Random();
    public void assignRandomPointers(){
        if(this.head == null) return;
        node<T> first =  this.head;
        while(first != null){
            node<T> randomNode = getNode(random.nextInt(this.size) + 1);
            first.setRandom(randomNode);
            first = first.getNext();
        }
    }

    @Override
    public String toString(){
        StringBuilder br = new StringBuilder();
        for(node<T> temp = head; temp != null; temp = temp.next)
            br.append("[").append(temp).append(", ").append(temp.getRandom().getData()).append("]").append("-->");
        return br.toString();
    }
}
