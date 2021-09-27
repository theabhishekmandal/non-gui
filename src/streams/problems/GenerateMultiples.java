package streams.problems;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given first five digits, use stream to generate all the unique multiples of them
 * Approach:
 * -   This is a problem of reduction operation as we have to reduce all the generated multiples to a single list
 */
public class GenerateMultiples {
    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4, 5);
        var finalList = list.stream()
                .map(GenerateMultiples::getFirstFiveMultiples)
                .flatMap(Collection::stream).distinct().collect(Collectors.toList());
        System.out.println("final list " + finalList);
    }

    private static List<Integer> getFirstFiveMultiples(int k) {
        return IntStream.rangeClosed(1, 5).boxed().map(x -> x * k).collect(Collectors.toList());
    }
}
