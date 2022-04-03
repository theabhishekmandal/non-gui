package multi_threading.semaphore;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * This is an example in which we want to perform operations in sequential way.
 * If we have 10 threads then all of them should first perform task1 and after that they should
 * perform task 2.
 * Ordering of threads while performing task1 or task2 does not matter. Only all threads should
 * first do task1 and then do task2.
 *
 * To achieve this we will use Semaphore(0). After doing the first task, we will call
 * to acquire semaphore for all threads except the last one.
 *
 * Each of the threads will get blocked and will go in waiting state. It will be
 * the responsibility of the last thread to release(n-1) permits which blocked the
 * remaining n - 1 threads.
 */
public class BarrierDemo {

    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 10;
        Barrier barrier = new Barrier(numberOfThreads);
        List<CoordinatedWorkerThread> threadList = IntStream.range(0, numberOfThreads)
                .mapToObj(x -> new CoordinatedWorkerThread(String.valueOf(x), barrier)).toList();

        for (var thread : threadList) {
            thread.start();
        }

        for (var thread : threadList) {
            thread.join();
        }
    }


    static class Barrier {
        private final int numberOfWorkers;
        private final Semaphore semaphore = new Semaphore(0);
        private int counter = 0;
        private final Lock lock = new ReentrantLock();

        public Barrier(int numberOfWorkers) {
            this.numberOfWorkers = numberOfWorkers;
        }

        public void barrier() throws InterruptedException {
            lock.lock();
            boolean isLastWorker = false;
            try {
                counter++;
                if (counter == this.numberOfWorkers) {
                    isLastWorker = true;
                }
            } finally {
                lock.unlock();
            }

            if (isLastWorker) {
                semaphore.release(this.numberOfWorkers - 1);
            } else {
                semaphore.acquire();
            }
        }

    }

    static class CoordinatedWorkerThread extends Thread {
        private final Barrier barrier;

        public CoordinatedWorkerThread(String name, Barrier barrier) {
            super(name);
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                task();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        private void task() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " thread Task 1 completed");
            barrier.barrier();
            System.out.println(Thread.currentThread().getName() + " thread Task 2 completed");
        }
    }
}
