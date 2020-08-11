package streams;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *  reduce methods takes an identity value and performs the accumulator function on that identity.
 *  In this example:
 *      int a = 0.0;
 *      for(int i : values) {
 *          a = accumulator.apply(a, i)
 *      }
 */

public class StreamReduceDemo {
    public static void main(String[] args) {

        // Reduction sum
        double total = Stream.of(7.3, 1.5, 4.8)
                .reduce(0.0, Double::sum);
        System.out.println("Total sum " + total);

        // Reduction summary statistics
        IntSummaryStatistics summary = IntStream.of(7, 3, 19, 88, 73, 4, 10)
                                        .summaryStatistics();
        System.out.println(summary);
    }
}
