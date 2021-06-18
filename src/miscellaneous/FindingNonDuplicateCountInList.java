package miscellaneous;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given a list of integers, collect those which are non - duplicate
 */
public class FindingNonDuplicateCountInList {
    public static void main(String[] args) {
        var random = new Random();

        List<Integer> list = random.ints(1, 10).limit(10)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> nonDuplicateList = list
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(x -> 1)))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(list);
        System.out.println(nonDuplicateList);
    }
}
