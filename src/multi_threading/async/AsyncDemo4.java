package multi_threading.async;

import java.util.concurrent.CompletableFuture;

/**
 * This is an example on how to create a async pipeline and how to use complete and completeExceptionally
 */
public class AsyncDemo4 {
    public static void main(String[] args) {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        future.thenApply(x -> x * 2)
                .exceptionally(AsyncDemo4::handleException)
                .thenApply(x -> x + 1)
                .thenAccept(System.out::println);

        System.out.println("built the pipeline");
        sleep(1000);

        // passing value in pipeline, use complete to evaluate pipeline
        if (Math.random() > 0.75) {
            future.complete(2);
        }
        // use completeExceptionally to throw exception in pipeline
        else {
           future.completeExceptionally(new RuntimeException("don't tell me what to do"));
        }
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
