package streams.problems;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Given two lists of strings determine there cartesian products i.e
 * ["1", "2"] and ["3", "4"] then cartesian product will be ["13", "14", "23", "24"]
 *
 * Approach
 * Idea behind this solution is that
 *
 * 1.   To consume a stream again and again, every stream should be called via supplier.
 *      This will allow us to iterate over the stream again which will be similar to the below for loop
 *
 * 2.   For a normal Iteration solution pseudocode would be like
 *
 *     for (String a : list1) {
 *          for (String b : list2) {
 *              for (String c : list3) {
 *                  print(a + b + c);
 *              }
 *          }
 *     }
 *
 *     This above can be achieved via streams as well.
 *  3.  A single stream of strings
 *      Supplier<Stream<String>> s1 = () -> Stream.of("Lawful", "Neutral", "Chaotic");
 *      s1.get().forEach(System.out::println);
 *      result = ["Lawful", "Neutral", "Chaotic"]
 *
 *      To combine two streams of strings
 *      Supplier<Stream<String>> s1 = () -> Stream.of("Lawful", "Neutral", "Chaotic");
 *      Supplier<Stream<String>> s2 = () -> Stream.of("Good", "Neutral", "Evil");
 *      Supplier<Stream<String>> intermediate = () -> s1.get().flatMap(a -> s2.get().map(b -> a + b));
 *      intermediate.get().forEach(System.out::println);
 *      result = ["LawfulGood", "LawfulNeutral", ....]
 *
 *      To combine three streams of strings
 *      Supplier<Stream<String>> s1 = () -> Stream.of("Lawful", "Neutral", "Chaotic");
 *      Supplier<Stream<String>> s2 = () -> Stream.of("Good", "Neutral", "Evil");
 *      Supplier<Stream<String>> s3 = () -> Stream.of("Simple", " ", "Complex");
 *
 *      Supplier<Stream<String>> intermediate = () -> s1.get().flatMap(a -> s2.get().map(b -> a + b));
 *      Supplier<Stream<String>> finalStream = () -> intermediate.get().flatMap(inter -> s1.get().map(c -> inter + c));
 *      Note: here inter = a + b from previous stream, so resultant is a + b + c
 *      finalStream.get().forEach(System.out::println);
 *      This will print all the cartesian products.
 *
 */
public class CartesianProductOfLists {
    public static void main(String[] args) {
        Supplier<Stream<String>> s1 = () -> Stream.of("Lawful", "Neutral", "Chaotic");
        Supplier<Stream<String>> s2 = () -> Stream.of("Good", "Neutral", "Evil");
        Supplier<Stream<String>> s3 = () -> Stream.of("Simple", " ", "Complex");

        // This is also work
        Supplier<Stream<String>> intermediate = () -> s1.get().flatMap(a -> s2.get().map(b -> a + b));
        Supplier<Stream<String>> finalStream = () -> intermediate.get().flatMap(a -> s1.get().map(b -> a + b));
        finalStream.get().forEach(System.out::println);

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
