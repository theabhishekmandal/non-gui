package streams.problems;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Given two lists of strings determine there cartesian products i.e
 * ["1", "2"] and ["3", "4"] then cartesian product will be ["13", "14", "23", "24"]
 */
public class CartesianProductOfLists {
    public static void main(String[] args) {
        Supplier<Stream<String>> s1 = () -> Stream.of("Lawful", "Neutral", "Chaotic");
        Supplier<Stream<String>> s2 = () -> Stream.of("Good", "Neutral", "Evil");
        Supplier<Stream<String>> s3 = () -> Stream.of("Simple", " ", "Complex");

        // The idea is to take two supplier stream and combine them again and again
        BinaryOperator<Supplier<Stream<String>>> binaryOperator = (x, y) -> () -> x.get().flatMap(a -> y.get().map(b -> a + b));
        Supplier<Stream<String>> result = null;
        boolean notFound = false;
        for (var supplier : Arrays.asList(s1, s2, s3)) {
            if (!notFound) {
                notFound = true;
                result = supplier;
            } else {
                result = binaryOperator.apply(result, supplier);
            }
        }
        result.get().forEach(System.out::println);



        // Now the above thing in more concise manner
        List<List<String>> input = List.of(
                List.of("Lawful ", "Neutral ", "Chaotic "),
                List.of("Simple ", " ", "Complex "),
                List.of("Good", "Neutral", "Evil")
        );


        System.out.println();
        // First Create a supplier of streams, so that our internal list is consumable again
        Stream<Supplier<Stream<String>>> streamOfStreamOfSuppliers = input.stream().map(list -> () -> list.stream());
        Supplier<Stream<String>> s = streamOfStreamOfSuppliers
                .reduce((x, y) -> () -> x.get().flatMap(a -> y.get().map(b -> a + b)))
                .orElse(() -> Stream.of(""));
        s.get().forEach(System.out::println);
    }
}
