package CollectionImplementation.Streams;

import java.util.Arrays;
import java.util.Comparator;

public class StreamFromArray {
    public static void main(String[] args) {
        String[] arr = {"A1", "Ankit", "kushal", "Brant", "Sarika", "amanda", "Shivika"};

        // reverse sorted by length, print all strings that starts with S
        Arrays.stream(arr)
                .filter(x -> x.startsWith("S"))
                .sorted(Comparator.comparing(String::length).reversed())
                .forEach(System.out::println);

        // average of squares of an int array
        Arrays.stream(new int[]{2, 3, 4, 5, 6})
                .map(x -> x * x)
                .average()
                .ifPresent(System.out::println);
    }
}
