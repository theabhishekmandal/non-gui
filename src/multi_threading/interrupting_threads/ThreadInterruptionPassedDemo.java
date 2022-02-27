package multi_threading.interrupting_threads;

import java.math.BigInteger;

/**
 * From previous example we have seen that only calling the interrupt method on a thread won't stop the
 * processing, we have to explicitly handle the interruption logic.
 */
public class ThreadInterruptionPassedDemo {
    public static void main(String[] args) {
        var longComputation = new Thread(new LongComputation(BigInteger.valueOf(2),
                BigInteger.valueOf(1000000)));

        longComputation.start();

        // interrupt after 2 seconds if computation is not completed
        sleep(2);
        longComputation.interrupt();
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    record LongComputation(BigInteger base, BigInteger power) implements Runnable {

        @Override
        public void run() {
            BigInteger result = base;
            for (BigInteger i = BigInteger.ONE; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                // what to do when interrupted
                if (Thread.currentThread().isInterrupted()) {
                    result = BigInteger.ZERO;
                    break;
                }
                result = result.multiply(base);
            }
            System.out.println(base + "**" + power + " = " + result);
        }

    }
}
