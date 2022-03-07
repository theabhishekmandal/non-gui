package multi_threading.executors;

import multi_threading.executors.dto.FactorialCallable;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This example showing on how to use cancel() method.
 * - cancel() method internally calls the interrupt method on the thread.
 * - Also, you need to handle the interruption logic see FactorialCallable.java.
 */
public class CallableDemo3 {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        var factorialCallable = new FactorialCallable(10000L);
        Future<BigInteger> factorialFuture = executorService.submit(factorialCallable);

        System.out.println("new callable task execution started");

        // will not use get
        // BigInteger factorialValue = factorialFuture.get();
        boolean isCancelled = factorialFuture.cancel(true);

        System.out.println("future cancelled " + isCancelled);
        System.out.println("Main Completed");

        executorService.shutdown();
    }

}
