package multi_threading.resource_sharing.synchronized_examples;

/**
 * Here similar to previous example. If Thread1 is executing the increment method
 * then Thread2 cannot use the decrement method and vice versa.
 */
public class SynchronizedDemo2 {
    public static void main(String[] args) throws InterruptedException {
        SharedClass sharedObject = new SharedClass();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                sharedObject.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                sharedObject.decrement();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(sharedObject.counter);
    }

    static class SharedClass {
        private int counter = 0;

        public synchronized void increment() {
            this.counter++;
        }

        public synchronized void decrement() {
            this.counter--;
        }
    }
}
