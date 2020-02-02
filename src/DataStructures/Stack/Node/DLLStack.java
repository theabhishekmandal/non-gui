package DataStructures.Stack.Node;

class DLLStack<T>{
    private class Node{
        T data;
        Node next, previous;
        Node(Node previous, T data, Node next){
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
    }
    private Node head, tail;
    private int size;
    public DLLStack(){
    }

    public void push(T data){
        Node last = this.tail;
        Node temp = new Node(last, data, null);
        this.tail = temp;
        if(last == null){
            this.head = temp;
        }
        else
            last.next = temp;
        this.size++;
    }

    public T peek(){
        if(this.tail == null) throw new NullPointerException();
        return this.tail.data;
    }

    public T pop(){
        if(this.tail == null) throw new NullPointerException();
        Node temp = tail;
        tail = tail.previous;
        this.size--;
        return temp.data;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public int size(){
        return this.size;
    }

    @Override
    public String toString(){
        Node temp = this.head;
        StringBuilder br = new StringBuilder();
        while(temp != null){
            br.append(temp.data).append(" ");
            temp = temp.next;
        }
        br.append("\n");
        return br.toString();
    }
}
