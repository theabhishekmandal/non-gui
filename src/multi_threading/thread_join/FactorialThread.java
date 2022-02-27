package multi_threading.thread_join;

import java.math.BigInteger;

public class FactorialThread extends Thread {
    protected final long inputNumber;
    protected BigInteger result;
    protected boolean finished;

    FactorialThread(long inputNumber) {
        this.inputNumber = inputNumber;
        this.finished = false;
        this.result = BigInteger.ZERO;
    }

    @Override
    public void run() {
        this.result = factorial(this.inputNumber);
        this.finished = true;
    }

    private BigInteger factorial(long inputNumber) {
        BigInteger tempResult = BigInteger.ONE;
        for (long i = 2; i <= inputNumber; i++) {
            tempResult = tempResult.multiply(new BigInteger(String.valueOf(i)));
        }
        return tempResult;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public BigInteger getResult() {
        return this.result;
    }

    public long getInputNumber() {
        return this.inputNumber;
    }
}