package multi_threading.async;

import java.util.concurrent.CompletableFuture;

/**
 * If the task is not completed then thenAccept will be called by forkJoinPool thread. If the task is completed then
 * it will be called by main thread
 */
public class AsyncDemo3 {
    public static void main(String[] args) {

        // uncomment sleep method in compute
        System.out.println("In Main thread " + Thread.currentThread());

        // creating non blocking task, will be done by different thread other than the main thread
        var future = create();

        // as the task is not complete (as we introduced sleep method in compute) after the sleep operation,
        // the printIt method will be executed by forkJoinPool
        // threads
        sleep(100);
        future.thenAccept(AsyncDemo3::printIt);
        System.out.println("here");
        sleep(1000);
    }

    private static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(AsyncDemo3::compute);
    }

    private static int compute() {
        System.out.println("compute: " + Thread.currentThread());
        sleep(1000);
        return 2;
    }

    private static void printIt(int data) {
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
