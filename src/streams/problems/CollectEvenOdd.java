package streams.problems;

import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectEvenOdd {
    public static void main(String[] args) {
        Random random = new Random();
        int n = 1 + random.nextInt(50);
        var integerList = IntStream.range(1, n).boxed().collect(Collectors.toList());

        var map = integerList.stream().collect(Collectors.groupingBy(x -> x % 2));
        var evenList = map.getOrDefault(0, Collections.emptyList());
        var oddList = map.getOrDefault(1, Collections.emptyList());

        System.out.println(evenList);
        System.out.println(oddList);
    }
}
