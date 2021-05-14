package data_structures.linked_list.node;

import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
    }

    public DoublyLinkedList(DoublyLinkedList<T> list) {
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
        final Node<T> newNode = new Node<>(last, data, null);
        tail = newNode;
        if (last == null)
            head = newNode;
        else
            last.next = newNode;
        size++;
    }

    public void addFirst(T data) {
        final Node<T> first = head;
        final Node<T> newNode = new Node<>(null, data, first);
        head = newNode;
        if (first == null)
            tail = newNode;
        else
            first.previous = newNode;
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
        final Node<T> succ = getNode(pos);
        final Node<T> pred = succ.previous;
        Node<T> newNode = new Node<>(pred, data, succ);
        succ.previous = newNode;
        pred.next = newNode;
        size++;
    }

    public Node<T> getNode(int pos) {
        if (pos < 0 || pos > size) throw new IndexOutOfBoundsException();
        Node<T> temp = null;
        if (pos < size >> 1) {
            temp = head;
            for (int i = 0; i < pos; i++)
                temp = temp.next;
        } else {
            temp = tail;
            for (int i = size - 1; i > pos; i--)
                temp = temp.previous;
        }
        return temp;
    }

    public Node<T> deleteLast() {
        if (tail == null) throw new NoSuchElementException();
        final Node<T> newNode = tail;
        tail = tail.previous;

        // deleting the previous pointer to null
        newNode.previous = null;

        if (tail == null) head = null;
        else
            tail.next = null;
        size--;
        return newNode;
    }

    public Node<T> deleteFirst() {
        if (head == null) throw new NoSuchElementException();
        final Node<T> newNode = head;
        head = head.next;

        // deleting the next pointer to null
        newNode.next = null;

        if (head == null) tail = null;
        else
            head.previous = null;
        size--;
        return newNode;
    }

    public Node<T> deleteInTheMiddle(int pos) {
        if (pos < 0 || pos >= size) throw new IndexOutOfBoundsException();
        if (pos == 0) return deleteFirst();
        if (pos == size - 1) return deleteLast();
        Node<T> currNode = getNode(pos);
        final Node<T> pred = currNode.previous;
        final Node<T> succ = currNode.next;
        currNode.next = currNode.previous = null;
        pred.next = succ;
        succ.previous = pred;
        size--;
        return currNode;
    }

    public void copyAll(DoublyLinkedList<T> list) {
        if (list == null) throw new NullPointerException();
        for (Node<T> temp = list.head; temp != null; temp = temp.next) {
            this.addLast(temp.data);
        }
    }

    public void merge(DoublyLinkedList<T> list) {
        if (list == null) throw new NullPointerException();
        if (list.size >= 0) this.size = this.size + list.size;
        else return;
        this.tail.next = list.head;
        list.head.previous = this.tail;
        this.tail = list.tail;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("-->", "", "-->");
        for (Node<T> temp = head; temp != null; temp = temp.next) {
            joiner.add(temp.toString());
        }
        return joiner.toString();
    }

    public static class Node<T> {
        private T data;
        private Node<T> previous;
        private Node<T> next;

        public Node(Node<T> previous, T data, Node<T> next) {
            this.previous = previous;
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return this.data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getPrevious() {
            return this.previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
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
