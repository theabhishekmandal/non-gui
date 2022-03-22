package multi_threading.deadlock.examples;

/**
 * The static Metrics class from MetricsWithPossibleDeadLockDemo.java was modified below.
 * All the methods are using synchronized keyword on methods and therefore, no possibility
 * of deadlock remains. But, one disadvantage is that the code will be slow.
 */
public class MetricsWithSlownessDemo {
    public static void main(String[] args) {
        System.out.println("No code here");
    }

    public static class Metrics {
        private long count;
        private double average;
        private long max;

        public synchronized void addSample(long sample) {
            average = (average * count + sample) / (++count);
            max = Math.max(max, sample);
        }

        public synchronized void reset() {
            count = 0;
            max = Integer.MIN_VALUE;
            average = 0.0;
        }

        public synchronized long getCount() {
            return count;
        }

        public synchronized long getMax() {
            return max;
        }

        public synchronized double getAverage() {
            return average;
        }
    }
}
