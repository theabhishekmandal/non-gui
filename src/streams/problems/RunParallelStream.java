package streams.problems;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * We can configure the parallelStream to use n number of threads by using Fork Join Pool
 * Just create the parallelStream and do the terminal operation in submit method
 */
public class RunParallelStream {

    private static void process(Stream<Integer> stream) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(100);
        pool.submit(() -> stream.forEach(e -> {
        }));
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
        process(list.parallelStream().map(RunParallelStream::transform));
    }


    private static int transform(int a) {
        System.out.println("t: " + a + "--" + Thread.currentThread());
        sleep(1000);
        return a;
    }

    private static boolean sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }
}
