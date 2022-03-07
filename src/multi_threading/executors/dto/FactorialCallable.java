package multi_threading.executors.dto;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public class FactorialCallable implements Callable<BigInteger> {

    private final long inputNumber;
    private BigInteger result;

    public FactorialCallable(long inputNumber) {
        this.inputNumber = inputNumber;
    }

    public BigInteger getResult() {
        return this.result;
    }

    @Override
    public BigInteger call() throws Exception {
        System.out.println("Factorial calculation started");
        BigInteger tempResult = BigInteger.ONE;
        for (long i = 2; i <= inputNumber; i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Factorial calculation interrupted");
                return BigInteger.ZERO;
            }
            tempResult = tempResult.multiply(new BigInteger(String.valueOf(i)));
            this.result = tempResult;
        }
        System.out.println("Factorial calculation stopped");
        return tempResult;
    }
}

