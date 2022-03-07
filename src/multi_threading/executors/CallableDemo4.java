package multi_threading.executors;

import multi_threading.executors.dto.FactorialCallable;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.*;

/**
 * Example showing multiple callable.
 * - To use List of callable we pass it to the invokeAll() method
 * - invokeAll() method returns a list of Futures.
 * - invokeAny() will fetch that result which completed first.
 */
public class CallableDemo4 {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        List<FactorialCallable> factorialCallableList = List.of(new FactorialCallable(10000L),
                new FactorialCallable(800L));

        invokeAll(executorService, factorialCallableList);
        invokeAny(executorService, factorialCallableList);

        executorService.shutdown();
    }

    private static void invokeAny(ExecutorService executorService,
                                  List<FactorialCallable> factorialCallableList)
            throws InterruptedException, ExecutionException, TimeoutException {

        System.out.println("Invoke Any started");
        var factorialResult = executorService.invokeAny(factorialCallableList, 1, TimeUnit.SECONDS);

        System.out.println("result is " + factorialResult);
        System.out.println("Invoke Any finished\n");
    }

    private static void invokeAll(ExecutorService executorService,
                                  List<FactorialCallable> factorialCallableList)
            throws InterruptedException, ExecutionException {


        System.out.println("Invoke All started");
        List<Future<BigInteger>> futureList = executorService.invokeAll(factorialCallableList);

        for (var future : futureList) {
            System.out.println("result is " + future.get());
        }
        System.out.println("Invoke All finished\n");
    }
}
