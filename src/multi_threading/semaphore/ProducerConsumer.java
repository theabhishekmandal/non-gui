package multi_threading.semaphore;

import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * For explanation go to notes
 */
public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        Semaphore full = new Semaphore(0);
        Semaphore empty = new Semaphore(1);
        Queue<Integer> queue = new ArrayDeque<>();

        Producer producer = new Producer(empty, full, queue);
        Consumer consumer = new Consumer(empty, full, queue);

        List<Thread> allThreadList = new ArrayList<>();
        allThreadList.add(producer);
        allThreadList.add(consumer);

        WatcherThread watcherThread = new WatcherThread(allThreadList);
        watcherThread.start();

        consumer.start();
        // Sleep denoting if Consumer starts first then also it won't be able to consume
        // anything until producer produces.
        Thread.sleep(1000);
        producer.start();

        producer.join();
        consumer.join();
        watcherThread.join();
        System.out.println("Everything is done");
    }

    // This class shows which Thread is in which state
    static class WatcherThread extends Thread {
        private final List<Thread> threadList;

        WatcherThread(List<Thread> threadList) {
            this.threadList = threadList;
        }

        @Override
        public void run() {
            while (true) {
                boolean allThreadsDone = threadList
                        .stream()
                        .peek(x -> System.out.println(x.getName() + " ->" + x.getState()))
                        .allMatch(x -> x.getState() == State.TERMINATED);

                if (allThreadsDone) {
                    break;
                }
            }
        }
    }


    static class Producer extends Thread {
        private final Random random = new Random();
        private final Semaphore empty;
        private final Semaphore full;
        private final Queue<Integer> queue;

        Producer(Semaphore empty, Semaphore full, Queue<Integer> queue) {
            super("Producer");
            this.empty = empty;
            this.full = full;
            this.queue = queue;
        }

        @Override
        public void run() {
            int size = random.nextInt(10);
            System.out.println("Total items to be produced " + size);
            for (int i = 0; i < size; i++) {
                try {
                    empty.acquire();

                    int producedValue = random.nextInt(10);
                    System.out.println("Produced Value " + producedValue);
                    queue.add(producedValue);

                    full.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            finish();
        }

        // release the opposite locks
        private void finish() {
            System.out.println(Thread.currentThread().getName() + " done");
            full.release();
        }
    }

    static class Consumer extends Thread {
        private final Semaphore empty;
        private final Semaphore full;
        private final Queue<Integer> queue;

        Consumer(Semaphore empty, Semaphore full, Queue<Integer> queue) {
            super("Consumer");
            this.empty = empty;
            this.full = full;
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    full.acquire();

                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty");
                        break;
                    }
                    int valueToConsume = queue.poll();
                    System.out.println("Consumed Value " + valueToConsume);

                    empty.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
           finish();
        }

        // release the opposite locks
        private void finish() {
            System.out.println(Thread.currentThread().getName() + " done");
            empty.release();
        }
    }
}
