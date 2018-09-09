package Collection_demo.Iterator;

import java.util.*;

import static java.lang.System.out;

/**
 * Spliterators is another type of iterators. It has an ability to provide support for parallel iteration
 * of the portions of the sequence. Thus it supports parallel programming.
 *
 * We can use spliterators for non-parallel execution also.
 */
public class SpliteratorDemo {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("hello");
        arr.add("world");
        arr.add("hey");
        arr.add("there");

        Spliterator<String> split = arr.spliterator();
        out.println(split.characteristics());
        out.println(split.estimateSize());
        split.forEachRemaining(System.out::println);
        split.forEachRemaining((n) -> System.out.println(n + n));
    }
}
