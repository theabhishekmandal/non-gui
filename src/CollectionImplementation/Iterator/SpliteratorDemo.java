package CollectionImplementation.Iterator;

import java.util.*;

import static java.lang.System.out;

/**
 * Spliterators is another type of iterators. It has an ability to provide support for parallel iteration
 * of the portions of the sequence. Thus it supports parallel programming.
 *
 * We can use spliterators for non-parallel execution also.
 *
 * It has the following methods:
 *      1. int characteristics()                : returns the characteristics of the invoking spliterator,
 *                                              encoded into an integer.
 *
 *      2. long estimateSize()                  : estimates the number of elements left to iterate and returns the result.
 *                                                  returns long.max_value if count was not obtained for any  reason.
 *
 *      3. default void forEachRemaining(
 *         Consumer<? super T> action)          : applies action to each unprocessed element in the data source.
 *
 *      4. default Comparator<? super T>        : returns the comparator used by the invoking spliterator or null if,
 *                                                  IllegalStateException is thrown.
 *
 *      5. default long getExactSizeIfKnown()   : If the invoking spliterator is sized, returns the number of elements left
 *                                                   to iterate. Returns -1 otherwise.
 *
 *      6. default boolean hasCharacteristics() :  returns true if the invoking spliterator has the characteristics passed
 *                                                 in val. Returns false otherwise.
 *
 *      7. boolean tryAdvance(Consumer<? super T>
 *          action)                             :  executes action on the next element in the iteration. Returns true if
 *                                                 there is next element. Returns false if no elements remain.
 *
 *      8. Spliterator<T> trySplit()            : If possible , splits the invoking spliterator, returning a reference
 *                                                to a new spliterator for the partition . Otherwise, returns null.
 *                                                Thus, if successful, the original splitertaor iterates over one
 *                                                portion of the sequence and the returned spliterator iterates over the
 *                                                other portion.
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

        out.println(split.getExactSizeIfKnown());

        out.println(split.hasCharacteristics(12));

        // throws exception if sequence is unordered.
        //out.println(split.getComparator());
        out.println();

        // using tryAdvance to print and perform other operations
        while(split.tryAdvance(out::println));
        out.println();

        split = arr.spliterator();
        while(split.tryAdvance((n) -> out.println(new StringBuilder(n).reverse().toString())));
        out.println();

        // using forEach to print the values and perform operations.
        split = arr.spliterator();
        split.forEachRemaining(System.out::println);
        out.println();

        split = arr.spliterator();
        split.forEachRemaining((n) -> System.out.println(n + n));
    }
}
