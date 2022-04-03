package multi_threading.object_methods;

/**
 * This is an example of Producer and Consumer problem
 * In this example we can see that it has some error if we look at the output
 * In a Producer and Consumer problem the  Producer produces the data and the Consumer consumes
 * the data. This is done with the help of a Queue.
 *
 * In the following example we can see that Nothing stops the Producer from overrunning the Consumer
 * and nothing stops Consumer from overrunning the Producer. That's why we can see that for one value of
 * the data produced by the Producer , Consumer gets the same value many times and vice versa.
 *
 * In this both the Consumer and the Producer doesn't have communication between each other
 * For the communication we will use various methods  in the next example.
 */

public class ProduceConsumerDemo {
    public static void main(String[] args) throws InterruptedException {
        Q ob = new Q();
        Producer ob1 = new Producer(ob);
        Consumer ob2 = new Consumer(ob);
        System.out.println("Press Ctrl-c to stop");

        ob1.start();
        ob2.start();

        ob1.join();
        ob2.join();

    }

    static class Q {
        private int n;

        synchronized void put(int n) {
            this.n = n;
            System.out.println("Put: " + this.n);
        }

        synchronized int get() {
            System.out.println("Got: " + this.n);
            return this.n;
        }
    }

    static class Consumer extends Thread {
        private final Q ob;

        Consumer(Q q) {
            super("Consumer");
            this.ob = q;
        }

        @Override
        public void run() {
            while (ob.get() != 10) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }
        }
    }


    static class Producer extends Thread {
        private final Q q;

        Producer(Q ob) {
            super("Producer");
            this.q = ob;
        }

        @Override
        public void run() {
            int i = 0;
            while (true) {
                q.put(i++);
                if (i == 10) {
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }
        }
    }
}
