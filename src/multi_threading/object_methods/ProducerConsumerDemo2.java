package multi_threading.object_methods;

/**
 * This is the new Example of Producer Consumer Problem.
 * In  the previous example we saw that the producer could produce a item once
 * but the consumer could consume the same item twice or many times . So, to avoid this
 * we use Inter-thread communications methods such as wait(),notify(),notifyAll()
 *
 * wait()       -   tells the calling thread to give up the monitor and go to sleep until some other thread
 *                  enters the same monitor and calls the notify() and notifyAll()
 *
 * notify()     -   wakes up thread that called wait() on the same object.
 *
 * notifyAll()  -   wakes up all thread that called wait() on the same object. One of the threads will be granted
 *                  access.
 *
 * In this example, we made a Producer class object , a q class object and a Consumer class object.
 * But we make threads of the q class objects only .
 * When the thread of q class which was passed in producer class object
 * tries to access the  q class then the thread of the q class which was passed in Consumer class object has to wait using
 * the wait() method until the thread of q class in producer class notifies the thread of q of Consumer class object using the notify() method
 * and vice versa.
 *
 * Here two threads are trying to access the same class, so there can be a read write problem where one of the
 * object is writing the value whereas the other is trying to read the value at the same time.
 */

public class ProducerConsumerDemo2 {
    public static void main(String[] args) throws InterruptedException {
        NewQ ob = new NewQ();
        Producer ob1 = new Producer(ob);
        Consumer ob2 = new Consumer(ob);
        System.out.println("Press Control-c for exit");

        ob1.start();
        ob2.start();

        ob1.join();
        ob2.join();

    }

    static class NewQ {
        private int n;
        private boolean value = false;

        synchronized void put(int n) {
            while (value) {
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.n = n;
            System.out.println("Put: " + this.n);
            value = true;
            notify();
        }


        synchronized int get() {
            while (!value) {
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Got: " + this.n);
            value = false;
            notify();
            return this.n;
        }
    }

    static class Producer extends Thread {
        private final NewQ ob;

        Producer(NewQ ob) {
            super("Producer");
            this.ob = ob;
        }

        @Override
        public void run() {
            int i = 0;
            while (true) {
                ob.put(i++);
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

    static class Consumer extends Thread {
        private final NewQ ob;

        Consumer(NewQ ob) {
            super("Consumer");
            this.ob = ob;
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
}
