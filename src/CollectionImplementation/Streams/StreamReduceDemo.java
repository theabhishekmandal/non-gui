package CollectionImplementation.Streams;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamReduceDemo {
    public static void main(String[] args) {

        // Reduction sum
        double total = Stream.of(7.3, 1.5, 4.8)
                .reduce(0.0, (Double a, Double b) -> a + b);
        System.out.println("Total sum " + total);

        // Reduction summary statistics
        IntSummaryStatistics summary = IntStream.of(7, 3, 19, 88, 73, 4, 10)
                                        .summaryStatistics();
        System.out.println(summary);
    }
}
