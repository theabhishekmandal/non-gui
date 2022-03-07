package multi_threading.executors;

import multi_threading.executors.dto.FactorialCallable;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This Callable example showing that cancel() method on future does not work
 * if it is added after the get() method. Since, get() method is blocking call
 * it will go to cancel method after it fetches the result.
 */
public class CallableDemo2 {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var factorialCallable = new FactorialCallable(10000L);
        Future<BigInteger> factorialFuture = executorService.submit(factorialCallable);

        System.out.println("new callable task execution started");

        BigInteger factorialValue = factorialFuture.get();
        boolean isCancelled = factorialFuture.cancel(true);

        System.out.println("factorial value " + factorialValue);
        System.out.println("future cancelled " + isCancelled);
        System.out.println("Main Completed");

        executorService.shutdown();
    }

}
