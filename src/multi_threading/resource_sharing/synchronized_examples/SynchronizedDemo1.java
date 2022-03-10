package multi_threading.resource_sharing.synchronized_examples;

/**
 * Here only one thread can increment the sharedObject at a time. When thread1 is
 * executing, thread2 is blocked and when thread2 is executing thread1 is blocked.
 */
public class SynchronizedDemo1 {
    public static void main(String[] args) throws InterruptedException {
        SharedClass sharedObject = new SharedClass();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                sharedObject.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                sharedObject.increment();
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
    }
}
