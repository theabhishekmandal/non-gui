package Collection_demo;

import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

/**
 *  Implementation of LinkedList.
 *
 *  Remember that if you make an object using the interface name then you can only access those methods
 *  which are present in the interface. For eg;
 *
 *   List<String> arr = new LinkedList<>(); // this can only access those which are present in list interface
 *
 *   LinkedList<String> arr = new LinkedList(); // this can access those which are present in LinkedList class
 */

public class linkedLIstDemo {
    public static void main(String args[]){

        LinkedList<Integer> arr = new LinkedList<>();

        // adding elements
        arr.add(10);
        arr.add(11);
        arr.add(12);
        arr.add(13);
        out.println("linked list values " + arr);

        // adding element at the beginning
        arr.addFirst(9);
        out.println("linkedlist after adding at the beginning " + arr);

        // adding element at the last
        arr.addLast(14);
        out.println("linkedlist after adding at the end " + arr);

        // getting the first element
        out.println("getting the first element " + arr.getFirst());

        // getting the last element
        out.println("getting the last element " + arr.getLast());

        // removing first element
        arr.removeFirst();
        out.println("list after removing first element" + arr);

        // removing last element
        arr.removeLast();
        out.println("list after removing last element " + arr);

        // peeking an element does not remove the item
        out.println("peeking the list" + arr.peek());

        // peeking from the front of the list
        out.println("peeking the first element"  +arr.peekFirst());

        // peeking from the end of the list
        out.println("peeking the last element" + arr.peekLast());

        // polling retrieves and removes the first element from the list
        out.println("polling the list"+ arr.poll());

        // polling from the front of the list
        out.println("polling the first element " + arr.pollFirst());

        // polling from the end of the list
        out.println("polling the last element " + arr.pollLast());

        // pushing an element when list is used as stack is added in front of the list
        arr.push(16);
        out.println("list after pushing an element " + arr);

        // poping an element returns the popped element from the front of the list
        arr.pop();
        out.println("list after poping an element" + arr);


    }
}
