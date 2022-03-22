package multi_threading.deadlock;

import java.util.Random;

/**
 * This is an example of deadlock situation.
 * trainAThread             trainBThread
 * lock(A)                  lock(B)
 * lock(B)                  lock(A)
 * train A is passing       train B is passing
 * release(B)               release(A)
 * release(A)               release(B)
 * <p>
 * - Here for a train A to pass first trainThreadA locks A and then B
 * - And for train B to pass second trainThreadB locks B and then A.
 * - This causes a deadlock situation as, first trainThreadA tries to lock A
 * and simultaneously trainThreadB tries to lock B which is also required
 * by trainThreadA.
 * - Like this we are in circular deadlock situation in which the lock that
 * trainThreadA is trying to acquire is already held by trainThreadB.
 * - Check next example for possible solution
 */
public class DeadLockDemo {
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
            synchronized (roadB) {
                System.out.println("Road B is locked by thread " + Thread.currentThread().getName());
                synchronized (roadA) {
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
