package multi_threading.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is an example showing that, if current thread is trying to acquire a lock
 * on existing acquired lock by different thread. Then current thread will go in
 * suspended state.
 * <p>
 * Also, calling the interrupt method, and handling the interruption logic
 * would not work here. See below
 */
public class LockInterruptionFailedDemo {
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
            lock.lock();

            /*
                This interruption logic to wake up thread would not work, as the thread
                would get suspended in the above lock() method.
                When another thread will release the lock(). Then it will come
                to this line of code and will check for interrupted flag.
             */
            if (Thread.currentThread().isInterrupted()) {
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
