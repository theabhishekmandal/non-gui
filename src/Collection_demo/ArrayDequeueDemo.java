package Collection_demo;

import java.util.ArrayDeque;
import java.util.Arrays;

import static java.lang.System.out;

/**
 *  ArrayDeque is similar to that of double ended queue
 *  It can also have different types of elements if the type is of Object.
 */
public class ArrayDequeueDemo {
    public static void main(String[] args) {
        ArrayDeque<String> arr = new ArrayDeque<>();
        arr.add("A");
        arr.add("B");
        arr.add("C");
        arr.add("D");
        arr.add("Abhishek");
        arr.add("hi");
        arr.add("hello");
        arr.add("Bohdan");

        // methods in arrayDequeue

        // adding a group of collections
        String arg[] = {"ball", "cat", "dog", "pen"};
        arr.addAll(Arrays.asList(arg));

        // methods is similar to presented in the LinkedList class
        arr.addFirst("first");
        arr.addLast("last");
        out.println("values after adding at the end and start\n" + arr);

        out.println("getting the last and the first element \n" + arr.getFirst() + " " + arr.getLast());

        // offer and offerlast to add at the last
        // offerfirst to add at the beginning
        arr.offer("blah");
        arr.offerFirst("weedbuzz");
        arr.offerLast("buzzweed");
        out.println(arr);

        // peeking the first element does not remove it from the list
        out.println("peeking the first element\n" + arr.peek() + " " + arr.peek());

        // peeking the last element does not remove it from the list
        out.println("peeking the last element \n" + arr.peekLast());

        // push and pop implementation if used as stack
        arr.push("push");
        out.println("queue after pushing an element\n "  + arr);
        arr.pop();
        out.println("queue after poping an element\n "  + arr);

        // polling retrieves the item and also removes it from the list
        out.println("queue before and after polling two front values using different methods");
        out.println(arr);
        arr.poll();
        out.println(arr);
        arr.pollFirst();
        out.println(arr);

        // polling from the last
        out.println("queue before and after polling value from the end ");
        out.println(arr);
        arr.pollLast();
        out.println(arr);
    }
}
