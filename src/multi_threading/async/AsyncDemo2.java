package multi_threading.async;

import java.util.concurrent.CompletableFuture;

/**
 * One thing to note that, methods like get and getNow will make the completableFuture as sync methods which
 * defeats the purpose of async programming.
 *
 * get will wait till the task is completed whereas getNow will check if the task is completed otherwise it will fetch
 * the passed parameter as result.
 */
public class AsyncDemo2 {

    public static void main(String[] args) {
        System.out.println("In Main thread " + Thread.currentThread());

        // creating non blocking task, will be done by different thread other than the main thread
        var completableFuture = create();
        printIt(completableFuture.getNow(0));
        System.out.println("here");
        sleep(2000);
    }

    private static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(AsyncDemo2::compute);
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
