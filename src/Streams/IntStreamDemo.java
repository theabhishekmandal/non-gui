package Streams;

import java.util.stream.IntStream;

public class IntStreamDemo {
    public static void main(String[] args) {

        // print the range of elements
        IntStream.range(0, 10)
                .forEach(System.out::print);

        System.out.println();

        // print the range of elements but skip first 5
        IntStream.range(0, 10)
                .skip(5)
                .forEach(System.out::print);

        // Integer stream with sum
        System.out.println(
        IntStream
                .range(0, 10)
                .sum());

    }
}
