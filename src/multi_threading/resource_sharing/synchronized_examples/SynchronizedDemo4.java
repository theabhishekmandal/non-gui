package multi_threading.resource_sharing.synchronized_examples;

/**
 * In this example
 * -    Thread1 and Thread2 are operating on same sharedObject but are accessing different methods
 * -    Also, both methods synchronize on different lock objects. So both Thread1 and Thread2 can execute
 * simultaneously without blocking each other.
 */
public class SynchronizedDemo4 {
    public static void main(String[] args) throws InterruptedException {
        SharedClass sharedObject = new SharedClass();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                sharedObject.incrementCounter1();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                sharedObject.incrementCounter2();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(sharedObject.counter1);
        System.out.println(sharedObject.counter2);
    }

    static class SharedClass {
        private final Object lock1 = new Object();
        private final Object lock2 = new Object();
        private int counter1 = 0;
        private int counter2 = 0;

        public void incrementCounter1() {
            synchronized (lock1) {
                this.counter1++;
            }
        }

        public void incrementCounter2() {
            synchronized (lock2) {
                this.counter2++;
            }
        }
    }
}
