package multi_threading.deadlock.examples;

/**
 * The static Metrics class from MetricsWithSlownessDemo.java was modified below.
 * All the methods except addSample and reset are not using synchronized keyword.
 * But since, they are only getter methods with long and double primitive types their result
 * will be incorrect on Multithreaded environment.
 * <p>
 * To avoid the race condition either we should use volatile keyword or we can wrap with
 * synchronized keywords in getter method.
 */
public class MetricsWithIncorrectResultDemo {
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

        public long getCount() {
            return count;
        }

        public long getMax() {
            return max;
        }

        public double getAverage() {
            return average;
        }
    }
}
