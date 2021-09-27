package streams.problems;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * given two lists perform the operations on two lists using stream
 */
public class ZipTwoLists {
    public static <T1, T2, R> Stream<R> zip(List<T1> list1, List<T2> list2, BiFunction<? super T1, ? super T2, ? extends R> mapper) {
        if (list1.size() != list2.size()) {
            throw new IllegalArgumentException("Different list sizes");
        }
        return IntStream.range(0, list1.size()).mapToObj(x -> mapper.apply(list1.get(x), list2.get(x)));
    }

    public static void main(String[] args) {

        List<Integer> firstList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> secondList = Arrays.asList(6, 7, 8, 9, 10);
        zip(firstList, secondList, (x, y) -> x * y).forEach(System.out::println);
    }
}
