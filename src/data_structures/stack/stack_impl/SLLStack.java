package data_structures.stack.stack_impl;


import java.util.EmptyStackException;

public class SLLStack<T> {
    private Node<T> top;
    private int size;
    public static class Node<T>{
        private final T data;
        private final Node<T> next;
        public Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }
        @Override
        public String toString(){
            return "[" + this.data + "]";
        }
    }
    public SLLStack(){
        this.size = 0;
        this.top = null;
    }

    public void push(T data){
        top = new Node<>(data, top);
        ++this.size;
    }

    public T peek(){
        if(isEmpty()) throw new EmptyStackException();
        return top.data;
    }

    public T pop(){
        if(isEmpty()) throw new EmptyStackException();
        Node<T> temp = top;
        top = top.next;
        --this.size;
        return temp.data;
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
        Node<T> temp = this.top;
        while(temp != null){
            br.append(temp.data);
            if(temp.next != null)
                br.append(", ");
            temp = temp.next;
        }
        br.append("]");
        return br.toString();
    }
}
