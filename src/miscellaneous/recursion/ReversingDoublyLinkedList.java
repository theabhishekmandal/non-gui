package miscellaneous.recursion;

import java.util.StringJoiner;

class LinkedList<T> {
    private Node<T> first;
    private Node<T> last;

    public void addLast(T ob) {
        final Node<T> l = last;
        final Node<T> newnode = new Node<>(l, ob, null);
        last = newnode;
        if (l == null)
            first = newnode;
        else
            l.next = newnode;
    }

    @Override
    public String toString() {
        if (first == null) {
            return "[]";
        }
        if (first == last) {
            return "[" + this.first.value + "]";
        }
        var joiner = new StringJoiner("[", ", ", "]");
        Node<T> f = first;
        while (f != null) {
            joiner.add(f.value.toString());
            f = f.next;
        }
        return joiner.toString();
    }

    public Node<T> getFirst() {
        return first;
    }

    public Node<T> getLast() {
        return last;
    }

    static class Node<T> {
        T value;
        Node<T> next;
        Node<T> previous;

        public Node(Node<T> prev, T value, Node<T> last) {
            this.value = value;
            this.next = last;
            this.previous = prev;
        }
    }
}

public class ReversingDoublyLinkedList {
    public static <T> LinkedList<T> reverseTheList(LinkedList<T> list2, LinkedList.Node<T> last) {
        if (last == null)
            return list2;
        list2.addLast(last.value);
        last = last.previous;
        return reverseTheList(list2, last);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        LinkedList<Integer> list = new LinkedList<>();
        for (int i : arr) {
            list.addLast(i);
        }
        System.out.println(list);
        LinkedList<?> list2 = reverseTheList(new LinkedList<>(), list.getLast());
        System.out.println(list2);
    }
}
