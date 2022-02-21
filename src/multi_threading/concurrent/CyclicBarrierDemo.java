package multi_threading.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        var cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("All threads finished waiting"));

        var one = new Thread(new Reader(cyclicBarrier, "first"));
        var two = new Thread(new Reader(cyclicBarrier, "second"));

        one.start();
        two.start();

        try {
            // can also make main thread wait
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    static class Reader implements Runnable{
        private final CyclicBarrier cyclicBarrier;

        private final String name;

        public Reader(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + " thread waiting");
            sleep(5000);

            // until all methods call the await method, all the threads will be suspended, only after the last thead
            // calls the await method, then only the execution will resume
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
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
