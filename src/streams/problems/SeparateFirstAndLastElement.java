package streams.problems;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a list of elements, separate the first and the rest of them
 */
public class SeparateFirstAndLastElement {
    public static void main(String[] args) {
        Random random = new Random();
        int n = 1 + random.nextInt(10);
        var integerList = IntStream.range(1, n).boxed().collect(Collectors.toList());
        List<Integer> first = integerList.stream().limit(1).collect(Collectors.toList());
        List<Integer> rest = integerList.stream().skip(1).collect(Collectors.toList());
        System.out.println(first);
        System.out.println(rest);
    }
}
