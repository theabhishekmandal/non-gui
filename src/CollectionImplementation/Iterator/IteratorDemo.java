package CollectionImplementation.Iterator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static java.lang.System.out;

/**
 *  Implementing Iterator interface

 *  It has the following methods:
 *
 *  1. forEachRemaining(Consumer<?super E> action) : the action specified by action is executed on each
 *                                                      unprocessed element in the collection.
 *
 *  2. boolean hasNext()                           : Returns true if there are more elements
 *
 *  3. E next()                                    : returns the next element. Throws NoSuchElementException if
 *                                                      nothing is found
 *
 *  4. default void remove()                       : Removes the current element. Throws IllegalStateException
 *                                                      if remove() is called before calling next()
 */
public class IteratorDemo {
    public static void main(String[] args) {
        Set<Object> arr = new HashSet<>();
        arr.add(1);
        arr.add("blah");
        arr.add(2.3);
        arr.add(1e9);
        out.println(arr);

        // it.remove() will throw illegalStateException if remove() is called without calling next()
        Iterator it = arr.iterator();
        while(it.hasNext()){
            out.println(it.next());
            it.remove();
        }
    }
}
