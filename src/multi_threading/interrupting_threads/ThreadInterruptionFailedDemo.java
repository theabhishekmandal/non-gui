package multi_threading.interrupting_threads;

import java.math.BigInteger;

/**
 * This is an example showing how even calling the interrupt method won't stop a running thread unless
 * it's interruption logic is explicitly handled. See example ThreadInterruptionPassedDemo.java
 */
public class ThreadInterruptionFailedDemo {
    public static void main(String[] args) {
        var longComputation = new Thread(new LongComputation(BigInteger.valueOf(2), BigInteger.valueOf(1000000)));

        longComputation.start();
        longComputation.interrupt();
    }

    record LongComputation(BigInteger base, BigInteger power) implements Runnable {

        @Override
        public void run() {
            BigInteger result = base;
            for (BigInteger i = BigInteger.ONE; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
            System.out.println(base + "**" + power + " = " + result);
        }
    }
}
