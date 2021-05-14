package data_structures.linked_list.node;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;
import java.util.StringJoiner;

public class RandomLinkedList<T> {
    private static final Random random = new Random();
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public RandomLinkedList() {
    }

    public RandomLinkedList(RandomLinkedList<T> list) {
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
        final Node<T> newNode = new Node<>(data, null);
        tail = newNode;
        if (last == null)
            head = newNode;
        else
            last.next = newNode;
        size++;
    }

    public void addFirst(T data) {
        final Node<T> first = head;
        final Node<T> newNode = new Node<>(data, first);
        head = newNode;
        if (first == null)
            tail = newNode;
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
        // getting the previous node of the current node
        // in the doubly linked list class this getNode will fetch
        // the current node rather than previous node
        Node<T> pred = getNode(pos);
        final Node<T> nextNode = pred.next;
        pred.next = new Node<>(data, nextNode);
        size++;
    }

    public Node<T> getNode(int pos) {
        if (pos < 1 || pos > size) throw new IndexOutOfBoundsException();
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

        if (tail == null) head = null;
        else {
            tail.next = null;
        }
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

    public void copyAll(RandomLinkedList<T> list) {
        if (list == null) throw new NullPointerException();
        for (Node<T> temp = list.head; temp != null; temp = temp.next) {
            this.addLast(temp.data);
        }
    }

    public void merge(RandomLinkedList<T> list) {
        if (list == null) throw new NullPointerException();
        if (list.size >= 0) this.size = this.size + list.size;
        else return;
        this.tail.next = list.head;
        this.tail = list.tail;
    }

    public void assignRandomPointers() {
        if (this.head == null) return;
        Node<T> first = this.head;
        while (first != null) {
            Node<T> randomNode = getNode(random.nextInt(this.size) + 1);
            first.setRandom(randomNode);
            first = first.getNext();
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (Node<T> temp = head; temp != null; temp = temp.next) {
            joiner.add("[" + temp.getData() + ", random = " + temp.getRandom().getData() + "]");
        }
        return joiner.toString();
    }

    public static class Node<T> {
        private T data;
        private Node<T> random;
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

        public Node<T> getRandom() {
            return this.random;
        }

        public void setRandom(Node<T> random) {
            this.random = random;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(data, node.data) &&
                    Objects.equals(random, node.random) &&
                    Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }

        @Override
        public String toString() {
            return "[" + this.data + "]";
        }

    }
}
