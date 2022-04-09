package multi_threading.lock_free_techniques;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Main Disadvantage of Atomic objects are that, two or more atomic operations are themselves
 * not atomic in nature.
 * For eg: Below are two operations
 *  -   add() and reset(). add() updates the value and reset() resets the value.
 *  -   both of these operations are done on atomic objects. But both these methods are used
 *      by different threads
 *  -   So there might be race conditions on method level of add() and reset() and you will
 *      see different result everytime
 *
 * Simple fact is that non-atomic operations on atomic objects are not race free.
 * Here Two threads are calling two different methods simultaneously causing the race condition
 */
public class AtomicOperationsDisadvantage {
    public static void main(String[] args) throws InterruptedException {
        AtomicOperationsFail atomicOperationsFail = new AtomicOperationsFail();
        int size = Integer.MAX_VALUE;
        Thread one = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                atomicOperationsFail.add();
                atomicOperationsFail.reset();
            }
        });
        one.setDaemon(true);

        Thread two = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                atomicOperationsFail.add();
                atomicOperationsFail.reset();
            }
        });
        two.setDaemon(true);

        one.start();
        two.start();

        Thread.sleep(5000);
        System.out.println(atomicOperationsFail.getOne());
        System.out.println(atomicOperationsFail.getTwo());
    }



    static class AtomicOperationsFail {
        private final AtomicInteger one = new AtomicInteger(0);
        private final AtomicInteger two = new AtomicInteger(1);

        public void add() {
            one.addAndGet(1);
            two.addAndGet(2);
        }

        public void reset() {
            one.set(0);
            two.set(1);
        }

        public int getOne() {
            return one.get();
        }

        public int getTwo() {
            return two.get();
        }
    }
}
