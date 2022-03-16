package multi_threading.race_condition.atomic;

import java.util.Random;

/**
 * This is an example showing how metrics are used in Production application, to know how fast
 * the business operations are performing.
 * <p>
 * Here the Metrics class is made through using synchronization, since it will be shared between
 * two business logic operations running on separate threads.
 * <p>
 * MetricsPrinter prints the value of all the results after every 100ms.
 */
public class AtomicOperationsDemo {
    public static void main(String[] args) {
        Metrics metrics = new Metrics();
        BusinessLogic businessLogic = new BusinessLogic(metrics);
        BusinessLogic businessLogic1 = new BusinessLogic(metrics);
        MetricsPrinter metricsPrinter = new MetricsPrinter(metrics);

        businessLogic.start();
        businessLogic1.start();
        metricsPrinter.start();

    }

    public static class MetricsPrinter extends Thread {
        private final Metrics metrics;

        public MetricsPrinter(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
                double currentAverage = metrics.getAverage();
                System.out.println("Current Average is " + currentAverage);
            }
        }
    }

    public static class BusinessLogic extends Thread {
        private final Metrics metrics;
        private final Random random = new Random();

        public BusinessLogic(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                long start = System.currentTimeMillis();

                // business logic taking random units of time to complete
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException ex) {
                }
                long end = System.currentTimeMillis();
                metrics.addSample(end - start);
            }
        }
    }

    public static class Metrics {
        private long count;
        private volatile double average;

        public synchronized void addSample(long sample) {
            double currentSum = average * count;
            count++;
            average = (currentSum + sample) / count;
        }

        public double getAverage() {
            return this.average;
        }
    }
}
