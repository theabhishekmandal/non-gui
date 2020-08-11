package streams;

import java.util.stream.IntStream;

/**
 * this produces a new stream that contains elements of the original stream that pass a given test (specified by a Predicate)
 */
public class FilterDemo {
    public static void main(String[] args) {
        // print all numbers that are even
        IntStream.range(0, 10)
                .filter(x -> x % 2 == 0)
                .forEach(System.out::println);
    }
}
