package multi_threading.async;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * This is similar to AsyncDemo4 but here we discuss about two methods completeOnTimeout, orTimeout.
 */
public class AsyncDemo42 {
    public static void main(String[] args) {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        future.thenApply(x -> x * 2)
                .exceptionally(AsyncDemo42::handleException)
                .thenApply(x -> x + 1)
                .thenAccept(System.out::println);

        System.out.println("built the pipeline");

        // passing value in pipeline, use completeOnTimeOut, to wait for given timeout,
        // if the task is not completed then return the value passed.
        if (new Random().nextBoolean()) {
            System.out.println("executing completeOnTimeOut");
            future.completeOnTimeout(0, 2, TimeUnit.SECONDS);
        }
        // use orTimeout to blow up if the task is not completed in given time
        else {
            System.out.println("executing orTimeOut");
            future.orTimeout(2, TimeUnit.SECONDS);
        }
        // wait for 1 second if none of the above finishes then use the below parameter to execute
        sleep(3000);
//        future.complete(2);
    }

    private static Integer handleException(Throwable throwable) {
        System.out.println("ERROR -- " + throwable);
        return 0;
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
