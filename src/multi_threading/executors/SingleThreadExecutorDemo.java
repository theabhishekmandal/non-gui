package multi_threading.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This example showing a single thread executor.
 *  -   Only one task is performed at a time
 *  -   Second task is picked when the previous task is finished.
 *
 *  Also remember to call the shutDown method to stop the application
 *  completely. This will ensure to interrupt any long-running threads
 *  and will stop them
 *
 */
public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(firstRunnable());
        executorService.execute(secondRunnable());
        executorService.shutdown();
    }

    public static Runnable firstRunnable() {
        return () -> {
            var br = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                br.append(i).append(" ");
            }
            System.out.println(br);
        };

    }

    public static Runnable secondRunnable() {
        return () -> {
            var br = new StringBuilder();
            for (int i = 10; i < 20; i++) {
                br.append(i).append(" ");
            }
            System.out.println(br);
        };
    }
}
