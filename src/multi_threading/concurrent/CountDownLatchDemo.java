package multi_threading.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
       var countDownLatch = new CountDownLatch(3);
       var thread = new Thread(new Reader(countDownLatch, "first", "1-file"));
       var thread1 = new Thread(new Reader(countDownLatch, "second", "2-file"));
       var thread2 = new Thread(new Reader(countDownLatch, "third", "3-file"));

       thread.start();
       thread1.start();
       thread2.start();

        try {
            // only if the above threads are finished, then only the main thread will resume, otherwise the thread
            // will wait
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("files are read start further processing");
    }

    static class Reader implements Runnable {
        private final CountDownLatch countDownLatch;
        private final String threadName;
        private final String fileName;

        public Reader(CountDownLatch countDownLatch, String threadName, String fileName) {
            this.countDownLatch = countDownLatch;
            this.threadName = threadName;
            this.fileName = fileName;
        }

        @Override
        public void run() {
            // here sleep is indicating that if it takes time to set the countDown to zero, then thread will wait
            System.out.println("Reading file " + fileName + " thread " + threadName);
            sleep(5000);

            countDownLatch.countDown();
        }

        private void sleep(long milliSecond) {
            try {
                Thread.sleep(milliSecond);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

