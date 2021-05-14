package collection_implementation.iterator;

import java.util.ArrayList;

import static java.lang.System.out;

/**
 * Implementing ListIterator Interface
 * It has the following methods:
 *      1. void add(E obj)                             : inserts obj into the list in front of the element that will be
 *                                                       returned by the next call to next().
 *
 *      2. forEachRemaining(Consumer<?super E> action) : the action specified by action is executed on each
 *                                                       unprocessed element in the collection.
 *
 *      3. boolean hasNext()                           : Returns true if there is a next element. Otherwise, returns false.
 *
 *      4. boolean hasPrevious()                       : Returns true if there is a previous element. Otherwise returns
 *                                                       false.
 *
 *      5. E next()                                    : Returns the next element. A NoSuchElementException is thrown if
 *                                                       if there is no next element.
 *
 *      6. E previous()                                : Returns the previous element. A NoSuchElementException is thrown if
 *                                                       if there is no previous element.
 *
 *      7. int previousIndex()                         : Returns the index of the previous element. Returns -1 if there is
 *                                                       no such element.
 *
 *      8. void remove()                               : Removes the current element from the list. An IllegalStateException
 *                                                       is thrown if remove() is called before next() or previous().
 *
 *      9. void set(E obj)                             : Assigns obj to the current element. This is the element last
 *                                                       returned by a all to either next() or previous().
 */

public class ListIteratorDemo {
    public static void main(String[] args) {
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(6);
        arr.add(7);
        arr.add(8);
        arr.add(9);
        arr.add(10);

        var it = arr.listIterator();
        out.println("traversing in forward direction");
        while (it.hasNext()) {
            out.println(it.nextIndex() + " " + it.next());
        }
        out.println();

        // now for traversing in reverse direction iterator must be it at last index
        out.println("traversing in reverse direction");
        while (it.hasPrevious()) {
            out.println(it.previousIndex() + " " + it.previous());
        }
        out.println();

        out.println("for each remaining implementation");
        it.forEachRemaining(System.out::println);
        out.println();

        out.println("adding, removing and setting the values using listIterator");
        it = arr.listIterator();
        out.println(arr);
        while (it.hasNext()) {
            it.next();
            it.remove();
            it.next();
            it.set(12);
        }
        it.add(1e9);
        out.println(arr);
        out.println();
    }
}
