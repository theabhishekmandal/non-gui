package multi_threading.async;

import java.util.concurrent.CompletableFuture;

/**
 * In this example we use CompletableFuture to create async tasks. Async task is created using method supplyAsync.
 * which acts as a supplier
 *
 * the task that is passed in supplyAsync method is run by threads in ForkJoinPool. Also the main thread won't
 * wait for the task to be completed. Example: "here" is printed even though the task is not completed.
 *
 * thenAccept is used after the supplyAsync is completed and provides the result to thenAccept method.
 * If someone wants to know that thenAccept is completed or not, in that case just use thenRun method as
 * a logger statement to denote the task is completed or not.
 *
 */
public class AsyncDemo1 {
    public static void main(String[] args) {

        System.out.println("In Main thread " + Thread.currentThread());

        // creating non blocking task, will be done by different thread other than the main thread
        create()
                .thenApply(x -> x * 10.0)
                .thenAccept(AsyncDemo1::printIt)
                .thenRun(() -> System.out.println("task completed"));
        System.out.println("here");
        sleep(2000);
    }

    private static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(AsyncDemo1::compute);
    }

    private static int compute() {
        System.out.println("compute: " + Thread.currentThread());
        sleep(1000);
        return 2;
    }

    private static void printIt(double data) {
        System.out.println(data + "--" + Thread.currentThread());
    }


    private static boolean sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch(InterruptedException e) {
            return false;
        }
        return true;
    }
}
