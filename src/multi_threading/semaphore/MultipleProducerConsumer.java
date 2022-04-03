package multi_threading.semaphore;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * This is an example of Multiple Producers with Multiple Consumers.
 */
public class MultipleProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        int numberOfProducerAndConsumer = 10;
        Semaphore full = new Semaphore(0);
        Semaphore empty = new Semaphore(numberOfProducerAndConsumer);
        Queue<Integer> queue = new ArrayDeque<>();
        ReentrantLock producerLock = new ReentrantLock();
        ReentrantLock consumerLock = new ReentrantLock();


        List<Producer> producerThreadList = IntStream.range(0, numberOfProducerAndConsumer)
                .mapToObj(num -> new Producer(num, empty, full, queue, producerLock)).toList();


        List<Consumer> consumerThreadList = IntStream.range(0, numberOfProducerAndConsumer)
                .mapToObj(num -> new Consumer(num, empty, full, queue, consumerLock)).toList();


        producerThreadList.forEach(Thread::start);
        consumerThreadList.forEach(Thread::start);

        for (Producer producer : producerThreadList) {
            producer.join();
        }

        for (Consumer consumer : consumerThreadList) {
            consumer.join();
        }

    }

    static class Producer extends Thread {
        private final Random random = new Random();
        private final Semaphore empty;
        private final Semaphore full;
        private final Queue<Integer> queue;
        private final Lock lock;

        Producer(int number, Semaphore empty, Semaphore full, Queue<Integer> queue, Lock lock) {
            super("Producer" + number);
            this.empty = empty;
            this.full = full;
            this.queue = queue;
            this.lock = lock;
        }

        @Override
        public void run() {
            int size = random.nextInt(10);
            System.out.println("Total items to be produced " + size);
            for (int i = 0; i < size; i++) {
                int producedValue = random.nextInt(10);
                try {
                    empty.acquire();

                    lock.lock();
                    try {
                        System.out.println("Produced Value " + producedValue);
                        queue.add(producedValue);
                    } finally {
                        lock.unlock();
                    }

                    full.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            finish();
        }

        // finish method releases so consumer can finish
        private void finish() {
            System.out.println(Thread.currentThread().getName() + " done");
            full.release();
        }
    }

    static class Consumer extends Thread {
        private final Semaphore empty;
        private final Semaphore full;
        private final Queue<Integer> queue;
        private final Lock lock;

        Consumer(int number, Semaphore empty, Semaphore full, Queue<Integer> queue, Lock lock) {
            super("Consumer" + number);
            this.empty = empty;
            this.full = full;
            this.queue = queue;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    full.acquire();

                    lock.lock();
                    try {
                        if (queue.isEmpty()) {
                            System.out.println("Queue is empty");
                            break;
                        }
                        int valueToConsume = queue.poll();
                        System.out.println("Consumed Value " + valueToConsume);
                    } finally {
                        lock.unlock();
                    }

                    empty.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            finish();
        }

        private void finish() {
            System.out.println(Thread.currentThread().getName() + " done");
            empty.release();
        }
    }
}
