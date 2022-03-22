package multi_threading.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Example showing fixed size Thread pool
 *  -   Here we predefine the number of threads we want to use
 *  -   Tasks can be picked up by different threads simultaneously
 *
 *  Also remember to call the shutDown method to stop the application
 *  completely. This will ensure to interrupt any long-running threads
 *  and will stop them
 *
 */
public class FixedThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(new Task(1));
        executorService.execute(new Task(2));
        executorService.execute(new Task(3));

        executorService.shutdown();
    }

    record Task(int number) implements Runnable {

        @Override
        public void run() {
            System.out.println("\nTask " + number + " Started");

            for (int i = number * 100; i <= number * 100 + 99; i++) {
                System.out.print(i + " ");
            }

            System.out.println("\nTask " + number + "Done");
        }
    }
}
