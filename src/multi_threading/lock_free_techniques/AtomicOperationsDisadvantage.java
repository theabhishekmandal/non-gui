package multi_threading.lock_free_techniques;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicOperationsDisadvantage {
    public static void main(String[] args) throws InterruptedException {
        AtomicOperationsFail atomicOperationsFail = new AtomicOperationsFail();
        Thread one = new Thread(() -> {
            while (true) {
                atomicOperationsFail.update();
                atomicOperationsFail.reset();
            }
        });
        one.setDaemon(true);

        Thread two = new Thread(() -> {
            while (true) {
                atomicOperationsFail.update();
                atomicOperationsFail.reset();
            }
        });
        two.setDaemon(true);

        Thread monitorObject = new Thread(() -> {
            while (true) {
                System.out.println(atomicOperationsFail.getOne());
                System.out.println(atomicOperationsFail.getTwo());
            }
        });
        monitorObject.setDaemon(true);

        one.start();
        two.start();
        monitorObject.start();

        Thread.sleep(5000);
    }



    static class AtomicOperationsFail {
        private final AtomicInteger one = new AtomicInteger(0);
        private final AtomicInteger two = new AtomicInteger(1);

        public void update() {
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
