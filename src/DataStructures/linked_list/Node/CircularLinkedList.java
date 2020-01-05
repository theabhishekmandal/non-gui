package DataStructures.linked_list.Node;

import java.util.NoSuchElementException;

public class CircularLinkedList<T>{
    private node<T> head;
    private node<T> tail;
    private int size;

    public CircularLinkedList(){}

    public CircularLinkedList(CircularLinkedList<T> list){
        copyAll(list);
    }

    public static class node<T>{
        private T data;
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
        public void setNext(node<T> next){
            this.next = next;
        }
        @Override
        public String toString(){
            return "[" + this.data + "]";
        }
    }

    public int getSize(){
        return this.size;
    }

    public void setTail(node<T> tail) {
        this.tail = tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setHead(node<T> head) {
        this.head = head;
    }

    public node<T> getHead(){
        return this.head;
    }

    public node<T> getTail(){
        return this.tail;
    }

    public void addLast(T data){
        final node<T> last = tail;
        final node<T> first = head;
        node<T> newnode = new node<>(data, first);
        tail = newnode;
        if(last == null)
            head = newnode;
        else
            last.next = newnode;
        size++;
    }

    public void addFirst(T data){
        final node<T> first = head;
        node<T> newnode = new node<>(data, first);
        head = newnode;
        if(first == null) tail = newnode;
        else{
            tail.next = newnode;
        }
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
        final node<T> pred = getNode(pos);
        final node<T> succ = pred.next;
        node<T> newnode = new node<T>(data, succ);
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

        // deleting the next pointer to null
        newnode.next = null;

        if(tail == null) head = null;
        else{
            tail.next = head;
        }
        size--;
        return newnode;
    }

    public node<T> deleteFirst(){
        if(head == null) throw new NoSuchElementException();
        final node<T> newnode = head;
        head = (head.next == head) ? null : head.next;

        // deleting the next pointer to null
        newnode.next = null;

        if(head == null) tail = null;
        else{
            tail.next = head;
        }
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

    public void copyAll(CircularLinkedList<T> list){
        if(list == null) throw new NullPointerException();
        for(node<T> temp = list.head; temp != list.tail; temp = temp.next)
            this.addLast(temp.data);
        this.addLast(list.tail.data);
    }

    public void merge(CircularLinkedList<T> list){
        if(list == null) throw new NullPointerException();
        if(list.size >= 0) this.size = this.size + list.size;
        else return;
        this.tail.next = list.head;
        this.tail = list.tail;
        this.tail.next = this.head;
    }

    @Override
    public String toString(){
        StringBuilder br = new StringBuilder();
        for(node<T> temp = head; temp != tail; temp = temp.next){
            br.append(temp).append("-->");
        }

        // printing tail here because it won't work in loop
        return br.append(tail).append("-->").toString();
    }
}
