package multi_threading.condition;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * See notes for better explanation
 */
public class ProducerConsumerUsingCondition {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Queue<Integer> queue = new ArrayDeque<>(1);

        Producer producer = new Producer("Producer", reentrantLock, condition, queue);
        Consumer consumer = new Consumer("Consumer", reentrantLock, condition, queue);

        consumer.start();
        producer.start();


        producer.join();
        consumer.join();
    }

    static class Producer extends Thread {
        private final Lock lock;
        private final Condition condition;
        private final Queue<Integer> queue;
        private final Scanner scanner;

        Producer(String name, Lock lock, Condition condition, Queue<Integer> queue) {
            super(name);
            this.lock = lock;
            this.condition = condition;
            this.queue = queue;
            this.scanner = new Scanner(System.in);
        }

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println("Enter your value in integer");
                int value = scanner.nextInt();
                System.out.println("Produced value is " + value);
                queue.add(value);
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    static class Consumer extends Thread {
        private final Lock lock;
        private final Condition condition;
        private final Queue<Integer> queue;

        Consumer(String name, Lock lock, Condition condition, Queue<Integer> queue) {
            super(name);
            this.lock = lock;
            this.condition = condition;
            this.queue = queue;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                // wait till the queue is populated
                while (queue.isEmpty()) {
                    condition.await();
                }

                // need to lock again as we are modifying the queue.
                lock.lock();
                Integer value = queue.poll();
                System.out.println("Consumed value is " + value);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
