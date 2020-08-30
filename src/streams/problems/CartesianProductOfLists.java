package streams.problems;

import java.util.List;
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

        Supplier<Stream<String>> reduced = () -> s1.get().flatMap(a -> s2.get().map(b -> a + b));
        reduced.get().forEach(System.out::println);

        List<List<String>> input = List.of(
                List.of("Lawful ", "Neutral ", "Chaotic "),
                List.of("Simple ", " ", "Complex "),
                List.of("Good", "Neutral", "Evil")
        );

        System.out.println();
        Stream<Supplier<Stream<String>>> streamOfStreamOfSuppliers = input.stream().map(list -> list::stream);
        Supplier<Stream<String>> s = streamOfStreamOfSuppliers
                .reduce((x, y) -> () -> x.get().flatMap(a -> y.get().map(b -> a + b)))
                .orElse(() -> Stream.of(""));
        s.get().forEach(System.out::println);
    }
}
