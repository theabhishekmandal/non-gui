package streams.problems;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

/**
 * Given a list of values, find the distinct values which appear n times and reject the others
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
