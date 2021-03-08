package multi_threading.async;

import java.util.concurrent.CompletableFuture;

/**
 * This is an example on how to handle exception in CompletableFuture.
 * Using exceptionally one can handle exception and can recover from it. For example
 * if there is any exception then use exceptionally to return an error value and continue
 * from there.
 */
public class AsyncDemo5 {

    private static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(AsyncDemo5::compute);
    }

    private static int compute() {
        throw new RuntimeException("something went wrong");
//        return 2;
    }

    public static void main(String[] args) {
        create()
                .thenApply(data -> data * 2)
                .exceptionally(AsyncDemo5::handleException2)
                .thenAccept(System.out::println)
                .exceptionally(AsyncDemo5::handleException);
    }

    private static Void handleException(Throwable throwable) {
        System.out.println(throwable);
        throw new RuntimeException("it is beyond all hope");
    }

    private static int handleException2(Throwable throwable) {
        System.out.println(throwable);
        return -1;
    }
}
