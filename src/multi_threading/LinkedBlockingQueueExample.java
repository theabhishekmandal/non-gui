package multi_threading;

/**
 * This is an example of how to use LinkedBlockingQueue.
 * -    LinkedBlockingQueue has a advantage that if
 *      there are no elements in the queue then it will wait till any thread adds the element in the queue.
 * -    This type of queue can be shared by different objects without any effect
 * -    In this example the main thread is adding elements to the queue, whereas the QueueConsumer threads
 *      wait till any elements are available, if they are available then it will poll them out from the head
 *
 */

import java.util.concurrent.LinkedBlockingQueue;
class QueueConsumer implements Runnable {
    private int counter = 0;
    private final LinkedBlockingQueue<Integer> linkedBlockingQueue;
    public QueueConsumer() {
        this.linkedBlockingQueue = new LinkedBlockingQueue<>();
        Thread thread = new Thread(this, "test");
        thread.start();
    }
    public void run() {
        int a;
        boolean flag = true;
        while(flag) {
            try {
                a = this.linkedBlockingQueue.take();
                counter++;
                System.out.println("ThreadName " + Thread.currentThread().getName() + " value polled = " + a + " counter = " + counter);
            } catch (Exception e) {
                flag = false;
            }
        }
    }
    public void add(int a) {
        System.out.println("ThreadName " + Thread.currentThread().getName() + " value added = " + a);
        this.linkedBlockingQueue.add(a);
    }
}
public class LinkedBlockingQueueExample {
    public static void main(String[] args) throws InterruptedException {
        QueueConsumer consumer = new QueueConsumer();
        int counter = 0;
        int step = 0;
        int i = 0;
        while(true) {
            for (; i < 100; i++) {
                consumer.add(i);
                counter++;
            }
            step++;
            Thread.sleep(15000);
            if (step == 5) {
                break;
            }
            i = 0;
        }
        System.out.println("elements added till now " + counter);
    }
}
