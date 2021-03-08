package multi_threading.async;

import java.util.concurrent.CompletableFuture;

/**
 * combine method is used to combine two completable futures and concat there result
 * compose method is use to create a new Completable future from the given result from another completable future
 */
public class AsyncDemo6 {

    private static CompletableFuture<Integer> create(int n) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return n;
        });
    }
    public static void main(String[] args) {
        combineDemo();
        composeDemo();
    }

    private static void composeDemo() {
        create(2)
                .thenCompose(AsyncDemo6::inc)
                .thenAccept(System.out::println);
        System.out.println("here");
        sleep(2000);
    }

    private static void combineDemo() {
        create(3).thenCombine(create(4), Integer::sum)
                .thenAccept(System.out::println);
        System.out.println("here");
        sleep(2000);
    }

    private static CompletableFuture<Integer> inc(int number) {
        return CompletableFuture.supplyAsync(() -> number + 1);
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
