package streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * its one of the common ways to get stuff out of the stream once we are done with all the processing.
 *
 * collect() performs mutable fold operations (repackaging elements to some data structures and applying some additional logic,
 * concatenating them, etc.) on data elements held in the Stream instance
 * The strategy for this operation is provided via the Collector interface implementation.
 */
public class CollectDemo {
    public static void main(String[] args) {
        List<Integer> arr = IntStream.range(0, 10)
                .map(x -> x * x)
                .boxed()                        //  Need to apply boxed() to IntStream to convert it into Stream<Integer>
                .collect(Collectors.toList());
        System.out.println(arr);
    }
}
