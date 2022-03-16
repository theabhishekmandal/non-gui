package multi_threading.race_condition.atomic.problems;

import java.util.Random;

/**
 * In this exercise we are going to implement a class called MinMaxMetrics .
 * <p>
 * A single instance of this class will be passed to multiple threads in our application.
 * <p>
 * MinMaxMetrics is an analytics class and is used to keep track of the minimum and the
 * maximum of a particular business or performance metric in our application.
 * <p>
 * Example:
 * <p>
 * A stock trading application that keeps track of the minimum and maximum price of the stock on a daily basis.
 * <p>
 * <p>
 * <p>
 * The class will have 3 methods:
 * <p>
 * addSample(..) - Takes a new sample.
 * <p>
 * getMin() - Returns the sample with the minimum value we have seen so far.
 * <p>
 * getMax() - Returns the sample with the maximum value we have seen so far.
 * <p>
 * <p>
 * <p>
 * Notes:
 * <p>
 * -    Each of those methods can be called by any given number threads concurrently,
 * so the class needs to be thread safe.
 * <p>
 * -    In addition, this class is used for analytics, so it needs to be as performant as possible
 * as we don't want it to slow down our business logic threads too much.
 * <p>
 * -    Threads that call getMin() or getMax() are interested in only one of the values and are
 * never interested in both the min and the max in the same time
 */
public class MinMaxDemo {
    public static void main(String[] args) {
        MinMaxMetrics minMaxMetrics = new MinMaxMetrics();
        Random random = new Random();

        Thread one = new Thread(() -> {
            while (true) {
                extracted();
                minMaxMetrics.addSample(random.nextInt(Integer.MAX_VALUE));
            }
        });

        Thread two = new Thread(() -> {
            while (true) {
                extracted();
                minMaxMetrics.addSample(random.nextInt(Integer.MAX_VALUE));
            }
        });

        Thread three = new Thread(() -> {
            while (true) {
                extracted();
                System.out.println("Max value " + minMaxMetrics.getMax());
                System.out.println("Min value " + minMaxMetrics.getMin());
            }
        });

        one.start();
        two.start();
        three.start();

    }

    private static void extracted() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class MinMaxMetrics {

        private volatile long minValue;
        private volatile long maxValue;

        /**
         * Initializes all member variables
         */
        public MinMaxMetrics() {
            this.maxValue = Long.MIN_VALUE;
            this.minValue = Long.MAX_VALUE;
        }

        /**
         * Adds a new sample to our metrics.
         */
        public void addSample(long newSample) {
            synchronized (this) {
                this.minValue = Math.min(newSample, this.minValue);
                this.maxValue = Math.max(newSample, this.maxValue);
            }
        }

        /**
         * Returns the smallest sample we've seen so far.
         */
        public long getMin() {
            return this.minValue;
        }

        /**
         * Returns the biggest sample we've seen so far.
         */
        public long getMax() {
            return this.maxValue;
        }

    }
}
