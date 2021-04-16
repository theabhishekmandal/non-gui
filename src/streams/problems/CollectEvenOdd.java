package streams.problems;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Use partitioning by to collect to
 */
public class CollectEvenOdd {
    public static void main(String[] args) {
        Random random = new Random();
        int n = 1 + random.nextInt(2);
        var integerList = IntStream.range(1, n).boxed().collect(Collectors.toList());

        var map = integerList.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0));
        var evenList = map.get(true);
        var oddList = map.get(false);
        System.out.println(evenList);
        System.out.println(oddList);
    }
}
