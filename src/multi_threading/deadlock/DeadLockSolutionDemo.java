package multi_threading.deadlock;

import java.util.Random;

/**
 * This is an example of deadlock situation.
 * trainAThread             trainBThread
 * lock(A)                  lock(A)
 * lock(B)                  lock(B)
 * train A is passing       train B is passing
 * release(B)               release(A)
 * release(A)               release(B)
 * <p>
 * - In this example we are trying to avoid the deadlock situation.
 * - If we check the order in acquiring the lock from above, we will notice that
 * both of threads use the same order for acquiring the lock i.e. lock(A) and lock(B)
 * - In previous example it was different which caused the deadlock situation.
 * - In this when trainAThread tries to acquire the lock(A) at the same time trainBThread
 * also tries to acquire the lock(A). But out of two only one of them will succeed. The
 * succeeded one will also be able to acquire lock(B).
 * - Also note that order of release of lock is not important here.
 */
public class DeadLockSolutionDemo {
    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        Thread trainAThread = new Thread(new TrainA(intersection));
        Thread trainBThread = new Thread(new TrainB(intersection));

        trainAThread.start();
        trainBThread.start();
    }

    public static class TrainA implements Runnable {
        private final Intersection intersection;
        private final Random random;


        public TrainA(Intersection intersection) {
            this.intersection = intersection;
            random = new Random();
        }

        @Override
        public void run() {
            while (true) {
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                intersection.takeRoadA();
            }
        }
    }

    public static class TrainB implements Runnable {
        private final Intersection intersection;
        private final Random random;


        public TrainB(Intersection intersection) {
            this.intersection = intersection;
            random = new Random();
        }

        @Override
        public void run() {
            while (true) {
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                intersection.takeRoadB();
            }
        }
    }

    public static class Intersection {
        private final Object roadA = new Object();
        private final Object roadB = new Object();

        public void takeRoadA() {
            synchronized (roadA) {
                System.out.println("Road A is locked by thread " + Thread.currentThread().getName());
                synchronized (roadB) {
                    System.out.println("Train is passing through road A");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void takeRoadB() {
            synchronized (roadA) {
                System.out.println("Road A is locked by thread " + Thread.currentThread().getName());
                synchronized (roadB) {
                    System.out.println("Train is passing through road B");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
