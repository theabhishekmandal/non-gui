package data_structures.linked_list.node;

public class Sl {
    public Node head;
    public Node tail;
    public static class Node {
        public int data;
        public Node next;
        public Node(int data) {
            this.data = data;
        }
        public static Node of(int data) {
            return new Node(data);
        }
        @Override
        public String toString() {
            return "[" + this.data + "]";
        }
    }
    public void insert(int data) {
        if(head == null) {
            head = Node.of(data);
            tail = head;
        }
        else {
            tail.next = Node.of(data);
            tail = tail.next;
        }
    }

    @Override
    public String toString() {
        Node temp = head;
        StringBuilder br = new StringBuilder();
        while(temp != null) {
            br.append(temp.data).append(" ");
            temp = temp.next;
        }
        return br.toString();
    }
}
