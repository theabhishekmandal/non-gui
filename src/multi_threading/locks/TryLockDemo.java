package multi_threading.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is an example of tryLock.
 * -   In this the thread tries to see if the lock is free. If it is free then it acquires the lock.
 * -   However, if it is not available then it does not block the thread.
 */
public class TryLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread one = new TestThread("one", lock);
        Thread two = new TestThread("two", lock);

        one.start();
        two.start();

        // calling interrupt method so that it will wake up
        two.interrupt();

        one.join();
        two.join();
    }

    static class TestThread extends Thread {
        private final Lock lock;

        public TestThread(String name, Lock lock) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            if (lock.tryLock()) {
                try {
                    System.out.println("Lock acquired by " + Thread.currentThread().getName());
                    Thread.sleep(50000);
                } catch (InterruptedException e) {
                    System.out.println("thread " + Thread.currentThread().getName() + " was interrupted");
                } finally {
                    System.out.println("Lock released by " + Thread.currentThread().getName());
                    lock.unlock();
                }
            } else {
                System.out.println("thread " + Thread.currentThread().getName() + " was acquiring already acquired lock");
            }
        }
    }
}
