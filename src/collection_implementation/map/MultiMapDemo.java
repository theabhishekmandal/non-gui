package collection_implementation.map;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This is an example of various operations that can be performed on multimap.
 */

public class MultiMapDemo {
    public static void main(String[] args) {
        var random = new Random();
        var list = random.ints(1, 100)
                .limit(100)
                .boxed()
                .collect(Collectors.toList());

        Map<Integer, List<Integer>> map = new HashMap<>();
        var joiner = new StringJoiner(", ");

        for (var i : list) {

            // contains check
            boolean contains = map.getOrDefault(i, List.of()).contains(i);
            joiner.add(String.valueOf(contains));

            // put using multimap
            map.computeIfAbsent(i, x -> new ArrayList<>()).add(i);
        }

        System.out.println(joiner);
        System.out.println(map);

        // size of values
        System.out.println(map.values().stream().mapToInt(List::size).count());

        // all values collected into list
        System.out.println(map
                .values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()));

        // remove operation from map
        for (var i : list) {
            map.computeIfPresent(i, (x, y) -> y.remove(i) && y.isEmpty()? null : y);
        }
        System.out.println(map);
    }
}
