package data_structures.linked_list.problems;

/**
 *
 * This is an example of Stack using LinkedList.
 */
class Stack<T>{
    private class Node{
        T data;
        Node next;
        Node previous;
        Node(Node previous, T data, Node next){
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
    }
    private Node head;
    private Node tail;
    private int size;

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
public class _40EasyStackUsingLinkedList {
    public static void main(String[] args){
        Stack<Integer> obj = new Stack<>();
        for(int i = 0; i < 11; i++)obj.push(i);
        System.out.println(obj);
        System.out.println("Top of the stack " + obj.peek() + " size of the stack " + obj.size());
        System.out.println("Popped value " + obj.pop() + " size of the stack " + obj.size());
    }
}
