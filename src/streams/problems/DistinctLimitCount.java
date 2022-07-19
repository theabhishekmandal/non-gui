package streams.problems;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * Given a list of values, find the distinct values which appear n times and reject the others.
 * How it is working
 *    -    In the predicate we have defined
 *         -   t -> map.merge(t, 1L, Long::sum) == atLeast;
 *         -   First we have to understand what map.merge does
 *             -   map.merge(t, 1L, Long::sum)
 *             -   It describes that whenever a key is encountered first time, then intialize
 *                 it's value as 1L.
 *             -   And when the same key is encountered again, use the same value as 1L and apply
 *                 the BiFunction which is Long::sum
 *             -   Eg: First time when Scala is added to   map ->  (Scala, 1L)
 *                     Second time it will be              map ->  (Scala, 1L + 1L)
 *                     Third time it will be               map ->  (Scala, 1L + 2L)
 *             -   Also, the merge method returns the newValue after applying the remapping function.
 *         -   Second, Now as we have done our main work of counting the number of occurrences, we
 *             have to just check, if the count returned by the map.merge is same as atLeast, then only
 *             those values should be allowed to pass the predicate.
 */
public class DistinctLimitCount {
    private static <T> Predicate<T> distinct(long atLeast) {
        Map<T, Long> map = new ConcurrentHashMap<>();
        return t -> map.merge(t, 1L, Long::sum) == atLeast;
    }

    public static void main(String[] args) {
        List<String> langs = List.of("Java", "Scala", "Kotlin", "Kotlin", "Scala", "Scala", "Groovy", "Kotlin");
        langs.stream().filter(distinct(3)).forEach(System.out::println);
        System.out.println();
        langs.stream().filter(distinct(1)).forEach(System.out::println);
    }
}
