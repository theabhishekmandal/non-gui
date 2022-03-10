package multi_threading.resource_sharing.synchronized_examples;

/**
 * In this example Since there is no sharedObject between two threads Thread1 and
 * Thread2. Both will execute simultaneously without blocking.
 */
public class SynchronizedDemo3 {
    public static void main(String[] args) throws InterruptedException {
        SharedClass sharedObject1 = new SharedClass();
        SharedClass sharedObject2 = new SharedClass();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                sharedObject1.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                sharedObject2.increment();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(sharedObject1.counter);
        System.out.println(sharedObject2.counter);
    }

    static class SharedClass {
        private int counter = 0;

        public synchronized void increment() {
            this.counter++;
        }
    }
}
