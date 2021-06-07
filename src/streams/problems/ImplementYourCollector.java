package streams.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This is an example of how to create your own collector.
 */
public class ImplementYourCollector {
    public static void main(String[] args) {
        customCollector2();
        customCollector1();
    }

    private static void customCollector2() {
        // this will give correct result as all the tasks are divided, and after computing the result are added together.
        var myIntCollector = Collector.of(() -> new long[1],
                (long[] result, Integer value) -> result[0] += value,
                (long[] result1, long[] result2) -> {result1[0] += result2[0]; return result1;},
                Collector.Characteristics.IDENTITY_FINISH,
                Collector.Characteristics.UNORDERED);
        var count = collectStream(false, myIntCollector)[0];
        System.out.println(count);

        // this will give incorrect output because of shared mutability of countArray.
        var countArray = new long[1];
        forEachStream(false, x -> countArray[0] += x);
        System.out.println(countArray[0]);
    }

    private static void customCollector1() {
        var isSerial = false;
        // this will work in parallel stream, because we are dividing tasks and then combining, using combiner thereby reducing
        var myList = collectStream(isSerial, Collector.of((Supplier<MyList<Integer>>) MyList::new,
                MyList::add, (left, right) -> {left.addAll(right); return left;},
                        Collector.Characteristics.IDENTITY_FINISH
                ));
        System.out.println(myList.size());


        // this is wrong if run in parallel because of shared mutability
        var myList2 = new MyList<Integer>();
        forEachStream(isSerial, myList2::add);
        System.out.println(myList2.size());
    }

    private static void forEachStream(boolean isSerial, Consumer<Integer> consumer) {
        var stream = integerStream();
        var now = System.currentTimeMillis();
        ((isSerial) ? stream : stream.parallel()).forEach(consumer);
        var newNow = (System.currentTimeMillis() - now);
        System.out.println(newNow);
    }

    private static <V, R> R collectStream(boolean isSerial, Collector<Integer, V, R> collector) {
        var stream = integerStream();
        var now = System.currentTimeMillis();
        var result = ((isSerial) ? stream : stream.parallel()).collect(collector);
        var newNow = (System.currentTimeMillis() - now);
        System.out.println(newNow);
        return result;
    }

    private static Stream<Integer> integerStream() {
        return IntStream.range(0, 100_000_00).boxed();
    }
}

class MyList<T> {
    private final List<T> list;

    public MyList(int capacity) {
        list = new ArrayList<>(capacity);
    }

    public MyList() {
        list = new ArrayList<>();
    }

    public boolean add(T data) {
        return list.add(data);
    }

    public boolean addAll(MyList<? extends T> collection) {
        if (collection == null) {
            return false;
        }
        return list.addAll(collection.list);
    }

    public String toString() {
        return list.toString();
    }

    public int size() {
        return list.size();
    }
}
