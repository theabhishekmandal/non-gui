package multi_threading.lock_free_techniques.data_structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is an example of how Normal Stack performs and LockFree Stack performs when run Separately.
 * Note that it is important to run them separately since, JVM optimises with time.
 * Also note that in both Pop and Push operations we are incrementing the counter rather than incrementing
 * counter in push and decrementing counter in pop.
 * This is done to know how many total operations we performed.
 */
public class CheckBothStack {
    public static void main(String[] args) throws InterruptedException {
//        StandardStack<Integer> stack = new StandardStack<>();

        AtomicStack<Integer> stack = new AtomicStack<>();
        Random random = new Random();

        for (int i = 0; i < 100000; i++) {
            stack.push(random.nextInt());
        }


        List<Thread> threads = new ArrayList<>();

        int pushingThreads = 2;
        int poppingThreads = 2;

        for (int i = 0; i < pushingThreads; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    stack.push(random.nextInt());
                }
            });
            thread.setDaemon(true);
            threads.add(thread);
        }


        for (int i = 0; i < poppingThreads; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    stack.pop();
                }
            });
            thread.setDaemon(true);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        Thread.sleep(10000);

        System.out.printf("%,d operations were performed in 10 seconds", stack.getCounter());
    }
}
