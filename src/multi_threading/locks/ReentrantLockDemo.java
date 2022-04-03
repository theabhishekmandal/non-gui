package multi_threading.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is an example of showing what Reentrant lock means. Reentrant lock means
 * that the same thread can acquire the same lock again. And each time the thread
 * acquires the lock the count is incremented. Lock is released when the same
 * thread unlocks it that many times.
 */
public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread one = new TestThread(lock, "A");
        Thread two = new TestThread(lock, "B");

        one.start();
        Thread.sleep(100);
        two.start();

        one.join();
        two.join();
    }

    static class TestThread extends Thread {
        private final Lock lock;

        TestThread(Lock lock, String name) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            int lockUnlockTime = 10;
            for (int i = 0; i < lockUnlockTime; i++) {
                lock();
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < lockUnlockTime; i++) {
                unlock();
            }
        }

        public void lock() {
            if (lock.tryLock()) {
                System.out.println("lock acquire by thread " + Thread.currentThread().getName());
            } else {
                System.out.println("lock acquire failed by thread " + Thread.currentThread().getName());
            }
        }

        public void unlock() {
            if (lock instanceof ReentrantLock reentrantLock) {
                // Checking if the lock is held by current thread then only release the lock
                if (reentrantLock.isHeldByCurrentThread()) {
                    System.out.println("lock is held by thread " + Thread.currentThread().getName() + " so unlocking");
                    lock.unlock();
                } else {
                    System.out.println("lock is not held by thread " + Thread.currentThread().getName() + " so not unlocking");
                }
            }
        }
    }
}
