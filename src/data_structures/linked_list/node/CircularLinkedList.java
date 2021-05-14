package data_structures.linked_list.node;

import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class CircularLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public CircularLinkedList() {
    }

    public CircularLinkedList(CircularLinkedList<T> list) {
        copyAll(list);
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node<T> getHead() {
        return this.head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getTail() {
        return this.tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public void addLast(T data) {
        final Node<T> last = tail;
        final Node<T> first = head;
        Node<T> newNode = new Node<>(data, first);
        tail = newNode;
        if (last == null) {
            head = newNode;
            tail.next = head;
        } else
            last.next = newNode;
        size++;
    }

    public void addFirst(T data) {
        final Node<T> first = head;
        Node<T> newNode = new Node<>(data, first);
        head = newNode;
        if (first == null) {
            tail = newNode;
            head.next = tail;
        } else {
            tail.next = newNode;
        }
        size++;
    }

    public void insertInTheMiddle(int pos, T data) {
        if (pos <= 0) {
            addFirst(data);
            return;
        }
        if (pos >= size) {
            addLast(data);
            return;
        }
        final Node<T> pred = getNode(pos);
        final Node<T> succ = pred.next;
        pred.next = new Node<>(data, succ);
        size++;
    }

    public Node<T> getNode(int pos) {
        if (pos < 0 || pos > size) throw new IndexOutOfBoundsException();
        Node<T> temp = head;
        if (head == tail) return null;
        for (int i = 0; i < pos - 1; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public Node<T> deleteLast() {
        if (tail == null) throw new NoSuchElementException();
        final Node<T> newNode = tail;
        tail = getNode(size - 1);

        if (tail == null) {
            head = null;
        } else {
            // deleting the next pointer to null
            newNode.next = null;
            tail.next = head;
        }
        size--;
        return newNode;
    }

    public Node<T> deleteFirst() {
        if (head == null) throw new NoSuchElementException();
        final Node<T> newNode = head;
        head = (head.next == head) ? null : head.next;

        // deleting the next pointer to null
        newNode.next = null;

        if (head == null) tail = null;
        else {
            tail.next = head;
        }
        size--;
        return newNode;
    }

    public Node<T> deleteInTheMiddle(int pos) {
        if (pos < 0 || pos >= size) throw new IndexOutOfBoundsException();
        if (pos == 0) return deleteFirst();
        if (pos == size - 1) return deleteLast();
        Node<T> temp = getNode(pos);
        Node<T> currentNode = temp.next;
        temp.next = currentNode.next;
        currentNode.next = null;
        size--;
        return currentNode;
    }

    public void copyAll(CircularLinkedList<T> list) {
        if (list == null) throw new NullPointerException();
        for (Node<T> temp = list.head; temp != list.tail; temp = temp.next)
            this.addLast(temp.data);
        this.addLast(list.tail.data);
    }

    public void merge(CircularLinkedList<T> list) {
        if (list == null) throw new NullPointerException();
        if (list.size >= 0) this.size = this.size + list.size;
        else return;
        this.tail.next = list.head;
        this.tail = list.tail;
        this.tail.next = this.head;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "";
        }
        StringJoiner joiner = new StringJoiner("-->", "", "-->");
        for (Node<T> temp = head; temp != null && temp != tail; temp = temp.next) {
            joiner.add(temp.toString());
        }

        // printing tail here because it won't work in loop
        return joiner.add(tail.toString()).toString();
    }

    public static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return this.data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return this.next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "[" + this.data + "]";
        }
    }
}
