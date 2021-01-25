package streams.problems;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Given a list of values, convert them to count map where each value should be initialised with default value of 0
 */
public class ListToCountMap {
    public static void main(String[] args) {
        List<Integer> arr = List.of(1, 2, 3, 4, 1);
        Map<Integer, Integer> zeroMap = arr.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(0, x -> 0, Integer::sum)));
        System.out.println(zeroMap);
    }
}
