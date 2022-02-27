package multi_threading.thread_join;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * In the previous example we saw, even after the main thread stopped our application was still running
 * It was due to one of the thread which was still doing computation. Now in this example if we interrupt the
 * thread and explicitly handle the interruption then we can stop the application completely.
 */
public class MultiThreadingWithThreadJoinDemo2 {
    public static void main(String[] args) throws InterruptedException {
        List<Long> list = Arrays.asList(333L, 1234L, 5467L, 6668L, 92340L, 10000000L);
        List<FactorialThread> factorialThreads = new ArrayList<>(list.size());

        // creating Factorial Thread
        for (Long number : list) {
            factorialThreads.add(new FactorialThreadNew(number));
        }

        // starting all the threads
        for (FactorialThread thread : factorialThreads) {
            thread.start();
        }

        // waiting almost 2 secs to finish the job
        for (FactorialThread factorialThread : factorialThreads) {
            factorialThread.join(2000);
        }

        // checking whether finished or not
        for (FactorialThread factorialThread : factorialThreads) {
            if (factorialThread.isFinished()) {
                System.out.println("factorial of number " + factorialThread.getInputNumber()
                        + " is " + factorialThread.getResult());
            } else {

                // making the factorial thread to interrupt because it is not stopping
                factorialThread.interrupt();
                System.out.println("factorial of number " + factorialThread.getInputNumber()
                        + " is being calculated");
            }
        }
    }

    static class FactorialThreadNew extends FactorialThread {

        FactorialThreadNew(long inputNumber) {
            super(inputNumber);
        }

        @Override
        public void run() {
            this.result = factorialNew(this.inputNumber);
            this.finished = true;
        }

        private BigInteger factorialNew(long inputNumber) {
            BigInteger tempResult = BigInteger.ONE;
            for (long i = 2; i <= inputNumber; i++) {

                // handling the interruption logic
                if (Thread.currentThread().isInterrupted()) {
                    return BigInteger.ZERO;
                }
                tempResult = tempResult.multiply(new BigInteger(String.valueOf(i)));
            }
            return tempResult;
        }
    }
}
