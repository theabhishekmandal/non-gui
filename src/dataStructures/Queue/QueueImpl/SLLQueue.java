package dataStructures.Queue.QueueImpl;


import java.util.NoSuchElementException;

public class SLLQueue<T> {
    private node<T> front;
    private node<T> rear;
    private int size;
    public static class node<T>{
        private T data;
        private node<T> next;
        public node(T data, node<T> next){
            this.data = data;
            this.next = next;
        }
        @Override
        public String toString(){
            return "[" + this.data + "]";
        }
    }
    public SLLQueue(){
        this.size = 0;
        this.front = null;
        this.rear = null;
    }

    public void enQueue(T data){
        final node<T> temp = rear;
        node<T> newNode = new node<>(data, null);
        rear = newNode;
        if(temp == null)
            front = newNode;
        else
            temp.next = newNode;
        ++this.size;
    }

    public T deQueue(){
        if(isEmpty()) throw new NoSuchElementException("empty queue");
        node<T> temp = front;
        if(front == rear) rear = rear.next;
        front = front.next;
        temp.next = null;
        --this.size;
        return temp.data;
    }

    public T getFirst(){
        if(isEmpty()) throw new NoSuchElementException("empty queue");
        return this.front.data;
    }

    public T getLast(){
        if(isEmpty()) throw new NoSuchElementException("empty queue");
        return this.rear.data;
    }
    public boolean isEmpty(){
        return this.size == 0;
    }

    public int getSize(){
        return this.size;
    }

    @Override
    public String toString(){
        StringBuilder br = new StringBuilder("[");
        node<T> temp = this.front;
        while(temp != null && temp != this.rear.next){
            br.append(temp.data);
            if(temp.next != null)
                br.append(", ");
            temp = temp.next;
        }
        br.append("]");
        return br.toString();
    }
}
