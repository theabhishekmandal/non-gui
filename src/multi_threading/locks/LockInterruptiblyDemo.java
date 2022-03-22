package multi_threading.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * In previous example LockInterruptionFailedDemo we saw that acquiring already existing lock
 * by current thread was making it to go in suspended state.
 * <p>
 * Also, calling the interrupt method and handling interruption logic was not working.
 * <p>
 * Here, below calling interrupt() method we can still get out of the suspended state.
 */
public class LockInterruptiblyDemo {
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
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("thread " + Thread.currentThread().getName() + " was acquiring already acquired lock");
                return;
            }

            try {
                System.out.println("Lock acquired by " + Thread.currentThread().getName());
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                System.out.println("thread " + Thread.currentThread().getName() + " was interrupted");
            } finally {
                System.out.println("Lock released by " + Thread.currentThread().getName());
                lock.unlock();
            }
        }
    }
}
