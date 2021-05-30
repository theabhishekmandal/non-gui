package multi_threading.problems;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a integer, try to update it using 5000 threads and the value of that variable should be 5000.
 */
public class IncrementAndCheck {
    private static final ExecutorService service = Executors.newFixedThreadPool(5000);
    public static void main(String[] args) throws InterruptedException {
        demo1();
        demo2();

        // wait for service to finish and then shutdown
        service.shutdown();
    }

    private static void demo2() throws InterruptedException {
        var two = new Two();
        List<Callable<Integer>> list = IntStream.range(0, 5000)
                .mapToObj(x -> (Callable<Integer>)two::update)
                .collect(Collectors.toList());
        var futureList = service.invokeAll(list);
        var done = futureList.stream().allMatch(Future::isDone);
        if (done) {
            System.out.println("done threading " + two.a);
        } else {
            System.out.println("not done threading " + two.a);
        }
    }

    private static void demo1() throws InterruptedException {
        var one = new One();
        List<Callable<Integer>> list = IntStream.range(0, 5000)
                .mapToObj(x -> (Callable<Integer>)one::update)
                .collect(Collectors.toList());
        var futureList = service.invokeAll(list);
        var done = futureList.stream().allMatch(Future::isDone);
        if (done) {
            System.out.println("done threading " + one.a);
        } else {
            System.out.println("not done threading " + one.a);
        }
    }

}

class One {
    final AtomicInteger a = new AtomicInteger(0);
    public int update() {
        return a.incrementAndGet();
    }
}

class Two {
    int a = 0;
    public synchronized int update() {
        return ++a;
    }
}
