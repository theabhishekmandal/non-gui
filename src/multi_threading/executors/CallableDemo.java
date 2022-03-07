package multi_threading.executors;

import multi_threading.executors.dto.FactorialCallable;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This is a simple example showing how Callable works,
 * - Using ExecutorService we submit a callable task to executor using submit() method.
 * - Submit method returns a future object.
 * - Using this future object we call the get method to fetch the result.
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<BigInteger> factorialFuture = executorService.submit(new FactorialCallable(100L));

        System.out.println("new callable task execution started");
        BigInteger factorialValue = factorialFuture.get();
        System.out.println("factorial value " + factorialValue);
        System.out.println("Main Completed");
        executorService.shutdown();
    }


}
