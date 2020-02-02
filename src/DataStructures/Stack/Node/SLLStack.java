package DataStructures.Stack.Node;


import java.util.EmptyStackException;

public class SLLStack<T> {
    private node<T> top;
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
    public SLLStack(){
        this.size = 0;
        this.top = null;
    }

    public void push(T data){
        node<T> temp = new node<>(data, top);
        top = temp;
        ++this.size;
    }

    public T peek(){
        if(isEmpty()) throw new EmptyStackException();
        return top.data;
    }

    public T pop(){
        if(isEmpty()) throw new EmptyStackException();
        node<T> temp = top;
        top = top.next;
        --this.size;
        return temp.data;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    @Override
    public String toString(){
        StringBuilder br = new StringBuilder("[");
        node<T> temp = this.top;
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
