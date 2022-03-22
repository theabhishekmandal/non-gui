package multi_threading.deadlock.examples;

/**
 * No code here for execution.
 * <p>
 * addSample and reset are two methods which can be executed separately in
 * two different threads.
 * <p>
 * Note here addSample and reset can cause deadlock issue since the order
 * of locks are not uniform in both of these methods
 */
public class MetricsWithPossibleDeadLockDemo {
    public static void main(String[] args) {
        System.out.println("No code here");
    }

    public static class Metrics {
        private final Object countLock = new Object();
        private final Object averageLock = new Object();
        private final Object maxLock = new Object();
        private long count;
        private double average;
        private long max;

        public void addSample(long sample) {
            synchronized (countLock) {
                synchronized (averageLock) {
                    synchronized (maxLock) {
                        average = (average * count + sample) / (++count);
                        max = Math.max(max, sample);
                    }
                }
            }
        }

        public void reset() {
            synchronized (maxLock) {
                synchronized (averageLock) {
                    synchronized (countLock) {
                        count = 0;
                        max = Integer.MIN_VALUE;
                        average = 0.0;
                    }
                }
            }
        }

        public long getCount() {
            synchronized (countLock) {
                return count;
            }
        }

        public long getMax() {
            synchronized (maxLock) {
                return max;
            }
        }

        public double getAverage() {
            synchronized (averageLock) {
                return average;
            }
        }
    }
}
